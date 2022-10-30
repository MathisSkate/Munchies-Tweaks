package fr.mathisskate.munchiestweaks.util;

import net.minecraftforge.common.ForgeConfigSpec;

public class FirstSpawnConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> X;
    public static final ForgeConfigSpec.ConfigValue<Integer> Y;
    public static final ForgeConfigSpec.ConfigValue<Integer> Z;

    static {
        BUILDER.push("Config of First Spawn");
        X = BUILDER.comment("First Spawn X").define("X", 0);
        Y = BUILDER.comment("First Spawn Y").define("Y", 250);
        Z = BUILDER.comment("First Spawn Z").define("Z", 0);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
