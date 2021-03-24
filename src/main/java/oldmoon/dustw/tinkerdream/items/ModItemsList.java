package oldmoon.dustw.tinkerdream.items;

import net.minecraft.item.ItemStack;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;
import slimeknights.mantle.client.CreativeTab;

public class ModItemsList {
    public static final ModItemsBase ICON = new ModItemsBase("icon");

    public static final CreativeTab MOD_TAB = new CreativeTab(TinkerDream.MOD_ID + ".tab", new ItemStack(ICON));

}
