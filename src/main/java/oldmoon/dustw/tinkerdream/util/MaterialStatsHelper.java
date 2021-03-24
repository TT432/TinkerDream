package oldmoon.dustw.tinkerdream.util;

import slimeknights.tconstruct.library.materials.ExtraMaterialStats;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.traits.ITrait;

import java.util.ArrayList;

/**
 * @author DustW
 */
public class MaterialStatsHelper {
    public final ArrayList<IMaterialStats> STATS_LIST = new ArrayList<>();

    public MaterialStatsHelper addHeadMaterialStats(int durability, float miningspeed, float attack, int harvestLevel) {
        this.STATS_LIST.add(new HeadMaterialStats(durability, miningspeed, attack, harvestLevel));
        return this;
    }

    public MaterialStatsHelper addHandleMaterialStats(int durability, float modifier) {
        this.STATS_LIST.add(new HandleMaterialStats(modifier, durability));
        return this;
    }

    public MaterialStatsHelper addExtraMaterialStats(int durability) {
        this.STATS_LIST.add(new ExtraMaterialStats(durability));
        return this;
    }

    public IMaterialStats[] getList() {
        return this.STATS_LIST.toArray(new IMaterialStats[]{});
    }
}
