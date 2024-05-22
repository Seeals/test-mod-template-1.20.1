package net.seeals.testmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;
import net.seeals.testmod.block.ModBlocks;
import net.seeals.testmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    private static final List<ItemConvertible> SAPPHIRE_SMELTABLES = List.of(ModItems.RAW_SAPPHIRE,
            ModBlocks.SAPPHIRE_ORE,
            ModBlocks.DEEPSLATE_SAPPHIRE_ORE);

    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    //provides recipes. Check RecipeProvider class!

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        offerSmelting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 200, "sapphire");
        offerBlasting(exporter, SAPPHIRE_SMELTABLES, RecipeCategory.MISC, ModItems.SAPPHIRE, 0.7f, 100, "sapphire");

        //This next line generates a recipe that both makes 1 block to 9 items and 9 items to 1 block
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SAPPHIRE, RecipeCategory.MISC, ModBlocks.SAPPHIRE_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RUBY, RecipeCategory.MISC, ModBlocks.RUBY_BLOCK);

        //Just adding some misc blocks recipes!
        createFenceGateRecipe(ModBlocks.SAPPHIRE_FENCE_GATE, Ingredient.ofItems(ModItems.SAPPHIRE)).criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE)).offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SAPPHIRE_FENCE_GATE)));
        createFenceRecipe(ModBlocks.SAPPHIRE_FENCE, Ingredient.ofItems(ModItems.SAPPHIRE)).criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE)).offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SAPPHIRE_FENCE)));
        createDoorRecipe(ModBlocks.SAPPHIRE_DOOR, Ingredient.ofItems(ModItems.SAPPHIRE)).criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE)).offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SAPPHIRE_DOOR)));
        createStairsRecipe(ModBlocks.SAPPHIRE_STAIRS, Ingredient.ofItems(ModItems.SAPPHIRE)).criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE)).offerTo(exporter, new Identifier(getRecipeName(ModBlocks.SAPPHIRE_STAIRS)));
        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_WALL, ModItems.SAPPHIRE);
        offerSlabRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.SAPPHIRE_SLAB, ModItems.SAPPHIRE);
        offerPressurePlateRecipe(exporter,ModBlocks.SAPPHIRE_PRESSURE_PLATE, ModItems.SAPPHIRE);
        offerShapelessRecipe(exporter, ModBlocks.SAPPHIRE_BUTTON, ModItems.SAPPHIRE, "sapphire", 1);

        //This one generates shape recipes!
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.METAL_DETECTOR, 1)
                .pattern("  A")
                .pattern(" B ")
                .pattern("CC ")
                .input('A', Items.STICK)
                .input('B', Items.IRON_INGOT)
                .input('C', ModItems.SAPPHIRE)
                .criterion(hasItem(ModItems.SAPPHIRE), conditionsFromItem(ModItems.SAPPHIRE))      //This line is needed!! Criteria for the item to show up in the recipe boo
                .offerTo(exporter, new Identifier(getRecipeName(ModItems.METAL_DETECTOR)));        //Call the recipe in a specify name. In this case, it calls the same name as the metal detector item.



    }
}
