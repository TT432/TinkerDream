package oldmoon.dustw.tinkerdream;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import oldmoon.dustw.tinkerdream.proxy.CommonProxy;
import org.apache.logging.log4j.Logger;

/**
 * @author DustW
 */
@Mod(
        modid = TinkerDream.MOD_ID,
        name = TinkerDream.MOD_NAME,
        version = TinkerDream.VERSION,
        dependencies =  "required-after:forge@[14.23.4.2745,);" +
        "required-after:mantle@[1.12-1.3.1,);" +
        "required-after:tconstruct@[1.12.2-2.13.0.171,);"
)
public class TinkerDream {

    public static final String MOD_ID = "tinkerdream";
    public static final String MOD_NAME = "TinkerDream";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @Mod.Instance(MOD_ID)
    public static TinkerDream INSTANCE;

    @SidedProxy(clientSide = "oldmoon.dustw.tinkerdream.proxy.ClientProxy", serverSide = "oldmoon.dustw.tinkerdream.proxy.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);

        proxy.initToolGuis();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
