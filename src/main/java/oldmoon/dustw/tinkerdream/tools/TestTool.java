package oldmoon.dustw.tinkerdream.tools;

import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

/**
 * @author NmmOC7
 */
public class TestTool extends TinkerToolCore {
    private static PartMaterialType partMaterialType = new PartMaterialType(ModPartsList.TEST_PART_A, ModPartsList.TEST_PART_A.getRegistryName().getPath());

    public static final float DURABILITY_MODIFIER = 1.1f;

    public TestTool() {
        super(partMaterialType, PartMaterialType.head(TinkerTools.swordBlade), PartMaterialType.handle(TinkerTools.toolRod));
        this.addCategory(Category.WEAPON);
        this.setTranslationKey(TinkerDream.MOD_ID + ".test_tool");
        this.setRegistryName(TinkerDream.MOD_ID + ":test_tool");

        ModToolsList.TOOLS_LIST.add(this);
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        ToolNBT data = buildDefaultTag(materials);

        data.attack += 0.5f;
        data.durability *= DURABILITY_MODIFIER;

        return data;
    }

    /**
     * Multiplier for damage from materials. Should be fixed per tool.
     */
    @Override
    public float damagePotential() {
        return 1.1f;
    }

    /**
     * Allows you set the base attack speed, can be changed by modifiers. Equivalent to the vanilla attack speed.
     * 4 is equal to any standard item. Value has to be greater than zero.
     */
    @Override
    public double attackSpeed() {
        return 0.99f;
    }
}
