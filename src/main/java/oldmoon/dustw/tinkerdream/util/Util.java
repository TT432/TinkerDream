package oldmoon.dustw.tinkerdream.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

        for (NBTBase material: toolMaterials) {
            stats = TinkerRegistry.getMaterial(material.toString().split("\"")[1]).getStats(identifier);

            if (stats != null) {
                return stats;
            }
        }

        return null;
    }

    public static boolean isExistForNbt(NBTTagCompound nbt, String... keys) {
        NBTTagCompound nbtTemp = nbt;

        for (String key: keys) {
            if (nbtTemp.hasKey(key)) {
                for (String keyJ: nbtTemp.getCompoundTag(key).getKeySet()) {
                    if (isExistForNbt(nbtTemp.getCompoundTag(key), keyJ)) {
                        return true;
                    };
                }
            }
            else {
                return false;
            }
        }

        return true;
    }
}
