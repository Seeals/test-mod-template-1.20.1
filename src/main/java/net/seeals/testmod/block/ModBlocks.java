package net.seeals.testmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.seeals.testmod.TestMod;
import net.seeals.testmod.block.custom.CornCropBlock;
import net.seeals.testmod.block.custom.SoundBlock;
import net.seeals.testmod.block.custom.TomatoCropBlock;

public class ModBlocks {

    //we add blocks here. Note that the FabricBlockSettings needs us to copy or create new type of blocks.
    // You could just pull a property from a block and edits it without making a new one (middle-click the IRON_BLOCK
    // to check the properties of each minecraft blocks
    // DON'T FORGET THE JSON FILES FOR BOTH BLOCKS AND ITEMS!!
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK).sounds(BlockSoundGroup.BASALT)));
    public static final Block SAPPHIRE_ORE = registerBlock("sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.STONE).strength(2f), UniformIntProvider.create(5, 10)));
    public static final Block DEEPSLATE_SAPPHIRE_ORE = registerBlock("deepslate_sapphire_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE).strength(2.3f), UniformIntProvider.create(5, 10)));

    //REMEMBER!! Creating new custom blocks doesnt use Block class but use your custom block's class instead!
    public static final Block SOUND_BLOCK = registerBlock("sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.WHITE_WOOL)));

    //Making misc blocks! Stairs, fence, buttons, doors, etc! (Make sure to check correct parameters for each blocks!)
    public static final Block SAPPHIRE_STAIRS = registerBlock("sapphire_stairs",
            new StairsBlock(ModBlocks.SAPPHIRE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)));
    public static final Block SAPPHIRE_SLAB = registerBlock("sapphire_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)));
    public static final Block SAPPHIRE_BUTTON = registerBlock("sapphire_button",
            new ButtonBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK), BlockSetType.IRON, 10, true));
    public static final Block SAPPHIRE_PRESSURE_PLATE = registerBlock("sapphire_pressure_plate",
            new PressurePlateBlock(PressurePlateBlock.ActivationRule.MOBS, FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK), BlockSetType.IRON));
    public static final Block SAPPHIRE_FENCE = registerBlock("sapphire_fence",
            new FenceBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)));
    public static final Block SAPPHIRE_FENCE_GATE = registerBlock("sapphire_fence_gate",
            new FenceGateBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK), WoodType.ACACIA));
    public static final Block SAPPHIRE_WALL = registerBlock("sapphire_wall",
            new WallBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK)));
    public static final Block SAPPHIRE_DOOR = registerBlock("sapphire_door",
            new DoorBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK).nonOpaque(), BlockSetType.STONE));
    public static final Block SAPPHIRE_TRAPDOOR = registerBlock("sapphire_trapdoor",
            new TrapdoorBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK).nonOpaque(), BlockSetType.STONE));
    //This one is for adding crop blocks! Here, we created the block by not using the registerBlock method because we don't want it to be a block item. Because we're planting down its seeds not the crop itself!
    public static final Block TOMATO_CROP = Registry.register(Registries.BLOCK, new Identifier(TestMod.MOD_ID, "tomato_crop"),
            new TomatoCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    public static final Block CORN_CROP = Registry.register(Registries.BLOCK, new Identifier(TestMod.MOD_ID, "corn_crop"),
            new CornCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));
    // FLowers! Also, things to keep in mind is that, for blocks that doesn't have item, you have to do Registry.register thing
    public static final Block DAHLIA = registerBlock("dahlia",
            new FlowerBlock(StatusEffects.FIRE_RESISTANCE, 18, FabricBlockSettings.copyOf(Blocks.ALLIUM).nonOpaque().noCollision()));
    public static final Block POTTED_DAHLIA = Registry.register(Registries.BLOCK, new Identifier(TestMod.MOD_ID, "potted_dahlia"),
            new FlowerPotBlock(DAHLIA, FabricBlockSettings.copyOf(Blocks.POTTED_ALLIUM).nonOpaque()));


    //helper method for registering the block itself (and the item)
    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(TestMod.MOD_ID, name), block);
    }

    //helper method for registering new block as an item
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(TestMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks () {
        TestMod.LOGGER.info("Registering ModBlocks for" + TestMod.MOD_ID);
    }
}
