package oldmoon.dustw.tinkerdream.parts;

import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.items.ModItemsBase;
import oldmoon.dustw.tinkerdream.items.ModItemsList;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;
import slimeknights.tconstruct.library.tools.ToolPart;

/**
 * @author NmmOC7
 */
public class ModPartsBase extends ToolPart {
    public ModPartsBase(String name, int cost) {
        super(cost);
        this.setRegistryName(TinkerDream.MOD_ID + ":" + name);
        this.setTranslationKey(TinkerDream.MOD_ID + "." + name);

        ModPartsList.PARTS_LIST.add(this);
    }
}
