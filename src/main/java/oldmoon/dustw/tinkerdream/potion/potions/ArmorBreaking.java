package oldmoon.dustw.tinkerdream.potion.potions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.potion.ModPotionList;
import oldmoon.dustw.tinkerdream.potion.fork.BasePotion;
import oldmoon.dustw.tinkerdream.util.Util;

import javax.annotation.Nullable;

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
