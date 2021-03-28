package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.stats.HorseMedalCoreStats;
import oldmoon.dustw.tinkerdream.stats.ModStatsList;
import oldmoon.dustw.tinkerdream.util.StatsTypes;
import oldmoon.dustw.tinkerdream.util.Util;
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
        this.setTranslationKey(TinkerDream.MOD_ID + ".horse_medal");
        this.setRegistryName(TinkerDream.MOD_ID + ":horse_medal");

        ModToolsList.TOOLS_LIST.add(this);
    }

    public void initHorseMedal(World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(EnumHand.OFF_HAND);
            HorseMedalCoreStats horseMedalCoreStats = (HorseMedalCoreStats) Util.getStatsFromTool(stack, StatsTypes.HORSE_MEDAL_CORE);

            if (stack.serializeNBT().hasKey(TinkerDream.MOD_ID) && !(stack.getItem() instanceof HorseMedal)) {
                return;
            }

            EntityHorse entityHorse = new EntityHorse(worldIn);

            entityHorse.setJumpPower(1);
            entityHorse.setAIMoveSpeed(horseMedalCoreStats.getHorseSpeed());
            entityHorse.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(horseMedalCoreStats.getHorseHealth());
            entityHorse.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(horseMedalCoreStats.getHorseArmor());

            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagCompound modIdNbt = new NBTTagCompound();
            NBTTagCompound horseNbt = new NBTTagCompound();
            entityHorse.writeEntityToNBT(horseNbt);
            modIdNbt.setTag("horse", horseNbt);
            modIdNbt.setBoolean("once", true);
            nbt.setTag(TinkerDream.MOD_ID, modIdNbt);

            NBTTagCompound nbtTool = stack.serializeNBT();
            if (!nbtTool.hasKey("tag")) {
                nbtTool.setTag("tag", nbt);
            }
            else {
                nbtTool.getCompoundTag("tag").merge(nbt);
            }

            stack.deserializeNBT(nbtTool);

            entityHorse.setDead();
        }
    }

    public void spawnHorse(World worldIn, EntityPlayer playerIn) {
        if (!worldIn.isRemote) {
            ItemStack stack = playerIn.getHeldItem(EnumHand.OFF_HAND);

            if (playerIn.getCooldownTracker().hasCooldown(stack.getItem())) { return; }

            NBTTagCompound nbt = stack.serializeNBT();

            if (!Util.isExistForNbt(nbt, "tag", TinkerDream.MOD_ID, "once")) {
                ((HorseMedal) stack.getItem()).initHorseMedal(worldIn, playerIn);
            }

            stack = playerIn.getHeldItem(EnumHand.OFF_HAND);
            nbt = stack.serializeNBT();

            EntityHorse entityHorse = new EntityHorse(worldIn);
            entityHorse.readEntityFromNBT(nbt.getCompoundTag(TinkerDream.MOD_ID).getCompoundTag("horse"));

            entityHorse.setHorseSaddled(true);
            entityHorse.setPosition(playerIn.posX, playerIn.posY, playerIn.posZ);
            entityHorse.setTamedBy(playerIn);

            worldIn.spawnEntity(entityHorse);
            playerIn.startRiding(entityHorse);

            playerIn.getCooldownTracker().setCooldown(stack.getItem(), 100);
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
