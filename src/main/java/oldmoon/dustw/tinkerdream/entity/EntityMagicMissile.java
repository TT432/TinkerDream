package oldmoon.dustw.tinkerdream.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import oldmoon.dustw.tinkerdream.util.DreamEntityUtil;
import oldmoon.dustw.tinkerdream.util.Util;
import oldmoon.dustw.tinkerdream.util.electroblob.AllyDesignationSystem;
import oldmoon.dustw.tinkerdream.util.fork.EntityFinders;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.client.particle.Particles;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.List;

/**
 * @author NmmOC7
 */
public class EntityMagicMissile extends EntityThrowable {
    public static final int SEEKING_TIME = 15;

    double seekerDistance = 0;
    int seekerStrength = 0;

    private EntityMagicMissile(World worldIn) {
        super(worldIn);
    }

    public EntityMagicMissile(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);

        this.setNoGravity(true);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if(getLifetime() >=0 && this.ticksExisted > getLifetime()){
            this.setDead();
        }

        // Seeking
        if(getSeekerStrength() > 0 && this.thrower instanceof EntityPlayer){
            Vec3d velocity = new Vec3d(motionX, motionY, motionZ);

            List<? extends Entity> entities = EntityFinders.nearby(this, this.seekerDistance);
            Entity hit = DreamEntityUtil.getNearestEntityLiving((EntityPlayer) this.thrower, this, entities.toArray(new Entity[]{}));

            if(hit != null){
                if(AllyDesignationSystem.isValidTarget(this.getThrower(), hit)){
                    Vec3d targetVec = getVectorToTarget(hit).scale(0.2);
                    Vec3d courseVec = getMotionVec();

                    double courseLen = courseVec.length();
                    double targetLen = targetVec.length();
                    double totalLen = Util.pythagorasTheorem(false, courseLen, targetLen);

                    Vec3d newMotion = courseVec.scale(courseLen / totalLen).add(targetVec.scale(targetLen / totalLen)).scale(0.6);

                    this.motionX = newMotion.x;
                    this.motionY = newMotion.y;
                    this.motionZ = newMotion.z;
                }
            }
        }

        if(world.isRemote){
            for(int i=0; i<5; i++){
                double dx = (rand.nextDouble() - 0.5) * width;
                double dy = (rand.nextDouble() - 0.5) * height + this.height/2;
                double dz = (rand.nextDouble() - 0.5) * width;
                double v = 0.06;

                Vec3d particlesPosVec = this.getPositionVector().add(dx - this.motionX / 2, dy, dz - this.motionZ / 2);
                Vec3d particlesSpeedVec = new Vec3d(-v * dx, -v * dy, -v * dz).scale(width * 2);

                if(ticksExisted > 1){
                    dx = (rand.nextDouble() - 0.5) * width;
                    dy = (rand.nextDouble() - 0.5) * height + this.height / 2;
                    dz = (rand.nextDouble() - 0.5) * width;

                    particlesPosVec = this.getPositionVector().add(dx - this.motionX, dy, dz - this.motionZ);
                    particlesSpeedVec = new Vec3d(-v * dx, -v * dy, -v * dz).scale(width*2);
                }

                TConstruct.proxy.spawnParticle(Particles.FRYPAN_ATTACK, world, particlesPosVec.x, particlesPosVec.y, particlesPosVec.z, particlesSpeedVec.x, particlesSpeedVec.y, particlesSpeedVec.z);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if(!world.isRemote){
            Entity entityHit = result.entityHit;

            if(entityHit != null){
                float damage = 0;

                if (this.thrower instanceof EntityPlayer) {
                    ItemStack tool = thrower.getHeldItem(EnumHand.MAIN_HAND);

                    if (tool.getItem() instanceof TinkerToolCore) {
                        damage = ToolHelper.getActualDamage(tool, this.thrower);
                    }
                }

                entityHit.attackEntityFrom(DamageSource.MAGIC, damage);
            }

            this.playSound(SoundEvent.REGISTRY.getObject(new ResourceLocation("minecraft", "block.snow.break")), 2, 0.8f + rand.nextFloat() * 0.3f);

            this.setDead();
        }
    }

    public double getSeekerDistance() {
        return seekerDistance;
    }

    public void setSeekerDistance(double seekerDistance) {
        this.seekerDistance = seekerDistance;
    }

    public int getSeekerStrength() {
        return seekerStrength;
    }

    public void setSeekerStrength(int seekerStrength) {
        this.seekerStrength = seekerStrength;
    }

    public int getLifetime(){
        return 30;
    }

    @Override
    public boolean hasNoGravity(){
        return true;
    }

    @Override
    public boolean canRenderOnFire(){
        return false;
    }

    private Vec3d getMotionVec() {
        return new Vec3d(this.motionX, this.motionY, this.motionZ);
    }

    private Vec3d getVectorToTarget(Entity target) {
        return new Vec3d(target.posX - this.posX, (target.posY + (double) target.getEyeHeight()) - this.posY, target.posZ - this.posZ);
    }
}
