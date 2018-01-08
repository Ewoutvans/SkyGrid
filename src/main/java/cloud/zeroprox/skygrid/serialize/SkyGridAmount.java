package cloud.zeroprox.skygrid.serialize;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
@ConfigSerializable
public class SkyGridAmount {

    @Setting("fixed")
    public boolean fixed;

    @Setting("min")
    public int min;

    @Setting("max")
    public int max;

    public SkyGridAmount() {}

    public SkyGridAmount(boolean fi, int mi, int ma) {
        fixed = fi;
        min = mi;
        max = ma;
    }
}