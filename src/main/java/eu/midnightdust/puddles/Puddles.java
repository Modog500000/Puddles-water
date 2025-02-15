package eu.midnightdust.puddles;

import eu.midnightdust.puddles.config.PuddlesConfig;
import eu.pb4.polymer.core.api.item.PolymerBlockItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.ParticleS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class Puddles implements ModInitializer {
    public static final String MOD_ID = "puddles";
    public static final Identifier PUDDLE_ID = id("puddle");
    private final static EntityAttributeModifier entityAttributeModifier = new EntityAttributeModifier(id("puddle_speed"), 100, EntityAttributeModifier.Operation.ADD_VALUE);

    @Override
    public void onInitialize() {
        PuddlesConfig.init(MOD_ID, PuddlesConfig.class);
    }
    public static Identifier id(String path) {
        return Identifier.of(MOD_ID, path);
    }
}
