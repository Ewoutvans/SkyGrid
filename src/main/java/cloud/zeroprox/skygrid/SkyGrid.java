package cloud.zeroprox.skygrid;

import cloud.zeroprox.skygrid.config.WorldConfig;
import cloud.zeroprox.skygrid.config.WorldConfigManager;
import cloud.zeroprox.skygrid.modifiers.SkyGridModifier;
import cloud.zeroprox.skygrid.serialize.SkyGridBlockWeight;
import cloud.zeroprox.skygrid.serialize.SkyGridEntityWeight;
import cloud.zeroprox.skygrid.serialize.SkyGridItemWeight;
import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.filter.Getter;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;
import org.spongepowered.api.text.format.TextStyles;
import org.spongepowered.api.util.weighted.WeightedObject;
import org.spongepowered.api.util.weighted.WeightedSerializableObject;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.gen.WorldGeneratorModifier;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Plugin(
        id = "skygrid",
        name = "Skygrid",
        description = "SkyGrid world generation",
        url = "https://www.zeroprox.cloud",
        authors = {
                "ewoutvs_",
                "Alagild"
        }
)
public class SkyGrid {

    @Inject
    public Logger logger;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private Path defaultConfig;

    @Inject
    @DefaultConfig(sharedRoot = true)
    private ConfigurationLoader<CommentedConfigurationNode> configManager;

    private ConfigurationNode rootNode;

    private static SkyGrid instance;
    private static WorldConfigManager worldConfigManager;
    private static WorldConfig DEFAULT_WORLD_CONFIG;


    @Listener
    public void onGameInitialization(GameInitializationEvent event) {
        instance = this;
        worldConfigManager = new WorldConfigManager();
        DEFAULT_WORLD_CONFIG = worldConfigManager.getWorldConfig("DEFAULT");

        Sponge.getRegistry().register(WorldGeneratorModifier.class , new SkyGridModifier());

        configManager = HoconConfigurationLoader.builder().setPath(defaultConfig).build();
        try {
            rootNode = configManager.load();
            loadConfig();
        } catch(IOException e) {
        } catch (ObjectMappingException e) {
            e.printStackTrace();
        }

    }

    private void loadConfig() throws IOException, ObjectMappingException {
        if (rootNode.getNode("overworld", "block").isVirtual()) {
            logger.info("Creating configuration");

            List<SkyGridBlockWeight> blocks  = DEFAULT_WORLD_CONFIG.blocks.stream().map(wblock -> new SkyGridBlockWeight(((WeightedObject<BlockState>)wblock).get(), (int) wblock.getWeight())).collect(Collectors.toList());
            rootNode.getNode("overworld", "block").setValue(new TypeToken<List<SkyGridBlockWeight>>(){}, blocks);

            List<SkyGridEntityWeight> spawners  = DEFAULT_WORLD_CONFIG.spawners.stream().map(wentity -> new SkyGridEntityWeight(wentity.get().getType(), (int) wentity.getWeight())).collect(Collectors.toList());
            rootNode.getNode("overworld", "spawners").setValue(new TypeToken<List<SkyGridEntityWeight>>(){}, spawners);

            List<SkyGridItemWeight> items  = DEFAULT_WORLD_CONFIG.items.stream().map(witem -> ((WeightedObject<SkyGridItemWeight>)witem).get()).collect(Collectors.toList());
            rootNode.getNode("overworld", "items").setValue(new TypeToken<List<SkyGridItemWeight>>(){}, items);


            rootNode.getNode("overworld", "spawn-chests").setValue(DEFAULT_WORLD_CONFIG.spawnChests);
            rootNode.getNode("overworld", "spawn-spawners").setValue(DEFAULT_WORLD_CONFIG.spawnSpawners);
            rootNode.getNode("overworld", "biome").setValue(TypeToken.of(BiomeType.class), DEFAULT_WORLD_CONFIG.biomeType);

            configManager.save(rootNode);
            loadConfig();
        } else {
            // Loading it
            logger.info("Loading configuration");
            List<SkyGridBlockWeight> blocks = rootNode.getNode("overworld", "block").getList(TypeToken.of(SkyGridBlockWeight.class));
            List<SkyGridEntityWeight> spawners = rootNode.getNode("overworld", "spawners").getList(TypeToken.of(SkyGridEntityWeight.class));
            List<SkyGridItemWeight> items = rootNode.getNode("overworld", "items").getList(TypeToken.of(SkyGridItemWeight.class));
            boolean spawnChests = rootNode.getNode("overworld", "spawn-chests").getBoolean();
            boolean spawnSpawners = rootNode.getNode("overworld", "spawn-spawners").getBoolean();
            BiomeType biomeType = rootNode.getNode("overworld", "biome").getValue(TypeToken.of(BiomeType.class));

            WorldConfig wc = new WorldConfig(spawnChests, spawnSpawners, biomeType);
            wc.setBlocks(blocks);
            wc.setSpawners(spawners);
            wc.setItems(items);

            logger.info("Blocks: " + blocks.size());
            logger.info("Spawners: " + spawners.size());



            getWorldConfigManager().addConfig(wc, "overworld");
        }
    }

    public static SkyGrid getInstance() {
        return instance;
    }

    public static WorldConfigManager getWorldConfigManager() {
        return worldConfigManager;
    }


}
