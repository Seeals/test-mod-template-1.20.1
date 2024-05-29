package net.seeals.testmod.block.entity;


import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;
import net.seeals.testmod.block.ModBlocks;

//registering new block entities
public class ModBlockEntities {
    public static final BlockEntityType<GemPolishingStationBlockEntity> GEM_POLISHING_STATION_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MOD_ID, "gem_polishing_be"),
                    FabricBlockEntityTypeBuilder.create(GemPolishingStationBlockEntity::new,
                            ModBlocks.GEM_POLISHING_STATION).build());

    public static void registerBlockEntities () {
        TestMod.LOGGER.info("Registering Block Entities for " + TestMod.MOD_ID);
    }
}
