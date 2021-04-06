package oldmoon.dustw.tinkerdream.stats;

import com.google.common.collect.ImmutableList;
import oldmoon.dustw.tinkerdream.util.StatsTypes;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.HeadMaterialStats;

import java.util.List;

/**
 * @author NmmOC7
 */
public class LanceHeadStats extends AbstractMaterialStats {
    public final float attack;
    public final int durability;

    public LanceHeadStats(float attack, int durability) {
        super(StatsTypes.TEST_A);
        this.attack = attack;
        this.durability = durability;

        ModStatsList.STATS_LIST.add(this);
    }

    @Override
    public List<String> getLocalizedInfo() {
        return ImmutableList.of(HeadMaterialStats.formatAttack(this.attack), HeadMaterialStats.formatDurability(this.durability));
    }

    @Override
    public List<String> getLocalizedDesc() {
        return ImmutableList.of(Util.translate(HeadMaterialStats.LOC_AttackDesc), Util.translate(HeadMaterialStats.LOC_DurabilityDesc));
    }
}
