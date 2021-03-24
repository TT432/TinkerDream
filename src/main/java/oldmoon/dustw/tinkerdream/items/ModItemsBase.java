package oldmoon.dustw.tinkerdream.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;
import slimeknights.mantle.client.CreativeTab;

import java.util.ArrayList;

/**
 * @author DustW
 */
public class ModItemsBase extends Item {

    public ModItemsBase(String registryName) {
        this.setRegistryName(TinkerDream.MOD_ID + ":" + registryName);
        this.setTranslationKey(TinkerDream.MOD_ID + "." + registryName);
        this.setCreativeTab(ModItemsList.MOD_TAB);

        ModMaterialsList.ITEM_LIST.add(this);
    }
}
