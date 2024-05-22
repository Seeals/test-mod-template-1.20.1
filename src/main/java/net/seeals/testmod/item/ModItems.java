package net.seeals.testmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;
import net.seeals.testmod.item.custom.MetalDetectorItem;
import net.seeals.testmod.item.custom.ModArmorItem;
import net.seeals.testmod.item.custom.ModArmorMaterial;
import net.seeals.testmod.item.custom.TestSoundItem;

public class ModItems { // other items will be added here.
    public static final Item SAPPHIRE = registerItem("sapphire", new Item(new FabricItemSettings()));
    public static final Item RAW_SAPPHIRE = registerItem("raw_sapphire", new Item(new FabricItemSettings()));
    public static final Item RUBY = registerItem("ruby", new Item(new FabricItemSettings()));

    //for advance item, use its item class!
    public static final Item METAL_DETECTOR = registerItem("metal_detector", new MetalDetectorItem(new FabricItemSettings().maxDamage(1024)));
    public static final Item SOUND_ITEM_TEST= registerItem("sound_item_test", new TestSoundItem(new FabricItemSettings()));
    public static final Item SAPPHIRE_STAFF = registerItem("sapphire_staff", new Item(new FabricItemSettings().maxDamage(2048)));
    //tools!
    public static final Item SAPPHIRE_PICKAXE = registerItem("sapphire_pickaxe", new PickaxeItem(ModToolMaterial.SAPPHIRE, 4, 2f, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_AXE = registerItem("sapphire_axe", new AxeItem(ModToolMaterial.SAPPHIRE, 13, 1.25f, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_SHOVEL = registerItem("sapphire_shovel", new ShovelItem(ModToolMaterial.SAPPHIRE, 3, 2f, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_SWORD = registerItem("sapphire_sword", new SwordItem(ModToolMaterial.SAPPHIRE, 8, 3f, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_HOE = registerItem("sapphire_hoe", new HoeItem(ModToolMaterial.SAPPHIRE, 3, 2f, new FabricItemSettings().maxDamage(2048)));
    //armors!!
    public static final Item SAPPHIRE_HELMET = registerItem("sapphire_helmet", new ModArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.HELMET, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_CHESTPLATE = registerItem("sapphire_chestplate", new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_LEGGINGS = registerItem("sapphire_leggings", new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.LEGGINGS, new FabricItemSettings().maxDamage(2048)));
    public static final Item SAPPHIRE_BOOTS = registerItem("sapphire_boots", new ArmorItem(ModArmorMaterial.SAPPHIRE, ArmorItem.Type.BOOTS, new FabricItemSettings().maxDamage(2048)));

    //for food item, food is not advance item with no custom code, so it use the Item class except in the fabric item setting, do this.
    public static final Item TOMATO= registerItem("tomato", new Item(new FabricItemSettings().food(ModFoodComponents.TOMATO)));
    public static final Item COAL_BRIQUETTE= registerItem("coal_briquette", new Item(new FabricItemSettings()));


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
