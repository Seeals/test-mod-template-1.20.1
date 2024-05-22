package net.seeals.testmod.item.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;


//Adding effects to player when the full set of armor is being worn!
public class ModArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, StatusEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    //this is an armor map! Basically it maps the armor into an effect. So just copy the block of code below for each armor materials! (only work with custom materials) And don't forget to move the .build() down!
                    .put(ModArmorMaterial.SAPPHIRE, new StatusEffectInstance(StatusEffects.HASTE, 400, 1,
                            false, false, true))
                    .build();


    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);

    }
    //the inventoryTick methods gets call every tick that the item is in your inventory!
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {                                                                                 //are we on the server?
            if (entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)) {                        //do we have full armors on?
                evaluateArmorEffects(player);
            }
        }


        super.inventoryTick(stack, world, entity, slot, selected);

    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapStatusEffect = entry.getValue();

            if (hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffect);

            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapStatusEffect) {
        boolean hasPlayerEffect = player.hasStatusEffect(mapStatusEffect.getEffectType());

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hasPlayerEffect) {
            player.addStatusEffect(new StatusEffectInstance(mapStatusEffect));
        }
    }

        //The check full armor method
        private boolean hasFullSuitOfArmorOn (PlayerEntity player){
            ItemStack boots = player.getInventory().getArmorStack(0);
            ItemStack leggings = player.getInventory().getArmorStack(1);
            ItemStack breastplate = player.getInventory().getArmorStack(2);
            ItemStack helmet = player.getInventory().getArmorStack(3);

            return !helmet.isEmpty() && !breastplate.isEmpty()
                    && !leggings.isEmpty() && !boots.isEmpty();
        }

        //Check if you have correct armor on
    private boolean hasCorrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }

        ArmorItem boots = ((ArmorItem)player.getInventory().getArmorStack(0).getItem());
        ArmorItem leggings = ((ArmorItem)player.getInventory().getArmorStack(1).getItem());
        ArmorItem breastplate = ((ArmorItem)player.getInventory().getArmorStack(2).getItem());
        ArmorItem helmet = ((ArmorItem)player.getInventory().getArmorStack(3).getItem());

        return helmet.getMaterial() == material && breastplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        ArmorMaterial material = ((ArmorItem) stack.getItem()).getMaterial();

        if (material == ModArmorMaterial.SAPPHIRE) {
            tooltip.add(Text.translatable("tooltip.testmod.sapphire_armor").formatted(Formatting.AQUA));

            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}

