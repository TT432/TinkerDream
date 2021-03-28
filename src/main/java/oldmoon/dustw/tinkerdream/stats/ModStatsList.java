package oldmoon.dustw.tinkerdream.stats;

import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.ArrayList;

public class ModStatsList {
    public static final ArrayList<AbstractMaterialStats> STATS_LIST = new ArrayList<>();

    public static final TestStats TEST_STATS = new TestStats(12, 100);
    public static final HorseMedalCoreStats HORSE_MEDAL_CORE_SATS = new HorseMedalCoreStats(10, 40, 2.5f, 0);
}
