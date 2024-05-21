package net.seeals.testmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.BlockItem;
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

                    }).build());

    public static void registerItemGroups() {
        TestMod.LOGGER.info("Registering Item Groups for" + TestMod.MOD_ID);
    }

}
