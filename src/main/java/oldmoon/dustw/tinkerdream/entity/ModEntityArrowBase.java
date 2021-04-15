package oldmoon.dustw.tinkerdream.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IThrowableEntity;

/**
 * @author NmmOC7
 */
public abstract class ModEntityArrowBase extends EntityArrow implements IThrowableEntity {

    public ModEntityArrowBase(World worldIn) {
        super(worldIn);
    }

    public ModEntityArrowBase(World worldIn, EntityLivingBase shooter) {
        super(worldIn, shooter);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public Entity getThrower() {
        return this.shootingEntity;
    }

    @Override
    public void setThrower(Entity entity) {
        this.shootingEntity = entity;
    }
}

