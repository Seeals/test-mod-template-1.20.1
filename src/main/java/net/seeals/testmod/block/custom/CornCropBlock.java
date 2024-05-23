package net.seeals.testmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.seeals.testmod.block.ModBlocks;
import net.seeals.testmod.item.ModItems;

public class CornCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 7;
    public static final int SECOND_STAGE_MAX_AGE = 1;
    private static final VoxelShape[] AGE_TO_SHAPE = new VoxelShape[]
            {Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)};


    public static final IntProperty AGE = IntProperty.of("age", 0, 8);


    public CornCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return AGE_TO_SHAPE[this.getAge(state)];        //returns the shape of the crop based on its age.
    }


    @Override
    protected ItemConvertible getSeedsItem() {
        return(ModItems.CORN_SEEDS);                    //returns the item used to plant the crop
    }

    @Override
    public void applyGrowth(World world, BlockPos pos, BlockState state) {          // increases the age of the crop, checking if it should transition to the second block stage if it has reached FIRST_STAGE_MAX_AGE and there's air above.
         int nextAge = this.getAge(state) + this.getGrowthAmount(world);
         int maxAge = this.getMaxAge();
         if(nextAge > maxAge) {
             nextAge = maxAge;
         }

         if(this.getAge(state) == FIRST_STAGE_MAX_AGE && world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {     //and then make sure the block below is kept at first stage max age
             world.setBlockState(pos.up(1), this.withAge(nextAge), 2);
         } else {
             world.setBlockState(pos, this.withAge(nextAge - 1), 2);
         }

    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9 ) {
            int currentAge = this.getAge(state);
            if (currentAge < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0f / f) + 1) == 0) {
                    if (currentAge == FIRST_STAGE_MAX_AGE) {
                        if (world.getBlockState(pos.up(1)).isOf(Blocks.AIR)) {
                            world.setBlockState(pos.up(1), this.withAge(currentAge + 1), 2);
                        }
                    } else {
                        world.setBlockState(pos, this.withAge(currentAge + 1), 2);
                    }
                }
            }
        }
    }
    // this is the can place for the crop not the player! It just tells the world that it can place the 2nd level of corn block on top of the first block
    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos) || (world.getBlockState(pos.down(1)).isOf(this) &&
                world.getBlockState(pos.down(1)).get(AGE) == FIRST_STAGE_MAX_AGE);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        // Preventing players from bone mealing fully grown crops
        //this took too long omfg
        ItemStack itemStack = player.getStackInHand(hand);
        BlockPos upperPos = pos.up();
        BlockState upperState = world.getBlockState(upperPos);
        if (itemStack.getItem() == Items.BONE_MEAL && upperState.isOf(ModBlocks.CORN_CROP)) {
            int currentAge = this.getAge(state);
            if (currentAge >= FIRST_STAGE_MAX_AGE) {
                if (!world.isClient) {
                    player.swingHand(hand, true);
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(0); // Simulate bone meal usage without applying effects
                    }
                }
                return ActionResult.CONSUME;
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }



    @Override
    //Breaks both the block above and below when the crop block is broken.
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(AGE) > FIRST_STAGE_MAX_AGE) {
            BlockPos belowPos = pos.down();
            BlockState belowState = world.getBlockState(belowPos);
            if (belowState.isOf(this) && belowState.get(AGE) == FIRST_STAGE_MAX_AGE) {
                world.breakBlock(belowPos,true, player);
            }
        }
        super.onBreak(world, pos, state, player);
    }

    @Override
    public int getMaxAge() {
        return FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected IntProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
