package net.seeals.testmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;
import net.seeals.testmod.item.custom.MetalDetectorItem;
import net.seeals.testmod.item.custom.TestSoundItem;

public class ModItems { // other items will be added here.
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new FabricItemSettings()));
    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire", new Item(new FabricItemSettings()));
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));

    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(1024)));
    public static final Item SOUND_ITEM_TEST= registerItem("sound_item_test", new TestSoundItem(new FabricItemSettings()));      //for advance item, use its item class!

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(SAPPHIRE);
        entries.add(RUBY);
        entries.add(RAW_SAPPHIRE);
    }


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(TestMod.MOD_ID, name), item);
    }


    public static void registerModitems() {
        TestMod.LOGGER.info("Registering Mod Items for " + TestMod.MOD_ID);

        //The "ItemGroups.INGREDIENTS" here, the "INGREDIENTS" part is changable to whatever creative tab you want it to show up.
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);

    }



}
