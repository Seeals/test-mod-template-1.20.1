package net.seeals.testmod.block.custom;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.seeals.testmod.block.entity.GemPolishingStationBlockEntity;
import net.seeals.testmod.block.entity.ModBlockEntities;
import org.jetbrains.annotations.Nullable;

public class GemPolishingStationBlock extends BlockWithEntity implements BlockEntityProvider {
    private static final VoxelShape SHAPE = Block.createCuboidShape(0,0,0,16,12,16);            //VoxelShape is basically the block outline (block hitbox)

    public GemPolishingStationBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {                    //this makes the block visible
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new GemPolishingStationBlockEntity(pos, state);
    }

    //This one will probably be the same in most cases. It's just telling the game to drop all the blocks and contents when it's destroyed.
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof GemPolishingStationBlockEntity) {
                ItemScatterer.spawn(world, pos, (GemPolishingStationBlockEntity)blockEntity);
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }
    //This onUse method too, will probably be the same. Only change small values and stuff, its just telling the game to open the GUI screen inventory for the block.
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((GemPolishingStationBlockEntity) world.getBlockEntity(pos));
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.GEM_POLISHING_STATION_BLOCK_ENTITY,
                (world1, pos, state1, blockEntity) -> blockEntity.tick(world, pos, state));
    }
}
