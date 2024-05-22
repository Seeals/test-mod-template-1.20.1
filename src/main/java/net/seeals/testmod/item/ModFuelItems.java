package net.seeals.testmod.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class ModFuelItems {
    public static void registerFuels() {
        FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE, 200);
    }

}
