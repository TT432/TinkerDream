package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.util.StatsTypes;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.Tags;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.library.utils.TooltipBuilder;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author NmmOC7
 */
public class TestTool extends TinkerToolCore {
    private static PartMaterialType partMaterialType = new PartMaterialType(ModPartsList.TEST_PART_A, StatsTypes.TEST_A);

    public static final float DURABILITY_MODIFIER = 1.1f;

    public TestTool() {
        super(partMaterialType,
                PartMaterialType.head(TinkerTools.swordBlade),
                PartMaterialType.handle(TinkerTools.toolRod)
        );

        this.addCategory(Category.WEAPON);
        this.setTranslationKey(TinkerDream.MOD_ID + ".test_tool");
        this.setRegistryName(TinkerDream.MOD_ID + ":test_tool");

        ModToolsList.TOOLS_LIST.add(this);
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        ToolNBT data = buildDefaultTag(materials);

        data.attack += 0.5f;
        data.durability *= DURABILITY_MODIFIER;

        return data;
    }

    @Override
    public float damagePotential() {
        return 1.1f;
    }

    @Override
    public double attackSpeed() {
        return 0.9f;
    }

    @Override
    public float getRepairModifierForPart(int index) {
        return DURABILITY_MODIFIER;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        return 200;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.isRiding()) {

        }
        else {
            if (!worldIn.isRemote) {
                EntityHorse entityHorse = new EntityHorse(worldIn);
                ItemStack stack = playerIn.getHeldItem(EnumHand.OFF_HAND);

                if (stack.getItem() instanceof HorseMedal) {
                    NBTTagCompound nbt = stack.serializeNBT();

                    if (!nbt.hasKey(TinkerDream.MOD_ID)) {
                        ((HorseMedal) stack.getItem()).initHorseMedal(worldIn, playerIn);
                    }

                    entityHorse.readEntityFromNBT(nbt.getCompoundTag("horse"));
                    worldIn.spawnEntity(entityHorse);
                    playerIn.startRiding(entityHorse);

                    playerIn.getCooldownTracker().setCooldown(stack.getItem(), 100);
                }

            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }
}
