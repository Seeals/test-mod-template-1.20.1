package net.seeals.testmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.seeals.testmod.sound.ModSounds;
import net.seeals.testmod.util.Modtags;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// Custom advance item!!

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                BlockState state = context.getWorld().getBlockState(positionClicked.down(i));

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.down(i), player, state.getBlock());
                    player.playSound(ModSounds.METAL_DETECTOR_FOUND_ORE,SoundCategory.BLOCKS, 5f, 1f); //in tutorial, he used context.getWorld().playSound( null, positionClicked, ModSounds.METAL_DETECTOR_FOUND_ORE, SoundCategory.BLOCKS, 1f, 1f); but this use null (its an exception means that everything around it will hear the sound. if you input "player" everything EXCEPTS the player will hear the sound).

                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(Text.literal("Nothing found yet!"));
                player.playSound(SoundEvents.ENTITY_VILLAGER_NO, SoundCategory.BLOCKS,1f, 1f);
            }
        }

        return ActionResult.SUCCESS;
    }

    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("Found " + block.asItem().getName().getString() + " at " + "(" + blockPos.getX() + "," + blockPos.getY() + "," + blockPos.getZ() + ")"), false);
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isIn(Modtags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }
    //adding tooltips
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.testmod.metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
