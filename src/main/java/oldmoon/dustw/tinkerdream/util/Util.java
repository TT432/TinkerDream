package oldmoon.dustw.tinkerdream.util;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.MathHelper;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.IMaterialStats;

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
}