package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.util.StatsTypes;
import oldmoon.dustw.tinkerdream.util.Util;
import oldmoon.dustw.tinkerdream.util.fork.EntityFinders;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.client.particle.Particles;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.EntityUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

/**
 * @author NmmOC7
 */
public class ToolLance extends TinkerToolCore {
    private static PartMaterialType partMaterialType = new PartMaterialType(ModPartsList.TEST_PART_A, StatsTypes.TEST_A);

    public static final float DURABILITY_MODIFIER = 1.5f;

    public ToolLance() {
        super(partMaterialType,
                PartMaterialType.head(TinkerTools.swordBlade),
                PartMaterialType.handle(TinkerTools.toolRod)
        );

        this.addCategory(Category.WEAPON);
        this.setTranslationKey(TinkerDream.MOD_ID + ".test_tool");
        this.setRegistryName(TinkerDream.MOD_ID + ":test_tool");

        ModToolsList.TOOLS_LIST.add(this);
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        ToolNBT data = buildDefaultTag(materials);

        data.attack += 0.5f;
        data.durability *= DURABILITY_MODIFIER;
        data.attackSpeedMultiplier = 0.7f;

        return data;
    }

    @Override
    public float damagePotential() {
        return 1.1f;
    }

    @Override
    public double attackSpeed() {
        return 0.7f;
    }

    @Override
    public float getRepairModifierForPart(int index) {
        return DURABILITY_MODIFIER;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public boolean canDisableShield(ItemStack stack, ItemStack shield, EntityLivingBase entity, EntityLivingBase attacker) {
        if (attacker instanceof EntityPlayer) {
            ((EntityPlayer) entity).getCooldownTracker().setCooldown(shield.getItem(), 200000);
        }

        return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        TConstruct.proxy.spawnAttackParticle(Particles.FRYPAN_ATTACK, entity, 0.3);

        return super.onLeftClickEntity(stack, player, entity);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!ToolHelper.isBroken(playerIn.getHeldItem(EnumHand.MAIN_HAND))) {
            if (playerIn.isRiding()) {
                ItemStack itemStackIn = playerIn.getHeldItem(handIn);
                playerIn.setActiveHand(handIn);
                return ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn);
            }
            else {
                if (!worldIn.isRemote) {
                    EntityHorse entityHorse = new EntityHorse(worldIn);

                    entityHorse.setJumpPower(1);
                    entityHorse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(playerIn.getMaxHealth() * 2);
                    entityHorse.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(playerIn.getTotalArmorValue() + 10);
                    entityHorse.setAIMoveSpeed(3.5f);
                    entityHorse.setHorseSaddled(true);
                    entityHorse.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
                    entityHorse.setTamedBy(playerIn);

                    worldIn.spawnEntity(entityHorse);
                    playerIn.startRiding(entityHorse);
                }
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = ((EntityPlayer) entityLiving);
            player.addExhaustion(0.2F);
            player.getCooldownTracker().setCooldown(stack.getItem(), 100);

            EntityHorse horse = (EntityHorse) player.getRidingEntity();

            if (horse != null && !horse.isElytraFlying()) {
                horse.setSprinting(true);

                float speed = 0.5F * timeLeft;
                if (speed > 5) {
                    speed = 5;
                }

                Util.entitySprint(horse, speed, 0);

                double attackDistance = (Util.getSprintDistance(horse) + 3) * 2;

                List<? extends Entity> entities = EntityFinders.facingHeight(horse, 3, attackDistance, 2, EntitySelectors.IS_ALIVE);

                for (Entity entity : entities) {
                    if (!(entity instanceof EntityPlayer) && entity instanceof EntityLivingBase) {
                        player.sendMessage(new TextComponentString(entity.toString()));

                        ToolHelper.attackEntity(stack, this, player, entity, null, false);
                        entity.attackEntityFrom(DamageSource.causePlayerDamage(player), stack.getItemDamage() / 1000f);

                        TConstruct.proxy.spawnAttackParticle(Particles.FRYPAN_ATTACK, entity, 0.3);

                        entity.motionY += 1;
                    }
                }

                stack.setItemDamage(-1);
                ToolHelper.breakTool(stack, player);
            }
        }

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);
    }
}
