package net.seeals.testmod.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;

public class ModBlocks {

    //we add blocks here. Note that the FabricBlockSettings needs us to copy or create new type of blocks.
    // You could just pull a property from a block and edits it without making a new one (middle-click the IRON_BLOCK
    // to check the properties of each minecraft blocks
    // DON'T FORGET THE JSON FILES FOR BOTH BLOCKS AND ITEMS!!
    public static final Block SAPPHIRE_BLOCK = registerBlock("sapphire_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RUBY_BLOCK = registerBlock("ruby_block",
            new Block(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK).sounds(BlockSoundGroup.BASALT)));


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
