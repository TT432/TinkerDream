package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author NmmOC7
 */
@Mod.EventBusSubscriber
public class ToolsEvent {
    @SubscribeEvent
    public static void onEntityMount(EntityMountEvent event) {
        if (!event.getWorldObj().isRemote){
            Entity entity = event.getEntityMounting();

            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                Entity ridingEntity = player.getRidingEntity();

                if (ridingEntity instanceof EntityHorse) {
                    EntityHorse entityHorse = (EntityHorse) ridingEntity;

                    if (event.isDismounting()) {
                        entityHorse.setDead();
                    }
                }
            }
        }
    }
}
