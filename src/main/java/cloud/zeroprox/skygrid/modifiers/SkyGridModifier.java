package cloud.zeroprox.skygrid.modifiers;

import cloud.zeroprox.skygrid.SkyGrid;
import cloud.zeroprox.skygrid.config.WorldConfig;
import cloud.zeroprox.skygrid.serialize.SkyGridItemWeight;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.MobSpawner;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.TileEntityTypes;
import org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.Slot;
import org.spongepowered.api.item.inventory.property.SlotIndex;
import org.spongepowered.api.item.inventory.property.SlotPos;
import org.spongepowered.api.item.inventory.query.QueryOperationTypes;
import org.spongepowered.api.text.translation.Translation;
import org.spongepowered.api.util.weighted.VariableAmount;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.biome.BiomeTypes;
import org.spongepowered.api.world.extent.Extent;
import org.spongepowered.api.world.gen.Populator;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.gen.WorldGenerator;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;
import org.spongepowered.api.world.gen.populator.RandomBlock;
import org.spongepowered.api.world.storage.WorldProperties;

import java.util.Locale;
import java.util.Random;
import java.util.function.Predicate;

/**
 * Created by ewoutvanschil on 8/01/18.
 */

public class SkyGridModifier implements WorldGeneratorModifier {

    Random r = new Random();
    VariableAmount variableAmount = VariableAmount.range(2, 9);


    @Override
    public void modifyWorldGenerator(WorldProperties world, DataContainer settings, WorldGenerator worldGenerator) {
        WorldConfig worldConfig = SkyGrid.getWorldConfigManager().getWorldConfig(world.getDimensionType().getName());

        worldGenerator.getGenerationPopulators().clear();
        worldGenerator.getPopulators().clear();
        worldGenerator.setBiomeGenerator(buffer -> {
            Vector3i min = buffer.getBiomeMin();
            Vector3i max = buffer.getBiomeMax();

            for (int x = min.getX(); x <= max.getX(); x++) {
                for (int z = min.getZ(); z <= max.getZ(); z++) {
                    buffer.setBiome(x, 0, z, worldConfig.biomeType);
                }
            }
        });

        worldGenerator.setBaseGenerationPopulator((world1, buffer, biomes) -> {
            for (int x = buffer.getBlockMin().getX(); x <= buffer.getBlockMax().getX(); x += 4) {
                for (int z = buffer.getBlockMin().getZ(); z <= buffer.getBlockMax().getZ(); z += 4) {
                    for (int y = buffer.getBlockMin().getY(); y < buffer.getBlockMax().getY(); y += 4) {
                        buffer.setBlock(new Vector3i(x, y, z), worldConfig.blocks.get(r).get(0));
                    }

                }
            }
        });
        worldGenerator.getBiomeSettings(worldConfig.biomeType).getPopulators().clear();
        worldGenerator.getBiomeSettings(worldConfig.biomeType).getGenerationPopulators().clear();
        worldGenerator.getBiomeSettings(worldConfig.biomeType).getGroundCoverLayers().clear();


        if (worldConfig.spawnChests) {
            worldGenerator.getBiomeSettings(worldConfig.biomeType).getPopulators().add(RandomBlock.builder()
                    .block(BlockTypes.CHEST.getDefaultState())
                    .height(VariableAmount.range(0, 255))
                    .perChunk(VariableAmount.baseWithVariance(140, 3))
                    .placementTarget(NOT_AIR)
                    .build());
        }

        if (worldConfig.spawnSpawners) {
            worldGenerator.getBiomeSettings(worldConfig.biomeType).getPopulators().add(RandomBlock.builder()
                    .block(BlockTypes.MOB_SPAWNER.getDefaultState())
                    .height(VariableAmount.range(0, 255))
                    .perChunk(VariableAmount.baseWithVariance(40, 3))
                    .placementTarget(NOT_AIR)
                    .build());
        }

        worldGenerator.getBiomeSettings(worldConfig.biomeType).getPopulators().add(new Populator() {
            @Override
            public PopulatorType getType() {
                /*
                Todo: check how to do the populatortype
                 */
                return new PopulatorType() {
                    @Override
                    public String getId() {
                        return "skygrid";
                    }

                    @Override
                    public String getName() {
                        return "SkyGrid";
                    }

                    @Override
                    public Translation getTranslation() {
                        return new Translation() {
                            @Override
                            public String getId() {
                                return "";
                            }

                            @Override
                            public String get(Locale locale) {
                                return "";
                            }

                            @Override
                            public String get(Locale locale, Object... args) {
                                return "";
                            }
                        };
                    }
                };
            }

            @Override
            public void populate(World world, Extent volume, Random random) {
                if (worldConfig.spawnChests) {
                    for (TileEntity chestTile : volume.getTileEntities(tileEntity -> tileEntity.getType().equals(TileEntityTypes.CHEST))) {
                        if (chestTile instanceof TileEntityCarrier) {
                            TileEntityCarrier chest = (TileEntityCarrier) chestTile;
                            Inventory inventory = chest.getInventory();
                            for (int i = (int)variableAmount.getAmount(r); i >= 0; i--) {
                                Inventory slots = inventory.query(QueryOperationTypes.INVENTORY_PROPERTY.of(SlotPos.of(r.nextInt(9), r.nextInt(3))));
                                SkyGridItemWeight item = worldConfig.items.get(r).get(0);
                                ItemStack is =  item.itemtype.copy();
                                is.setQuantity((item.amount.fixed ? item.amount.min : (int)VariableAmount.range(item.amount.min, item.amount.max).getAmount(r)));
                                slots.set(is);
                            }
                        }
                    }
                }
                if (worldConfig.spawnSpawners) {
                    for (TileEntity spawnerTile : volume.getTileEntities(tileEntity -> tileEntity.getType().equals(TileEntityTypes.MOB_SPAWNER))) {
                        if (spawnerTile instanceof MobSpawner) {
                            MobSpawner spawner = (MobSpawner) spawnerTile;
                            spawner.offer(Keys.SPAWNER_NEXT_ENTITY_TO_SPAWN, worldConfig.spawners.get(random.nextInt(worldConfig.spawners.size())));
                        }

                    }
                }
            }
        });
    }


    public static final Predicate<Location<World>> NOT_AIR = (input) -> input.getBlock().getType() != BlockTypes.AIR;

    @Override
    public String getId() {
        return "skygrid:skygrid";
    }

    @Override
    public String getName() {
        return "SkyGrid";
    }
}
