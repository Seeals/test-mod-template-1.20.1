package net.seeals.testmod.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TestSoundItem extends Item {
    public TestSoundItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            System.out.println("Playing test sound for player at: " + player.getX() + ", " + player.getY() + ", " + player.getZ());

            // Test with different sound event and category
            player.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.MASTER, 1f, 1f);

            // Additional debug output
            System.out.println("Sound event: ENTITY_EXPERIENCE_ORB_PICKUP, Category: MASTER");
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, player.getStackInHand(hand));
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        System.out.println("useOnBlock method called.");

        if (!context.getWorld().isClient) {
            System.out.println("Running on server side.");

            PlayerEntity player = context.getPlayer();

            if (player == null) {
                System.out.println("Player is null!");
                return ActionResult.FAIL;
            }

            System.out.println("Playing sound for player at: " + player.getX() + ", " + player.getY() + ", " + player.getZ());

            // Play sound directly for the player
            player.playSound(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME, SoundCategory.PLAYERS, 1f, 1f);

            // Additional debug output
            System.out.println("Sound event: BLOCK_AMETHYST_BLOCK_CHIME, Category: PLAYERS");

            // Additional debugging: play sound using world.playSound with player coordinates
            player.getWorld().playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME,
                    SoundCategory.PLAYERS,
                    1f,
                    1f
            );
        }

        return ActionResult.SUCCESS;
    }
}