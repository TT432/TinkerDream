package oldmoon.dustw.tinkerdream.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * @author NmmOC7
 */
public class EntitySeekerArrow extends ModEntityArrowBase {

    private static final DataParameter<Integer> TARGET = EntityDataManager.createKey(EntitySeekerArrow.class, DataSerializers.VARINT);

    private static final double SEEK_DISTANCE = 5.0;
    private static final double SEEK_FACTOR = 0.2;
    private static final double SEEK_ANGLE = Math.PI / 6.0;
    private static final double SEEK_THRESHOLD = 0.5;

    public EntitySeekerArrow(World world) {
        super(world);
    }

    public EntitySeekerArrow(World world, EntityLivingBase shooter) {
        super(world, shooter);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(TARGET, -1);
    }

    @Override
    public void onUpdate() {
        if (isThisArrowFlying()) {
            if (!world.isRemote) {
                updateTarget();
            }

            if (world.isRemote && !inGround) {
                for (int i = 0; i < 4; ++i) {
                    this.world.spawnParticle(EnumParticleTypes.SPELL_WITCH, this.posX + this.motionX * (double) i / 4.0D, this.posY + this.motionY * (double) i / 4.0D, this.posZ + this.motionZ * (double) i / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
                }
            }

            Entity target = getTarget();
            if (target != null) {
                Vec3d targetVec = getVectorToTarget(target).scale(SEEK_FACTOR);
                Vec3d courseVec = getMotionVec();

                // vector lengths
                double courseLen = courseVec.length();
                double targetLen = targetVec.length();
                double totalLen = Math.sqrt(courseLen*courseLen + targetLen*targetLen);

                double dotProduct = courseVec.dotProduct(targetVec) / (courseLen * targetLen); // cosine similarity

                if (dotProduct > SEEK_THRESHOLD) {

                    // add vector to target, scale to match current velocity
                    Vec3d newMotion = courseVec.scale(courseLen / totalLen).add(targetVec.scale(targetLen / totalLen));

                    this.motionX = newMotion.x;
                    this.motionY = newMotion.y;
                    this.motionZ = newMotion.z;

                    // compensate (mostly) for gravity
                    this.motionY += 0.045F;

                } else if (!world.isRemote) {
                    // too inaccurate for our intended target, give up on it
                    setTarget(null);
                }
            }
        }

        super.onUpdate();
    }

    private void updateTarget() {
        Entity target = getTarget();

        if (target != null && target.isDead) {
            setTarget(target = null);
        }

        if (target == null) {
            AxisAlignedBB positionBox = new AxisAlignedBB(posX, posY, posZ, posX, posY, posZ);
            AxisAlignedBB targetBox = positionBox;

            // add two possible courses to our selection box
            Vec3d courseVec = getMotionVec().scale(SEEK_DISTANCE).rotateYaw((float) SEEK_ANGLE);
            targetBox = targetBox.union(positionBox.offset(courseVec));

            courseVec = getMotionVec().scale(SEEK_DISTANCE).rotateYaw((float) -SEEK_ANGLE);
            targetBox = targetBox.union(positionBox.offset(courseVec));

            targetBox = targetBox.grow(0, SEEK_DISTANCE * 0.5, 0);

            double closestDot = -1.0;
            Entity closestTarget = null;

            for (EntityLivingBase living : this.world.getEntitiesWithinAABB(EntityLivingBase.class, targetBox)) {

                if (living instanceof EntityPlayer) {
                    continue;
                }

                Vec3d motionVec = getMotionVec().normalize();
                Vec3d targetVec = getVectorToTarget(living).normalize();

                double dot = motionVec.dotProduct(targetVec);

                if (dot > Math.max(closestDot, SEEK_THRESHOLD)) {
                    closestDot = dot;
                    closestTarget = living;
                }
            }

            if (closestTarget != null) {
                setTarget(closestTarget);
            }
        }
    }

    private Vec3d getMotionVec() {
        return new Vec3d(this.motionX, this.motionY, this.motionZ);
    }

    private Vec3d getVectorToTarget(Entity target) {
        return new Vec3d(target.posX - this.posX, (target.posY + (double) target.getEyeHeight()) - this.posY, target.posZ - this.posZ);
    }

    @Nullable
    private Entity getTarget() {
        return world.getEntityByID(dataManager.get(TARGET));
    }

    private void setTarget(@Nullable Entity e) {
        dataManager.set(TARGET, e == null ? -1 : e.getEntityId());
    }

    private boolean isThisArrowFlying() {
        return !inGround && motionX * motionX + motionY * motionY + motionZ * motionZ > 1.0;
    }
}

