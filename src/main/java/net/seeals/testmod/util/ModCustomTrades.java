package net.seeals.testmod.util;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import net.seeals.testmod.item.ModItems;

public class ModCustomTrades {
    public static void registerCustomTrade() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1,
                factories -> {
                    //Add new trades here to the factories method
            factories.add((entity, random) -> new TradeOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.TOMATO, 7),
                    6, 5, 0.05f));
                //You can add multiple items into a trade!
            factories.add((entity, random) -> new TradeOffer(
              new ItemStack(Items.EMERALD, 1),
              new ItemStack(Items.DIAMOND, 4),
              new ItemStack(ModItems.CORN, 5),
                    6, 5, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {
                    //Add new trades here to the factories method
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.GOLD_INGOT, 2),
                            new ItemStack(ModItems.TOMATO_SEEDS, 7),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.IRON_INGOT, 1),
                            new ItemStack(ModItems.CORN_SEEDS, 5),
                            6, 5, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.LIBRARIAN, 1,
                factories -> {
                    //You can also add enchanted item
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.GOLD_INGOT, 2),
                            new ItemStack(ModItems.TOMATO_SEEDS, 7),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(ModItems.SAPPHIRE, 32),
                            EnchantedBookItem.forEnchantment(new EnchantmentLevelEntry(Enchantments.PIERCING, 2)),
                            6, 5, 0.05f));
                });

        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 2,
                factories -> {
                    //Add new trades here to the factories method
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.GOLD_INGOT, 2),
                            new ItemStack(ModItems.TOMATO_SEEDS, 7),
                            6, 5, 0.05f));

                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.IRON_INGOT, 1),
                            new ItemStack(ModItems.CORN_SEEDS, 5),
                            6, 5, 0.05f));
                });

        TradeOfferHelper.registerWanderingTraderOffers( 2,
                factories -> {
                    //Add new trades here to the factories method
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 2),
                            new ItemStack(ModItems.RUBY, 7),
                            6, 5, 0.05f));

                });
    }
}
