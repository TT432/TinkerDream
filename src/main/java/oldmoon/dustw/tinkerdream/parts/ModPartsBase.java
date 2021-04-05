package oldmoon.dustw.tinkerdream.parts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import oldmoon.dustw.tinkerdream.TinkerDream;
import slimeknights.tconstruct.common.config.Config;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tools.ToolPart;

/**
 * @author NmmOC7
 */
public class ModPartsBase extends ToolPart {
    private String stats;

    public ModPartsBase(String name, int cost, String stats) {
        super(cost);
        this.setRegistryName(TinkerDream.MOD_ID + ":" + name);
        this.setTranslationKey(TinkerDream.MOD_ID + "." + name);
        this.stats = stats;

        ModPartsList.PARTS_LIST.add(this);
    }

    @Override
    public boolean canUseMaterial(Material mat) {
        return mat.hasStats(stats);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems) {
        if(this.isInCreativeTab(tab)) {
            // this adds a variant of each material to the creative menu
            for(Material mat : TinkerRegistry.getAllMaterialsWithStats(MaterialTypes.HEAD)) {
                subItems.add(getItemstackWithMaterial(mat));
                if(!Config.listAllPartMaterials) {
                    break;
                }
            }
        }
    }
}
