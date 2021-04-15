package oldmoon.dustw.tinkerdream.util.electroblob;

import net.minecraft.entity.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.FakePlayer;

public class AllyDesignationSystem {
    public static boolean isValidTarget(Entity attacker, Entity target){

        // Owned entities inherit their owner's allies
        if(attacker instanceof IEntityOwnable && !isValidTarget(((IEntityOwnable)attacker).getOwner(), target)) {
            return false;
        }

        // Always return false if the target is null
        if(target == null) {
            return false;
        }

        // Always return true if the attacker is null - this must be after the target null check!
        if(attacker == null) {
            return true;
        }

        // Tests whether the target is the attacker
        if(target == attacker) {
            return false;
        }

        // I really shouldn't need to do this, but fake players seem to break stuff...
        if(target instanceof FakePlayer) {
            return false;
        }

        // Use a positive check for these rather than a negative check for monsters, because we only want mobs
        // that are definitely passive
        if((target.isCreatureType(EnumCreatureType.AMBIENT, false)
                || target.isCreatureType(EnumCreatureType.CREATURE, false)
                || target.isCreatureType(EnumCreatureType.WATER_CREATURE, false))){
            return false;
        }

        // Tests whether the target is a creature that was summoned by the attacker
//		if(target instanceof ISummonedCreature && ((ISummonedCreature)target).getCaster() == attacker){
//			return false;
//		}

        // Tests whether the target is a creature that was summoned/tamed (or is otherwise owned) by the attacker
        if(target instanceof IEntityOwnable && ((IEntityOwnable)target).getOwner() == attacker){
            return false;
        }

        return true;
    }
}
