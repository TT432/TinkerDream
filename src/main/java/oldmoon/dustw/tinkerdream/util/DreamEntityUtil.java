package oldmoon.dustw.tinkerdream.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHangingEntity;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * @author NmmOC7
 */
public class DreamEntityUtil {
    public static EntityLiving getNearestEntityLiving(EntityPlayer player, Entity center, Entity... entities) {
        boolean isInit = false;

        EntityLiving entityTemp = null;
        double distance = 0.0f;

        for (Entity entity : entities) {
            if (!entity.equals(player) && entity instanceof EntityLiving) {
                double distanceTemp = entity.getDistanceSq(center);

                if (!isInit) {
                    isInit = true;
                    distance = distanceTemp;
                    entityTemp = (EntityLiving) entity;
                }

                if (distanceTemp < distance) {
                    entityTemp = (EntityLiving) entity;
                }
            }
        }

        return entityTemp;
    }
}
