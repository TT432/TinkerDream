package oldmoon.dustw.tinkerdream.materials.stats;

import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class ModStatsList {
    public static final ArrayList<AbstractMaterialStats> STATS_LIST = new ArrayList<>();

    public static final LanceHeadStats LANCE_HEAD_STATS = new LanceHeadStats(12, 100);
    public static final InteractionCoreStats INTERACTION_CORE_STATS = new InteractionCoreStats(1000);
}
