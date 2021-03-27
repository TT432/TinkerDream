package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.stats.HorseMedalCoreStats;
import oldmoon.dustw.tinkerdream.stats.ModStatsList;
import oldmoon.dustw.tinkerdream.util.StatsTypes;
import slimeknights.tconstruct.library.materials.HandleMaterialStats;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

/**
 * @author NmmOC7
 */
public class HorseMedal extends TinkerToolCore {
    private static PartMaterialType partMaterialType = new PartMaterialType(ModPartsList.HORSE_MEDAL_CORE, StatsTypes.HORSE_MEDAL_CORE);

    int extraHealth;
    float extraSpeed;
    int extraArmor;

    public HorseMedal() {
        super(partMaterialType,
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate)
        );

        this.addCategory(Category.NO_MELEE);
        this.setTranslationKey(TinkerDream.MOD_ID + ".test_tool");
        this.setRegistryName(TinkerDream.MOD_ID + ":test_tool");

        ModToolsList.TOOLS_LIST.add(this);
    }

    public void initHorseMedal(World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(EnumHand.OFF_HAND);
            HorseMedalCoreStats horseMedalCoreStats = this.getMaterialForPartForGuiRendering(0).getStatsOrUnknown(StatsTypes.HORSE_MEDAL_CORE);

            if (stack.serializeNBT().hasKey(TinkerDream.MOD_ID) && !(stack.getItem() instanceof HorseMedal)) {
                return;
            }

            EntityHorse entityHorse = new EntityHorse(worldIn);

            entityHorse.setWorld(worldIn);
            entityHorse.setTamedBy(playerIn);
            entityHorse.setHorseSaddled(true);
            entityHorse.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            entityHorse.setJumpPower(1);
            entityHorse.setAIMoveSpeed(horseMedalCoreStats.getHorseSpeed());
            entityHorse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(horseMedalCoreStats.getHorseHealth());
            entityHorse.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(horseMedalCoreStats.getHorseArmor());

            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound modIdNbt = new NBTTagCompound();
            NBTTagCompound horseNbt = new NBTTagCompound();
            entityHorse.writeEntityToNBT(horseNbt);
            modIdNbt.setTag("horse", horseNbt);
            nbt.setTag(TinkerDream.MOD_ID, modIdNbt);

            stack.writeToNBT(nbt);
        }
    }



    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        return this.buildDefaultTag(materials);
    }

    @Override
    public float damagePotential() {
        return 0;
    }

    @Override
    public double attackSpeed() {
        return 4;
    }
}
