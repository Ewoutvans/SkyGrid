package cloud.zeroprox.skygrid.serialize;

import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;
import org.spongepowered.api.item.inventory.ItemStack;

/**
 * Created by ewoutvanschil on 8/01/18.
 */
@ConfigSerializable
public class SkyGridItemWeight {

    @Setting("weight")
    public int weight;

    @Setting("itemstack")
    public ItemStack itemtype;

    @Setting("amount")
    public SkyGridAmount amount;

    public SkyGridItemWeight(){}

    public SkyGridItemWeight(ItemStack item, int we, SkyGridAmount range) {
        itemtype = item;
        weight = we;
        amount = range;
    }
}
