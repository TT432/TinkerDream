package oldmoon.dustw.tinkerdream.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oldmoon.dustw.tinkerdream.tools.ModToolsList;
import slimeknights.tconstruct.common.ModelRegisterUtil;
import slimeknights.tconstruct.library.TinkerRegistryClient;
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
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
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
        ToolBuildGuiInfo testToolInfo = new ToolBuildGuiInfo(ModToolsList.LANCE);
        testToolInfo.addSlotPosition(32 + 20, 42 - 20);
        testToolInfo.addSlotPosition(32 - 19, 42 + 1);
        testToolInfo.addSlotPosition(32 + 1, 42 - 1);
        testToolInfo.addSlotPosition(32 - 21, 42 + 21);
        TinkerRegistryClient.addToolBuilding(testToolInfo);
    }
    
}
