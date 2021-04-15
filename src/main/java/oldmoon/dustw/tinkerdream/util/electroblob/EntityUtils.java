package oldmoon.dustw.tinkerdream.util.electroblob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

/**
 * @author reallyTouch
 */
public class EntityUtils {
    public static boolean isLiving(Entity entity){
        return entity instanceof EntityLivingBase && !(entity instanceof EntityArmorStand);
    }

    @Nullable
    public static Entity getEntityByUUID(World world, @Nullable UUID id){

        if(id == null) {
            return null; // It would return null eventually but there's no point even looking
        }

        for(Entity entity : world.loadedEntityList){
            // This is a perfect example of where you need to use .equals() and not ==. For most applications,
            // this was unnoticeable until world reload because the UUID instance or entity instance is stored.
            // Fixed now though.
            if(entity != null && entity.getUniqueID() != null && entity.getUniqueID().equals(id)){
                return entity;
            }
        }
        return null;
    }
}
