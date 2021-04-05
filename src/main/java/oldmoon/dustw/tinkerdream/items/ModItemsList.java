package oldmoon.dustw.tinkerdream.items;

import net.minecraft.item.ItemStack;
import oldmoon.dustw.tinkerdream.TinkerDream;
import slimeknights.mantle.client.CreativeTab;

/**
 * @author DustW, NmmOC7
 */
public class ModItemsList {
    public static final ModItemsBase ICON = new ModItemsBase("icon");

    public static final CreativeTab MOD_TAB = new CreativeTab(TinkerDream.MOD_ID + ".tab", new ItemStack(ICON));
}
