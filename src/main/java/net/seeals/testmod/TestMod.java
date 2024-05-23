package net.seeals.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.seeals.testmod.block.ModBlocks;
import net.seeals.testmod.item.ModFuelItems;
import net.seeals.testmod.item.ModItemGroups;
import net.seeals.testmod.item.ModItems;
import net.seeals.testmod.util.ModCustomTrades;
import net.seeals.testmod.util.ModLootTableModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMod implements ModInitializer {
	public static final String MOD_ID = "testmod";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {


		ModItems.registerModitems();
		ModItemGroups.registerItemGroups();
		ModBlocks.registerModBlocks();
		ModFuelItems.registerFuels();

		ModLootTableModifier.modifyLootTables();
		ModCustomTrades.registerCustomTrade();


	}
}