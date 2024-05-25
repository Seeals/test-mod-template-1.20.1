package net.seeals.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;
import net.seeals.testmod.block.ModBlocks;


public class ModItemGroups {
    public static final ItemGroup SEEALS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TestMod.MOD_ID, "sapphire"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sapphire")) //add display name from the en_us.json
                    .icon(()-> new ItemStack(ModItems.SAPPHIRE)).entries((displayContext, entries) -> { //icon displays the sapphire, and entires specifies item in the group. Called using lambda function
                        entries.add(ModItems.SAPPHIRE); //Items into item groups goes here.
                        entries.add(ModItems.RUBY);
                        entries.add(ModBlocks.SAPPHIRE_BLOCK);
                        entries.add((ModBlocks.RUBY_BLOCK));
                        entries.add((ModBlocks.SAPPHIRE_ORE));
                        entries.add((ModItems.RAW_SAPPHIRE));
                        entries.add(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
                        entries.add(ModItems.METAL_DETECTOR);
                        entries.add(ModBlocks.SOUND_BLOCK);
                        entries.add(ModItems.SOUND_ITEM_TEST);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.COAL_BRIQUETTE);
                        entries.add(ModItems.SAPPHIRE_STAFF);
                        entries.add(ModItems.SAPPHIRE_SWORD);
                        entries.add(ModItems.SAPPHIRE_PICKAXE);
                        entries.add(ModItems.SAPPHIRE_AXE);
                        entries.add(ModItems.SAPPHIRE_SHOVEL);
                        entries.add(ModItems.SAPPHIRE_HOE);
                        entries.add(ModItems.SAPPHIRE_HELMET);
                        entries.add(ModItems.SAPPHIRE_CHESTPLATE);
                        entries.add(ModItems.SAPPHIRE_LEGGINGS);
                        entries.add(ModItems.SAPPHIRE_BOOTS);
                        entries.add(ModItems.TOMATO_SEEDS);
                        entries.add(ModItems.CORN);
                        entries.add(ModItems.CORN_SEEDS);
                        entries.add(ModBlocks.DAHLIA);
                        entries.add(ModItems.BAR_BRAWL_MUSIC_DISC);
                        entries.add(ModItems.PORCUPINE_SPAWN_EGG);
                    }).build());

    public static final ItemGroup SEEALS_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TestMod.MOD_ID, "mod_blocks"),
                    FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mod_blocks"))
                                    .icon(()-> new ItemStack(ModBlocks.SAPPHIRE_STAIRS)).entries((displayContext, entries) -> {
                                entries.add((ModBlocks.SAPPHIRE_BUTTON));
                                entries.add((ModBlocks.SAPPHIRE_FENCE));
                                entries.add((ModBlocks.SAPPHIRE_DOOR));
                                entries.add((ModBlocks.SAPPHIRE_SLAB));
                                entries.add((ModBlocks.SAPPHIRE_STAIRS));
                                entries.add((ModBlocks.SAPPHIRE_PRESSURE_PLATE));
                                entries.add((ModBlocks.SAPPHIRE_TRAPDOOR));
                                entries.add((ModBlocks.SAPPHIRE_WALL));
                                entries.add((ModBlocks.SAPPHIRE_FENCE_GATE));
                                entries.add(ModBlocks.SAPPHIRE_BLOCK);
                                entries.add((ModBlocks.SAPPHIRE_ORE));
                                entries.add((ModBlocks.DEEPSLATE_SAPPHIRE_ORE));
                                entries.add((ModBlocks.RUBY_BLOCK));
                            }).build());



    public static void registerItemGroups() {
        TestMod.LOGGER.info("Registering Item Groups for" + TestMod.MOD_ID);

    }

}
