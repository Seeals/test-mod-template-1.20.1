package net.seeals.testmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.seeals.testmod.block.ModBlocks;
import net.seeals.testmod.entity.ModEntities;
import net.seeals.testmod.entity.client.ModModelLayers;
import net.seeals.testmod.entity.client.PorcupineModel;
import net.seeals.testmod.entity.custom.PorcupineEntity;
import net.seeals.testmod.item.ModFuelItems;
import net.seeals.testmod.item.ModItemGroups;
import net.seeals.testmod.item.ModItems;
import net.seeals.testmod.sound.ModSounds;
import net.seeals.testmod.util.ModCustomTrades;
import net.seeals.testmod.util.ModLootTableModifier;
import net.seeals.testmod.vilager.ModVillagers;
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
		ModVillagers.registerVillagers();
		ModSounds.registerSounds();
		ModEntities.registerModEntities();

		ModLootTableModifier.modifyLootTables();
		ModCustomTrades.registerCustomTrade();

		FabricDefaultAttributeRegistry.register(ModEntities.PORCUPINE, PorcupineEntity.createPorcupineAttributes());


	}
}