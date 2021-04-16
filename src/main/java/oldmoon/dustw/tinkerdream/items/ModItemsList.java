package oldmoon.dustw.tinkerdream.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import oldmoon.dustw.tinkerdream.TinkerDream;
import oldmoon.dustw.tinkerdream.entity.EntityMagicMissile;
import oldmoon.dustw.tinkerdream.entity.EntitySeekerArrow;
import slimeknights.mantle.client.CreativeTab;

/**
 * @author DustW, NmmOC7
 */
public class ModItemsList {
    public static final ModItemsBase ICON = new ModItemsBase("icon");

    public static final CreativeTab MOD_TAB = new CreativeTab(TinkerDream.MOD_ID + ".tab", new ItemStack(ICON));

    public static final ModItemsBase RIGHT_CLICK_TEST = new ModItemsBase("right_test"){
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            EntityArrow arrow = new EntitySeekerArrow(worldIn, playerIn);

            if (!worldIn.isRemote) {
                arrow.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead, 1, 1, 0);
                worldIn.spawnEntity(arrow);
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(EnumHand.MAIN_HAND));
        }
    };

    public static final ModItemsBase RIGHT_CLICK_TEST_1 = new ModItemsBase("right_test_1"){
        @Override
        public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
            if (!worldIn.isRemote) {
                EntityMagicMissile magicMissile = new EntityMagicMissile(worldIn, playerIn);

                TinkerDream.logger.info("log!");

                magicMissile.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYawHead, 0, 1, 0);
                worldIn.spawnEntity(magicMissile);
            }

            return new ActionResult<>(EnumActionResult.SUCCESS, playerIn.getHeldItem(EnumHand.MAIN_HAND));
        }
    };
}
