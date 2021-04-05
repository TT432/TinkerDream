package oldmoon.dustw.tinkerdream.util;

import slimeknights.tconstruct.library.materials.*;

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

    public MaterialStatsHelper addCustomStats(AbstractMaterialStats stats) {
        this.STATS_LIST.add(stats);
        return this;
    }

    public IMaterialStats[] getList() {
        return this.STATS_LIST.toArray(new IMaterialStats[]{});
    }
}
