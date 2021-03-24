package oldmoon.dustw.tinkerdream.materials;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.items.ModItemsBase;
import oldmoon.dustw.tinkerdream.util.MaterialTypes;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.traits.ITrait;

/**
 * @author DustW
 */
public class ModMaterialsBase {
    Item item;
    Material material;

    public ModMaterialsBase(String name, int color) {
        this.item = new ModItemsBase(name);
        this.material = new Material(TinkerDream.MOD_ID + "." + this.item.getRegistryName().getPath(), color);

        ModMaterialsList.MATERIALS_LIST.add(this);
    }

    public Item getItem() {
        return item;
    }

    public Material getMaterial() {
        return material;
    }

    public ModMaterialsBase setFluid(Fluid fluid) {
        this.material.setFluid(fluid);
        return this;
    }

    public ModMaterialsBase addTrait(ITrait trait, String materialType) {
        if (MaterialTypes.BASE.equals(materialType)) {
            this.material.addTrait(trait);
        }
        else {
            this.material.addTrait(trait, materialType);
        }
        return this;
    }

    public ModMaterialsBase withStats(IMaterialStats... materialStats) {
        for (IMaterialStats stats: materialStats) {
            this.material.addStats(stats);
        }
        return this;
    }

    public void registry() {
        this.material.addItem(this.item, 1, 144);
        this.material.setRepresentativeItem(this.item);
        MaterialIntegration materialIntegration = new MaterialIntegration(this.material, this.material.getFluid());
        materialIntegration.preInit();
        materialIntegration.integrate();
    }
}
