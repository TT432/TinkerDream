package oldmoon.dustw.tinkerdream.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.client.book.ModToolsTransformer;
import oldmoon.dustw.tinkerdream.entity.EntityMagicMissile;
import oldmoon.dustw.tinkerdream.entity.EntitySeekerArrow;
import oldmoon.dustw.tinkerdream.entity.RenderDefaultArrow;
import oldmoon.dustw.tinkerdream.items.ModItemsList;
import oldmoon.dustw.tinkerdream.tools.ModToolsList;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.TinkerRegistryClient;
import slimeknights.tconstruct.library.book.TinkerBook;
import slimeknights.tconstruct.library.client.ToolBuildGuiInfo;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

/**
 * @author DustW
 */
@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy{
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);

        renderInit();
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);

        TinkerBook.INSTANCE.addTransformer(new ModToolsTransformer(new FileRepository("tconstruct:book")));
    }

    @Override
    public void registerToolPartModel(ToolPart part) {
        ModelRegisterUtil.registerPartModel(part);
    }

    @Override
    public void registerToolModel(ToolCore tc) {
        ModelRegisterUtil.registerToolModel(tc);
    }

    @Override
    public void initToolGuis() {
        ToolBuildGuiInfo lanceInfo = new ToolBuildGuiInfo(ModToolsList.LANCE);
        lanceInfo.addSlotPosition(32 + 20, 42 - 20);
        lanceInfo.addSlotPosition(32 - 19, 42 + 1);
        lanceInfo.addSlotPosition(32 + 1, 42 - 1);
        lanceInfo.addSlotPosition(32 - 21, 42 + 21);
        TinkerRegistryClient.addToolBuilding(lanceInfo);

        ToolBuildGuiInfo durabilityPackageInfo = new ToolBuildGuiInfo(ModToolsList.DURABILITY_PACKAGE);
        durabilityPackageInfo.addSlotPosition(32, 42);
        durabilityPackageInfo.addSlotPosition(32 + 20, 42);
        durabilityPackageInfo.addSlotPosition(32, 42 - 20);
        durabilityPackageInfo.addSlotPosition(32 - 20, 42);
        durabilityPackageInfo.addSlotPosition(32, 42 + 20);
        TinkerRegistryClient.addToolBuilding(durabilityPackageInfo);
    }

    public static void renderInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntitySeekerArrow.class, RenderDefaultArrow::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMagicMissile.class,
                manager -> new RenderSnowball<>(manager, ModItemsList.ICON, Minecraft.getMinecraft().getRenderItem()));
    }
}
