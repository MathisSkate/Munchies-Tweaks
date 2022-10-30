package fr.mathisskate.munchiestweaks.util;

import net.minecraftforge.common.ForgeConfigSpec;

public final class Config {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;


    public static final ForgeConfigSpec.ConfigValue<Boolean> ANVIL;
    public static final ForgeConfigSpec.ConfigValue<Boolean> BIENVENUE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DISCORD;
    public static final ForgeConfigSpec.ConfigValue<Boolean> ENDERCHEST;
    public static final ForgeConfigSpec.ConfigValue<Boolean> FIRSTSPAWN;
    public static final ForgeConfigSpec.ConfigValue<Boolean> FURNACE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> JUMP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> MOBSEFFECTS;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SITE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> TOP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> TPHERE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> TPP;
    public static final ForgeConfigSpec.ConfigValue<Boolean> VANISH;

    static {
        BUILDER.push("Config of Munchies Tweaks");
        ANVIL = BUILDER.comment("If you want to enable / disable Anvil Command (default: true)").define("Anvil Command", true);
        BIENVENUE = BUILDER.comment("If you want to enable / disable Bienvenue Command (default: true)").define("Bienvenue Command", true);
        DISCORD = BUILDER.comment("If you want to enable / disable Discord Command (default: true)").define("Discord Command", true);
        ENDERCHEST = BUILDER.comment("If you want to enable / disable EnderChest Command (default: true)").define("EnderChest Command", true);
        FIRSTSPAWN = BUILDER.comment("If you want to enable / disable FIRSTSPAWN Module (default: true)").define("First Spawn Module", false);
        FURNACE = BUILDER.comment("If you want to enable / disable Furnace Command (default: true)").define("Furnace Command", true);
        JUMP = BUILDER.comment("If you want to enable / disable Jump Command (default: true)").define("Jump Command", true);
        MOBSEFFECTS = BUILDER.comment("If you want to play with Mobs Effects (default: true)").define("Mobs Effects", true);
        SITE = BUILDER.comment("If you want to enable / disable Site Command (default: true)").define("Site Command", true);
        TOP = BUILDER.comment("If you want to enable / disable Top Command (default: true)").define("Top Command", true);
        TPHERE = BUILDER.comment("If you want to enable / disable TpHere Command (default: true)").define("TpHere Command", true);
        TPP = BUILDER.comment("If you want to enable / disable Tpp Command (default: true)").define("Tpp Command", true);
        VANISH = BUILDER.comment("If you want to enable / disable Vanish Module (default: true)").define("Vanish Module", true);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
