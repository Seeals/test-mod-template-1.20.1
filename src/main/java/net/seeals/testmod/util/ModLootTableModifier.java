package net.seeals.testmod.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import net.seeals.testmod.item.ModItems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Modifying or replacing minecraft loot tables! Check external libraries for more example!

//Specifying which loot tables you are modifying. The Identifier identifies it (note that minecraft here could be omitted but if I understand correctly, when use custom loot table, you have to change it to your mod id)
public class ModLootTableModifier {
    private static final Identifier JUNGLE_TEMPLE_ID =
            new Identifier("minecraft", "chests/jungle_temple");
    private static final Identifier CREEPER_ID =
            new Identifier("minecraft", "entities/creeper");
    private static final Identifier SUSPICIOUS_SAND_ID =
            new Identifier("minecraft", "archaeology/desert_pyramid");


    public static void  modifyLootTables() {
    //Adding the actual properties of the loot table specified. Just duplicate the if statement for more.
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if(JUNGLE_TEMPLE_ID.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder().
                        rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(1f)) //Drops 100% of the time
                        .with(ItemEntry.builder(ModItems.METAL_DETECTOR))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)).build()); //1 in there or 2 in there ex : (1.0f, 2.0f)

                tableBuilder.pool(poolBuilder.build());
            }

                if(CREEPER_ID.equals(id)) {
                    LootPool.Builder poolBuilder = LootPool.builder().
                            rolls(ConstantLootNumberProvider.create(1))
                            .conditionally(RandomChanceLootCondition.builder(1f)) //Drops 100% of the time
                            .with(ItemEntry.builder(ModItems.COAL_BRIQUETTE))
                            .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)).build()); //1 in there or 2 in there ex : (1.0f, 2.0f)

                    tableBuilder.pool(poolBuilder.build());
                }
        });

        //SUS SAND!! Need replace events! We're not really replacing the loot table of the sus sand here, we're modifying it. But it has to be done this way because after it gave the first item to the player, the rest is discards. So its a bit wierd. SADLY ITS NOT WORKING IN 1.12.2
        LootTableEvents.REPLACE.register(((resourceManager, lootManager, id, original, source) -> {
//            if(SUSPICIOUS_SAND_ID.equals(id)) {                                                                 //
//                List<LootPoolEntry> entries =  new ArrayList<>(Arrays.asList(original.pools[0].entries));       //specifying the entries that already exist. In this case, the desert pyramid archaeology. This reads out the list and adds the new entries
//                entries.add(ItemEntry.builder(ModItems.METAL_DETECTOR).build());                                //Adding to the list
//                entries.add(ItemEntry.builder(ModItems.RUBY).build());
//                entries.add(ItemEntry.builder(ModItems.RAW_SAPPHIRE).build());
//
//                LootPool.Builder pool = LootPool.builder().with(entries);
//                return LootTable.builder().pool(pool).build();                                                  //replacing said list
//
//            }


            return null; //You have to return the replaced loot table or null if it wasn't replaced. So this is like a safety barrier
        }));

    }
}
