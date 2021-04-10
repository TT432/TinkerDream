package oldmoon.dustw.tinkerdream.util;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

/**
 * @author NmmOC7
 */
public class NBTHelper {
    public static final String ATTRIBUTE_MODIFIERS_KEY = "AttributeModifiers";
    public static final String[] SLOTS = new String[] { "mainhand", "offhand", "head", "chest", "legs", "feet" };

    public static NBTTagCompound getTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        return stack.getTagCompound();
    }

    public static NBTTagList getAttributeTag(ItemStack stack) {
        NBTTagCompound stackTag = getTag(stack);

        if (!stackTag.hasKey(ATTRIBUTE_MODIFIERS_KEY, Constants.NBT.TAG_LIST)) {
            stackTag.setTag(ATTRIBUTE_MODIFIERS_KEY, new NBTTagList());
        }

        return getTag(stack).getTagList(ATTRIBUTE_MODIFIERS_KEY, Constants.NBT.TAG_COMPOUND );
    }

    public static NBTTagList getAttributeTag(NBTTagCompound nbt) {
        if (!nbt.hasKey(ATTRIBUTE_MODIFIERS_KEY, Constants.NBT.TAG_LIST)) {
            nbt.setTag(ATTRIBUTE_MODIFIERS_KEY, new NBTTagList());
        }

        return nbt.getTagList(ATTRIBUTE_MODIFIERS_KEY, Constants.NBT.TAG_COMPOUND );
    }

    public static void addAttribute(NBTTagCompound nbt, IAttribute attribute, double amount, int slot) {
        NBTTagList stackAttributeTag = getAttributeTag(nbt);

        AttributeModifier attributeModifier = new AttributeModifier(attribute.getName(), amount, 0);
        NBTTagCompound attributeNbt = SharedMonsterAttributes.writeAttributeModifierToNBT(attributeModifier);

        attributeNbt.setString("AttributeName", attribute.getName());

        if ( slot > 0 && slot < SLOTS.length ) {
            attributeNbt.setString( "Slot", SLOTS[slot - 1] );
        }
        else {
            attributeNbt.removeTag( "Slot" );
        }

        attributeNbt.setInteger("Operation", 0);

        stackAttributeTag.appendTag(attributeNbt);
    }
}
