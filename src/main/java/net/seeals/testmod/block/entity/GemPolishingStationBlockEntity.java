package net.seeals.testmod.block.entity;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.seeals.testmod.item.ModItems;
import net.seeals.testmod.screen.GemPolishingScreenHandler;
import org.jetbrains.annotations.Nullable;

//BLOCK ENTITY!!! Check ctrl + h for BlockEntity for more info!

public class GemPolishingStationBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);                //Making the default GUI empty with 2 slots

    private static final int INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    //Specifying progress for our block
    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 72;

    public GemPolishingStationBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.GEM_POLISHING_STATION_BLOCK_ENTITY, pos, state);
        this.propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> GemPolishingStationBlockEntity.this.progress;
                    case 1 -> GemPolishingStationBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> GemPolishingStationBlockEntity.this.progress = value;
                    case 1 -> GemPolishingStationBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {      //write data of opening screen from server to data and data to server
        buf.writeBlockPos(this.pos);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Gem Polishing Station");                               //Should change to Text.translatable and use the lang file instead.
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new GemPolishingScreenHandler(syncId, playerInventory, this, this.propertyDelegate );
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
    //Write nbt to inventory. Basically saving data
    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putInt("gem_polishing_station.progress", progress);
    }
    //Then this one is loading the data
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, inventory);
        progress = nbt.getInt("gem_polishing_station.progress");
    }

    //Most logic goes here
    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (isOutputSlotEmptyOrRecieveable()) {
            if (this.hasRecipe()) {
                this.increaseCraftProgress();
                markDirty(world, pos, state);

                if (hasCraftingFinished()) {
                    this.craftItem();
                    this.resetProgress();
                }
            } else {
                this.resetProgress();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }

    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        this.removeStack(INPUT_SLOT, 1);
        ItemStack result = new ItemStack(ModItems.SAPPHIRE);

        this.setStack(OUTPUT_SLOT, new ItemStack(result.getItem(), getStack(OUTPUT_SLOT).getCount() + result.getCount()));
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        ItemStack result = new ItemStack(ModItems.SAPPHIRE);
        boolean hasInput = getStack(INPUT_SLOT).getItem() == ModItems.RAW_SAPPHIRE;

        return hasInput && canInsertAmountIntoOutputSlot(result) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.getStack(OUTPUT_SLOT).getItem() == item || this.getStack(OUTPUT_SLOT).isEmpty();
    }

    private boolean canInsertAmountIntoOutputSlot(ItemStack result) {
        return this.getStack(OUTPUT_SLOT).getCount() + result.getCount() <= getStack(OUTPUT_SLOT).getMaxCount();
    }

    private boolean isOutputSlotEmptyOrRecieveable() {
        return this.getStack(OUTPUT_SLOT).isEmpty() || this.getStack(OUTPUT_SLOT).getCount() < this.getStack(OUTPUT_SLOT). getMaxCount();
    }
}
