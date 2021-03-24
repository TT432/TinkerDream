package oldmoon.dustw.tinkerdream.handlers;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.items.ModItemsBase;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsBase;
import oldmoon.dustw.tinkerdream.materials.ModMaterialsList;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.tools.ModToolsList;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.Pattern;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;
import slimeknights.tconstruct.tools.TinkerTools;

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

        for (TinkerToolCore tool: ModToolsList.TOOLS_LIST) {
            event.getRegistry().register(tool);
            TinkerDream.proxy.registerToolModel(tool);
            TinkerRegistry.registerToolForgeCrafting(tool);
        }

        for (ToolPart part: ModPartsList.PARTS_LIST) {
            event.getRegistry().register(part);
            TinkerRegistry.registerToolPart(part);
            TinkerDream.proxy.registerToolPartModel(part);
            TinkerRegistry.registerStencilTableCrafting(Pattern.setTagForPart(new ItemStack(TinkerTools.pattern), part));

            for (ToolCore toolCore : TinkerRegistry.getTools())
            {
                for (PartMaterialType partMaterialType : toolCore.getRequiredComponents())
                {
                    if (partMaterialType.getPossibleParts().contains(part))
                    {
                        ItemStack stencil = new ItemStack(TinkerTools.pattern);
                        Pattern.setTagForPart(stencil, part);
                        TinkerRegistry.registerStencilTableCrafting(stencil);
                        return;
                    }
                }
            }
        }

        for (ModMaterialsBase material: ModMaterialsList.MATERIALS_LIST) {
            material.registry();
        }
    }
}
