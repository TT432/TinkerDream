package oldmoon.dustw.tinkerdream.util;

import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.tools.TinkerToolCore;

import java.util.ArrayList;

/**
 * @author NmmOC7, DustW
 */
public class Util {
    public static IMaterialStats getStatsFromTool(TinkerToolCore tool, int partAmount, String identifier) {
        IMaterialStats stats = null;

        for (int i = 0; i < partAmount; i++) {
            stats = tool.getMaterialForPartForGuiRendering(i).getStatsOrUnknown(identifier);
            if (stats != null) {
                break;
            }
        }

        return stats;
    }
}
