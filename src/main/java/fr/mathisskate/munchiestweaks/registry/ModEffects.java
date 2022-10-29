package fr.mathisskate.munchiestweaks.registry;

import fr.mathisskate.munchiestweaks.effect.VanishEffect;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static fr.mathisskate.munchiestweaks.util.References.MODID;

public class ModEffects {
    public static DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, MODID);

    public static RegistryObject<Effect> VANISH = EFFECTS.register("vanish", VanishEffect::new);

    public static  void registerEffects() {
        EFFECTS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

}