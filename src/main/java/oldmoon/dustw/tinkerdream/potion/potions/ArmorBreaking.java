package oldmoon.dustw.tinkerdream.potion.potions;

import oldmoon.dustw.tinkerdream.potion.fork.BasePotion;
import oldmoon.dustw.tinkerdream.util.Util;

/**
 * @author DustW
 */
public class ArmorBreaking extends BasePotion {
    public static final String ARMOR_BREAKING_NAME = "armor_breaking";

    public ArmorBreaking() {
        super(ARMOR_BREAKING_NAME, Util.getIcon(ARMOR_BREAKING_NAME), true, 0xFF882734);
    }

    @Override
    public boolean isReady(int tick, int level) {
        return true;
    }
}
