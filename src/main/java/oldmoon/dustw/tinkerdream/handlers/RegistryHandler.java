package oldmoon.dustw.tinkerdream.handlers;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsBase;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.potion.ModPotionList;
import oldmoon.dustw.tinkerdream.materials.stats.ModStatsList;
import oldmoon.dustw.tinkerdream.tools.ModToolsList;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.Collection;

/**
 * @author NmmOC7, DustW
 */
@Mod.EventBusSubscriber
public class RegistryHandler {
    @SubscribeEvent
    public static void onItemRegistry(RegistryEvent.Register<Item> event) {
        for (Item item: ModMaterialsList.ITEM_LIST) {
            event.getRegistry().register(item);
        }

        for (AbstractMaterialStats stats: ModStatsList.STATS_LIST) {
            Collection<Material> materials = TinkerRegistry.getAllMaterials();

            for (Material material: materials) {
                material.addStats(stats);
            }
        }

        for (ModMaterialsBase material: ModMaterialsList.MATERIALS_LIST) {
            material.registry();
        }

        for (TinkerToolCore tool: ModToolsList.TOOLS_LIST) {
            event.getRegistry().register(tool);
            TinkerRegistry.registerToolForgeCrafting(tool);
            TinkerDream.proxy.registerToolModel(tool);
        }

        for (ToolPart part: ModPartsList.PARTS_LIST) {
            event.getRegistry().register(part);
            TinkerRegistry.registerToolPart(part);
            TinkerDream.proxy.registerToolPartModel(part);
            TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), part));
        }
    }

    @SubscribeEvent
    public static void onPotionRegistry(RegistryEvent.Register<Potion> event) {
        for (Potion potion: ModPotionList.POTION_LIST) {
            event.getRegistry().register(potion);
        }
    }
}
