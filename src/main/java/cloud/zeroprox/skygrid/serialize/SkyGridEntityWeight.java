package cloud.zeroprox.skygrid.serialize;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.entity.EntityType;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
@ConfigSerializable
public class SkyGridEntityWeight {

    @Setting("weight")
    public int weight;

    @Setting("entity")
    public EntityType entity;


    public SkyGridEntityWeight() {}

    public SkyGridEntityWeight(EntityType defaultState, int i) {
        entity = defaultState;
        weight = i;
    }
}
