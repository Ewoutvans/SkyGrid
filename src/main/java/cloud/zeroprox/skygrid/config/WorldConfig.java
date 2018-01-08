package cloud.zeroprox.skygrid.config;

import cloud.zeroprox.skygrid.serialize.SkyGridBlockWeight;
import cloud.zeroprox.skygrid.serialize.SkyGridEntityWeight;
import cloud.zeroprox.skygrid.serialize.SkyGridItemWeight;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.util.weighted.WeightedObject;
import org.spongepowered.api.util.weighted.WeightedSerializableObject;
import org.spongepowered.api.util.weighted.WeightedTable;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.biome.BiomeTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
public class WorldConfig {

    public boolean spawnChests, spawnSpawners;
    public WeightedTable<BlockState> blocks = new WeightedTable<>();
    public List<WeightedSerializableObject<EntityArchetype>> spawners = new ArrayList<>();
    public WeightedTable<SkyGridItemWeight> items = new WeightedTable<>();
    public BiomeType biomeType = BiomeTypes.FLOWER_FOREST;

    public WorldConfig(boolean spawnChests, boolean spawnSpawners, BiomeType type) {
        this.spawnChests = spawnChests;
        this.spawnSpawners = spawnSpawners;
        this.biomeType = type;
    }

    public void setSpawners(List<SkyGridEntityWeight> spawners) {
        this.spawners = spawners.stream().map(entry -> new WeightedSerializableObject<>(EntityArchetype.of(entry.entity), entry.weight)).collect(Collectors.toList());
    }

    public void setBlocks(List<SkyGridBlockWeight> blocks) {
        for (SkyGridBlockWeight entry : blocks)
            this.blocks.add(new WeightedObject<>(entry.blockState, entry.weight));
    }

    public void setItems(List<SkyGridItemWeight> items) {
        for (SkyGridItemWeight entry : items)
            this.items.add(new WeightedObject<>(entry, entry.weight));
    }
}