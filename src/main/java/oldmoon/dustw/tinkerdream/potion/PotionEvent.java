package oldmoon.dustw.tinkerdream.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author DustW
 */
@Mod.EventBusSubscriber
public class PotionEvent {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onEntityBeHurt(LivingHurtEvent event) {
        World world = event.getEntity().getEntityWorld();
        EntityLivingBase beHurtEntity = event.getEntityLiving();
        float damageAmount = event.getAmount();

        if (!event.isCanceled()) {
            if (beHurtEntity.isPotionActive(ModPotionList.ARMOR_BREAKING) && !world.isRemote) {
                beHurtEntity.setHealth(beHurtEntity.getHealth() - (damageAmount / 20));
            }
        }
    }
}
