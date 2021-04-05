package oldmoon.dustw.tinkerdream.util.fork;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

/**
 * 链接 https://github.com/351768593/MinecraftTinkersThings/blob/indev1122/src/main/java/firok/tiths/util/Ranges.java
 * @author Fork
 */
public class Ranges {
    private Ranges(){}

    public static BlockPos[] neighbour(BlockPos center)
    {
        return new BlockPos[]{
                center.up(),
                center.down(),
                center.east(),
                center.west(),
                center.south(),
                center.north(),
        };
    }

    public static AxisAlignedBB neighbour(Entity entity, double distance)
    {
        return new AxisAlignedBB(
                entity.posX-distance,entity.posY-distance,entity.posZ-distance,
                entity.posX+distance,entity.posY+distance,entity.posZ+distance
        );
    }
    public static AxisAlignedBB neighbour(BlockPos center, double distance)
    {
        return new AxisAlignedBB(
                center.getX()-distance,center.getY()-distance,center.getZ()-distance,
                center.getX()+distance,center.getY()+distance,center.getZ()+distance
        );
    }
    public static AxisAlignedBB neighbour(Vec3d center, double distance)
    {
        return new AxisAlignedBB(
                center.x-distance,center.y-distance,center.z-distance,
                center.x+distance,center.y+distance,center.z+distance
        );
    }
}
