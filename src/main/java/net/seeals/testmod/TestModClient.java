package net.seeals.testmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import net.seeals.testmod.block.ModBlocks;
import net.seeals.testmod.entity.ModEntities;
import net.seeals.testmod.entity.client.ModModelLayers;
import net.seeals.testmod.entity.client.PorcupineModel;
import net.seeals.testmod.entity.client.PorcupineRenderer;
import net.seeals.testmod.screen.GemPolishingScreen;
import net.seeals.testmod.screen.ModScreenHandler;

public class TestModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Anything that isn't opaque MUST be add here! If it doesn't have cut out, it will turn into an x-ray block!
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SAPPHIRE_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SAPPHIRE_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CORN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DAHLIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DAHLIA, RenderLayer.getCutout());

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PORCUPINE, PorcupineModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.PORCUPINE, PorcupineRenderer::new);

        HandledScreens.register(ModScreenHandler.GEM_POLISHING_SCREEN_HANDLER, GemPolishingScreen::new);

    }
}
