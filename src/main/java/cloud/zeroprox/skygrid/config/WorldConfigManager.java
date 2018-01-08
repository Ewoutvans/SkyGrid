package cloud.zeroprox.skygrid.config;

import cloud.zeroprox.skygrid.config.WorldConfig;
import cloud.zeroprox.skygrid.serialize.SkyGridAmount;
import cloud.zeroprox.skygrid.serialize.SkyGridItemWeight;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.type.DirtTypes;
import org.spongepowered.api.data.type.DyeColors;
import org.spongepowered.api.data.type.StoneTypes;
import org.spongepowered.api.data.type.TreeTypes;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.util.weighted.WeightedSerializableObject;
import org.spongepowered.api.world.biome.BiomeTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
public class WorldConfigManager {

    private HashMap<String, WorldConfig> worldConfigHashMap = new HashMap<>();

    public WorldConfigManager() {
        WorldConfig worldConfig = new WorldConfig(
                true,
                true,
                BiomeTypes.FLOWER_FOREST
        );
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.DIAMOND_ORE.getDefaultState(), 1));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.EMERALD_ORE.getDefaultState(), 1));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.REDSTONE_ORE.getDefaultState(), 8));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.GOLD_ORE.getDefaultState(), 10));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.IRON_ORE.getDefaultState(), 15));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.GRASS.getDefaultState(), 50));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.COAL_ORE.getDefaultState(), 32));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.LAPIS_ORE.getDefaultState(), 6));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.LOG.getDefaultState(), 35));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.PLANKS.getDefaultState(), 15));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.WEB.getDefaultState(), 6));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.OBSIDIAN.getDefaultState(), 3));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.ICE.getDefaultState(), 4));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.PRISMARINE.getDefaultState(), 2));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.MYCELIUM.getDefaultState(), 7));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.PUMPKIN.getDefaultState(), 7));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.BOOKSHELF.getDefaultState(), 3));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.SLIME.getDefaultState(), 8));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.STONE.getDefaultState(), 50));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.WOOL.getDefaultState(), 10));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.CLAY.getDefaultState(), 10));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.SANDSTONE.getDefaultState(), 30));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.HAY_BLOCK.getDefaultState(), 8));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.WATER.getDefaultState(), 1));
        worldConfig.blocks.add(new WeightedSerializableObject<>(BlockTypes.LAVA.getDefaultState(), 1));

        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.LLAMA), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.HORSE), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.BLAZE), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.SHEEP), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.VILLAGER), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.ENDERMAN), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.CHICKEN), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.COW), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.MUSHROOM_COW), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.BAT), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.ZOMBIE), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.SKELETON), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.CREEPER), 1));
        worldConfig.spawners.add(new WeightedSerializableObject<>(EntityArchetype.of(EntityTypes.SLIME), 1));

        List<SkyGridItemWeight> items = new ArrayList<>();
        // Plants
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.OAK).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.SPRUCE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.BIRCH).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.JUNGLE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.ACACIA).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAPLING).add(Keys.TREE_TYPE, TreeTypes.DARK_OAK).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.VINE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WATERLILY).build(), 1, new SkyGridAmount(false, 1, 16)));
        //Blocks
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.STONE).add(Keys.STONE_TYPE, StoneTypes.STONE).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.STONE).add(Keys.STONE_TYPE, StoneTypes.GRANITE).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.STONE).add(Keys.STONE_TYPE, StoneTypes.DIORITE).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.STONE).add(Keys.STONE_TYPE, StoneTypes.ANDESITE).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.GRASS).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.DIRT).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.GRASS).add(Keys.DIRT_TYPE, DirtTypes.PODZOL).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.COBBLESTONE).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SAND).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.GRAVEL).build(), 1, new SkyGridAmount(false, 1, 32)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.WHITE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.ORANGE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.MAGENTA).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.LIGHT_BLUE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.YELLOW).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.GREEN).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.PINK).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.GRAY).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.SILVER).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.CYAN).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.PURPLE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.BLUE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.BROWN).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.GREEN).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.RED).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.WOOL).add(Keys.DYE_COLOR, DyeColors.BLACK).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.BRICK_BLOCK).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.OBSIDIAN).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.CLAY).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.STONEBRICK).build(), 1, new SkyGridAmount(false, 1, 16)));
        //Redstone
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.REDSTONE).build(), 1, new SkyGridAmount(false, 1, 16)));
        //Misc
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.COAL).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.IRON_INGOT).build(), 1, new SkyGridAmount(false, 1, 6)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.GOLD_INGOT).build(), 1, new SkyGridAmount(false, 1, 6)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.MILK_BUCKET).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.EGG).build(), 1, new SkyGridAmount(false, 1, 16)));
        //SpawnEggs
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.BAT).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.BLAZE).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.CAVE_SPIDER).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.CHICKEN).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.COW).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.CREEPER).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.ENDERMITE).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.LLAMA).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.PIG).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.SKELETON).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.SHEEP).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.SLIME).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.SPIDER).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.VILLAGER).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.WOLF).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.ZOMBIE).build(), 1, new SkyGridAmount(true, 1, 0)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.SPAWN_EGG).add(Keys.SPAWNABLE_ENTITY_TYPE, EntityTypes.PIG_ZOMBIE).build(), 1, new SkyGridAmount(true, 1, 0)));
        //Food
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.APPLE).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.BREAD).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.COOKED_PORKCHOP).build(), 1, new SkyGridAmount(false, 1, 16)));
        items.add(new SkyGridItemWeight(ItemStack.builder().itemType(ItemTypes.COOKED_CHICKEN).build(), 1, new SkyGridAmount(false, 1, 16)));
        worldConfig.setItems(items);

        worldConfigHashMap.put("DEFAULT", worldConfig);
    }


    public WorldConfig getWorldConfig(String worldName) {
        if (worldConfigHashMap.containsKey(worldName)) {
            return worldConfigHashMap.get(worldName);
        }
        return null;
    }

    public void addConfig(WorldConfig config, String overworld) {
        worldConfigHashMap.put(overworld, config);
    }
}
