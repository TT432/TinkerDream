package oldmoon.dustw.tinkerdream.tools;

import net.minecraftforge.common.config.Config;
import oldmoon.dustw.tinkerdream.TinkerDream;

/**
 * @author NmmOC7
 */
@Config(modid = TinkerDream.MOD_ID)
public final class ToolConfig {
    @Config.Comment("If true, player can use lance when lance HAVE unbreakable!")
    @Config.Name("Use Unbreakable Lance")
    @Config.RequiresMcRestart
    public static boolean unbreakableLance = false;
}
