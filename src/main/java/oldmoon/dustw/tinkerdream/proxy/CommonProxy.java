package oldmoon.dustw.tinkerdream.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.tools.ToolPart;

/**
 * @author DustW
 */
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent event) { }
    public void init(FMLInitializationEvent event) { }
    public void postInit(FMLPostInitializationEvent event) { }

    public void registerToolPartModel(ToolPart part) { }
    public void registerToolModel(ToolCore tool) { }

    public void initToolGuis() { }
}
