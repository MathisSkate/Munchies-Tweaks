package fr.mathisskate.munchiestweaks;

import fr.mathisskate.munchiestweaks.event.FirstSpawnEvents;
import fr.mathisskate.munchiestweaks.event.MobEffectEvents;
import fr.mathisskate.munchiestweaks.event.VanishEvents;
import fr.mathisskate.munchiestweaks.registry.ModCommands;
import fr.mathisskate.munchiestweaks.util.Config;
import fr.mathisskate.munchiestweaks.util.FirstSpawnConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("munchiestweaks")
public class MunchiesTweaksMain {
    public static final Logger LOGGER = LogManager.getLogger();

    public MunchiesTweaksMain() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC, "munchies_tweaks_common.toml");
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FirstSpawnConfig.SPEC, "munchies_tweaks_firstspawn.toml");
    }

    private void setup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(ModCommands.class);
        if (Config.VANISH.get())
            MinecraftForge.EVENT_BUS.register(new VanishEvents());
        if (Config.MOBSEFFECTS.get())
            MinecraftForge.EVENT_BUS.register(new MobEffectEvents());
        if(Config.FIRSTSPAWN.get())
            MinecraftForge.EVENT_BUS.register(new FirstSpawnEvents());
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
    }

    private void enqueueIMC(final InterModEnqueueEvent event) {
    }

    private void processIMC(final InterModProcessEvent event) {
    }
}
