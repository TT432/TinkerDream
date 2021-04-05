package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oldmoon.dustw.tinkerdream.util.fork.EntityFinders;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.utils.EntityUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.ArrayList;
import java.util.List;

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

    @SubscribeEvent
    public static void onMouseClick(MouseEvent event) {
        // 左键
        if (event.getButton() == 0) {
        }
    }
}
