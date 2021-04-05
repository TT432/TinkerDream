package oldmoon.dustw.tinkerdream.util.fork;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

/**
 * 所有的行为封装
 *
 * 链接 https://github.com/351768593/MinecraftTinkersThings/blob/indev1122/src/main/java/firok/tiths/util/Actions.java
 * @author Fork
 */
public final class Actions {
    private Actions(){}

    /**
     * 用来求若干点到直线距离
     * @param p1 直线上一点坐标
     * @param facing 方向向量
     * @param points 若干需要求的点
     * @return 直线导致这些点的距离
     */
    public static double[] distanceToLine(Vec3d p1,Vec3d facing,Vec3d... points)
    {
        if(points==null) {
            return new double[0];
        }
        double[] ret=new double[points.length];

        // 第2个点
        Vec3d p2=p1.add(facing);

        // 底边长度
        double d12=p1.distanceTo(p2);
        for(int i=0;i<points.length;i++)
        {
            // 海伦公式 Heron's formula
            // https://baike.baidu.com/item/%E6%B5%B7%E4%BC%A6%E5%85%AC%E5%BC%8F
            Vec3d d3=points[i];

            // 求出剩下两边长
            double d32=d3.distanceTo(p2);
            double d31=d3.distanceTo(p1);

            // 半周长
            double p=(d12+d32+d31)/2;
            double pp1=Math.abs(p-d12),pp2=Math.abs(p-d32),pp3=Math.abs(p-d31);

            // 三角形面积
            double area= MathHelper.sqrt( p*pp1*pp2*pp3 );

            // 三角形面积=底×高÷2 => 三角形面积×2÷底=高
            ret[i] = area * 2 / d12;

//			System.out.printf("d12[%f],d31[%f],d32[%f],p[%f],area[%f],ret[%f],pp1~3[%f,%f,%f]\n",d12,d31,d32,p,area,ret[i],pp1,pp2,pp3);
        }

        // "System.out.println("到直线距离"+Arrays.toString(ret));"

        return ret;
    }
}
