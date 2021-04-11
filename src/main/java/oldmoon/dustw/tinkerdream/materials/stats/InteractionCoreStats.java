package oldmoon.dustw.tinkerdream.materials.stats;

import com.google.common.collect.ImmutableList;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

import java.util.List;

/**
 * @author NmmOC7
 */
public class InteractionCoreStats extends AbstractMaterialStats {
    public final int durability;

    public InteractionCoreStats(int durability) {
        super(StatsTypes.INTERACTION_CORE);

        this.durability = durability;

        ModStatsList.STATS_LIST.add(this);
    }

    /**
     * Returns a list containing a String for each player-relevant value.</br>
     * Each line should consist of the name of the value followed by the value itself.</br>
     * Example: "Durability: 25"</br>
     * </br>
     * This is used to display properties of materials to the user.
     */
    @Override
    public List<String> getLocalizedInfo() {
        return ImmutableList.of(HeadMaterialStats.formatDurability(this.durability));
    }

    @Override
    public List<String> getLocalizedDesc() {
        return ImmutableList.of(Util.translate(HeadMaterialStats.LOC_DurabilityDesc));
    }
}
