package oldmoon.dustw.tinkerdream.util.fork;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;

/**
 * 链接 https://github.com/351768593/MinecraftTinkersThings/blob/indev1122/src/main/java/firok/tiths/util/InnerActions.java
 * @author Fork
 */
public class InnerActions {
    private InnerActions(){}

    /**
     * 因为{@link Entity#getForward}是客户端专有方法, 所以做了一个封装方法
     */
    public static Vec3d getEntityForward(Entity entity)
    {
        if(entity == null) {
            return new Vec3d(0,0,0);
        } else {
            return Vec3d.fromPitchYaw(entity.rotationPitch, entity.rotationYaw);
        }
    }
}
