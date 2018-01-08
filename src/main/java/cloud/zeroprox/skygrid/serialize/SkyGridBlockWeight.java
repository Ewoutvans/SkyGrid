package cloud.zeroprox.skygrid.serialize;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.block.BlockState;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
@ConfigSerializable
public class SkyGridBlockWeight {

    @Setting("weight")
    public int weight;

    @Setting("blocktype")
    public BlockState blockState;


    public SkyGridBlockWeight() {}

    public SkyGridBlockWeight(BlockState defaultState, int i) {
        blockState = defaultState;
        weight = i;
    }
}
