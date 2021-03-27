package oldmoon.dustw.tinkerdream.stats;

import oldmoon.dustw.tinkerdream.util.StatsTypes;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;

import java.util.List;

/**
 * @author NmmOC7
 */
public class HorseMedalCoreStats extends AbstractMaterialStats {
    public final int durability;
    public final int horseHealth;
    public final float horseSpeed;
    public final int horseArmor;

    public HorseMedalCoreStats(int durability, int horseHealth, float horseSpeed, int horseArmor) {
        super(StatsTypes.HORSE_MEDAL_CORE);
        this.durability = durability;
        this.horseHealth = horseHealth;
        this.horseSpeed = horseSpeed;
        this.horseArmor = horseArmor;
    }

    public int getHorseHealth() {
        return horseHealth;
    }

    public float getHorseSpeed() {
        return horseSpeed;
    }

    public int getHorseArmor() {
        return horseArmor;
    }

    @Override
    public List<String> getLocalizedInfo() {
        return null;
    }

    @Override
    public List<String> getLocalizedDesc() {
        return null;
    }
}
