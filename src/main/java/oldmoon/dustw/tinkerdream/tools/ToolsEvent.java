package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oldmoon.dustw.tinkerdream.util.AttributeTypes;
import oldmoon.dustw.tinkerdream.util.NBTHelper;
import slimeknights.tconstruct.library.events.TinkerCraftingEvent;
import slimeknights.tconstruct.library.utils.ToolHelper;

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
    public static void onToolsCrafting(TinkerCraftingEvent.ToolCraftingEvent event) {
        ItemStack stack = event.getItemStack();
        EntityPlayer player = event.getPlayer();
        float attackDamage = ToolHelper.getActualAttack(stack);
        float attackSpeed = ToolHelper.getActualAttackSpeed(stack);

        if (stack.getItem() instanceof ToolLance) {
            NBTHelper.addAttribute(stack.serializeNBT(), AttributeTypes.ATTACK_DAMAGE, attackDamage, 1);
            NBTHelper.addAttribute(stack.serializeNBT(), AttributeTypes.ATTACK_SPEED, attackSpeed, 1);

            NBTHelper.addAttribute(stack.serializeNBT(), AttributeTypes.REACH_DISTANCE, 6, 1);
        }
    }
}
