package fr.mathisskate.munchiestweaks.utils;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
        public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
        public static final ForgeConfigSpec SPEC;

        public static final ForgeConfigSpec.ConfigValue<Boolean> hasMobsEffects;

        static {
            BUILDER.push("Config of Munchies Tweaks");
            hasMobsEffects = BUILDER.comment("If you want to play with Mobs Effects").define("Mobs Effects", true);
            BUILDER.pop();
            SPEC = BUILDER.build();
        }
}
