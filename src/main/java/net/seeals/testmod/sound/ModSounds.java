package net.seeals.testmod.sound;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.seeals.testmod.TestMod;

public class ModSounds {
    public static final SoundEvent METAL_DETECTOR_FOUND_ORE = registerSoundEvent("metal_detector_found_ore");
    //Music Disc!
    public static final SoundEvent BAR_BRAWL = registerSoundEvent("bar_brawl");

    public static final SoundEvent SOUND_BLOCK_BREAK = registerSoundEvent("sound_block_break");
    public static final SoundEvent SOUND_BLOCK_STEP = registerSoundEvent("sound_block_step");
    public static final SoundEvent SOUND_BLOCK_PLACE = registerSoundEvent("sound_block_place");
    public static final SoundEvent SOUND_BLOCK_HIT = registerSoundEvent("sound_block_hit");
    public static final SoundEvent SOUND_BLOCK_FALL = registerSoundEvent("sound_block_fall");
    //when a single block has many sounds, you have to group it like this
    public static final BlockSoundGroup SOUND_BLOCK_SOUNDS = new BlockSoundGroup(1f, 1f,
            ModSounds.SOUND_BLOCK_BREAK, ModSounds.SOUND_BLOCK_STEP, ModSounds.SOUND_BLOCK_PLACE, ModSounds.SOUND_BLOCK_HIT, ModSounds.SOUND_BLOCK_FALL); //Has to be in this order!

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = new Identifier(TestMod.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void registerSounds() {
        TestMod.LOGGER.info("Registering Sounds for " + TestMod.MOD_ID);
    }
}
