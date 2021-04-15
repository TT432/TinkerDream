package oldmoon.dustw.tinkerdream.tools;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.materials.stats.StatsTypes;
import oldmoon.dustw.tinkerdream.parts.ModPartsList;
import oldmoon.dustw.tinkerdream.util.Util;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tinkering.PartMaterialType;
import slimeknights.tconstruct.library.tools.TinkerToolCore;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTools;

import java.util.List;

/**
 * @author NmmOC7
 */
public class ToolDurabilityPackage extends TinkerToolCore {
    private static PartMaterialType partMaterialType = new PartMaterialType(ModPartsList.INTERACTION_CORE, StatsTypes.INTERACTION_CORE);
    public static final float DURABILITY_MODIFIER = 10f;

    public ToolDurabilityPackage() {
        super(  partMaterialType,
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate),
                PartMaterialType.extra(TinkerTools.largePlate)
        );

        this.addCategory(Category.NO_MELEE);
        this.setTranslationKey(TinkerDream.MOD_ID + ".durability_package");
        this.setRegistryName(TinkerDream.MOD_ID + ":durability_package");

        ModToolsList.TOOLS_LIST.add(this);
    }

    @Override
    protected ToolNBT buildTagData(List<Material> materials) {
        ToolNBT data = buildDefaultTag(materials);

        data.durability *= DURABILITY_MODIFIER;
        data.attack = 0;
        data.harvestLevel = 0;
        data.speed = 0;

        return data;
    }

    @Override
    public float damagePotential() {
        return 0;
    }

    @Override
    public double attackSpeed() {
        return 3;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(EnumHand.MAIN_HAND);
        int maxDamage = stack.getMaxDamage();

        if (stack.getItem() instanceof TinkerToolCore) {
            if (ToolHelper.isBroken(stack) && !(stack.getItem() instanceof ToolDurabilityPackage)) {
                ItemStack[] durabilityPackages = Util.getItemStackFromInventory(playerIn, this);
                int durabilityCounter = 0;

                for (ItemStack tool: durabilityPackages) {
                    if (!ToolConfig.unbreakableDurabilityPackage) {
                        if (Util.isUnbreakable(tool) && !worldIn.isRemote) {
                            playerIn.sendMessage(new TextComponentTranslation("message.tinkerdream.durability_package.unbreakerror"));
                        }
                    }
                    else {
                        if (Util.isUnbreakable(tool)) {
                            durabilityCounter = maxDamage;
                            break;
                        }
                    }

                    if (!ToolHelper.isBroken(tool)) {
                        int trueDamage = tool.getMaxDamage() - tool.getItemDamage();

                        if (trueDamage <= maxDamage - durabilityCounter) {
                            durabilityCounter += trueDamage;
                            ToolHelper.breakTool(tool, playerIn);
                        }
                        else {
                            tool.setItemDamage(tool.getItemDamage() + (maxDamage - durabilityCounter));
                            durabilityCounter = maxDamage;
                        }
                    }

                    if (durabilityCounter >= maxDamage) {
                        break;
                    }
                }

                ToolHelper.unbreakTool(stack);
                stack.setItemDamage(maxDamage - durabilityCounter);
            }
        }

        return new ActionResult<>(EnumActionResult.PASS, stack);
    }
}
