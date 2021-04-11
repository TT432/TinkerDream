package oldmoon.dustw.tinkerdream.parts;

import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tools.ToolPart;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class ModPartsList {
    public static final ArrayList<ToolPart> PARTS_LIST = new ArrayList<>();

    public static final ModPartsBase LANCE_HEAD = new ModPartsBase("lance_head", Material.VALUE_Ingot * 3, MaterialTypes.HEAD);
    public static final ModPartsBase INTERACTION_CORE = new ModPartsBase("interaction_core", Material.VALUE_Ingot * 8, MaterialTypes.HEAD);
}
