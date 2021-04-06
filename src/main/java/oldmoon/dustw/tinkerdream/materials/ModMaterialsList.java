package oldmoon.dustw.tinkerdream.materials;

import net.minecraft.item.Item;
import oldmoon.dustw.tinkerdream.stats.ModStatsList;
import oldmoon.dustw.tinkerdream.trait.ModTraitList;
import oldmoon.dustw.tinkerdream.util.MaterialStatsHelper;
import oldmoon.dustw.tinkerdream.util.MaterialTypes;

import java.util.ArrayList;

/**
 * @author DustW
 */
public class ModMaterialsList {
    public static final ArrayList<Item> ITEM_LIST = new ArrayList<>();
    public static final ArrayList<ModMaterialsBase> MATERIALS_LIST = new ArrayList<>();

    public static final ModMaterialsBase TEST_MATERIAL =
            new ModMaterialsBase("test", 0xFF00FF00)
                .withStats(new MaterialStatsHelper()
                                .addHeadMaterialStats(100, 1, 1, 3)
                                .addHandleMaterialStats(15, 0.8f)
                                .addExtraMaterialStats(30)
                                .addCustomStats(ModStatsList.LANCE_HEAD_STATS)
                                .getList())
                .addTrait(ModTraitList.TEST_TRAIT, MaterialTypes.BASE);
}
