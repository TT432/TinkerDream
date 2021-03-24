package oldmoon.dustw.tinkerdream.trait;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import oldmoon.dustw.tinkerdream.TinkerDream;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;

public class ModTraitList {
    public static final ITrait TEST_TRAIT = new AbstractTrait(TinkerDream.MOD_ID + ".test", 0xFF00FF00) {
        @Override
        public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
            if (player.getEntityWorld().isRemote) {
                return;
            }
            player.sendMessage(new TextComponentString(I18n.format("tinkerdream.player.message", "[1]成功了")));
        }
    };
}
