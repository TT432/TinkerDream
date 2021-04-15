package oldmoon.dustw.tinkerdream.items;

import net.minecraft.item.Item;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;

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
