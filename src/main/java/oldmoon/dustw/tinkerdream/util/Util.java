package oldmoon.dustw.tinkerdream.util;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import oldmoon.dustw.tinkerdream.TinkerDream;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.IMaterialStats;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author NmmOC7, DustW
 */
public class Util {
    public static IMaterialStats getStatsFromTool(ItemStack toolStack, String identifier) {
        NBTTagCompound tag = toolStack.getTagCompound();
        IMaterialStats stats;
        NBTTagList toolMaterials = tag.getCompoundTag("TinkerData").getTagList("Materials", 8);

        for (NBTBase material : toolMaterials) {
            stats = TinkerRegistry.getMaterial(material.toString().split("\"")[1]).getStats(identifier);

            if (stats != null) {
                return stats;
            }
        }

        return null;
    }

    public static boolean isExistForNbt(NBTTagCompound nbt, String... keys) {
        NBTTagCompound nbtTemp = nbt;

        for (String key : keys) {
            if (!nbtTemp.hasKey(key)) {
                return false;
            }
        }

        return true;
    }

    public static double pythagorasTheorem(boolean isLongestSide, double longestSideOrSideA, double sideB) {
        if (isLongestSide) {
            return Math.sqrt(longestSideOrSideA * longestSideOrSideA - sideB * sideB);
        }
        else {
            return Math.sqrt(longestSideOrSideA * longestSideOrSideA + sideB * sideB);
        }
    }

    public static void entitySprint(Entity entity, float speed, float motionY) {
        double xPlus = -MathHelper.sin(entity.rotationYaw / 180.0F * (float) Math.PI) *
                MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * speed;
        double zPlus = MathHelper.cos(entity.rotationYaw / 180.0F * (float) Math.PI) *
                MathHelper.cos(entity.rotationPitch / 180.0F * (float) Math.PI) * speed;

        entity.motionY += motionY;
        entity.motionX = xPlus;
        entity.motionZ = zPlus;
    }

    public static double getSprintDistance(Entity entity) {
        return Util.pythagorasTheorem(false, entity.motionX, entity.motionZ);
    }

    public static double getAttackDamage(ItemStack stack) {
        return ((AttributeModifier) stack
                    .getAttributeModifiers(EntityEquipmentSlot.MAINHAND)
                    .get("generic.attackDamage")
                    .toArray()[0]
                ).getAmount();
    }

    public static void addPotion(EntityLivingBase entity, Potion potion, int level, float seconds) {
        entity.addPotionEffect(new PotionEffect(potion, (int) (seconds * 20) + 1, level));
    }

    public static ResourceLocation getIcon(String name) {
        return new ResourceLocation(TinkerDream.MOD_ID, String.format("textures/potions/%s.png",name));
    }
}