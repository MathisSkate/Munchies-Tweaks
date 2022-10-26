package fr.mathisskate.munchiestweaks.event;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VanishEvents {

    @SubscribeEvent
    public void onVanishHurt(LivingHurtEvent event) {
        if (event.getEntity() != null)
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.getActivePotionEffect(Effects.INVISIBILITY) != null)
                    if (((PlayerEntity) event.getEntity()).getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99)
                        event.setCanceled(true);
            }
    }

    @SubscribeEvent
    public void onVanishAttacked(LivingAttackEvent event) {
        if (event.getEntity() != null)
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.getActivePotionEffect(Effects.INVISIBILITY) != null)
                    if (player.getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99)
                        event.setCanceled(true);
            }
    }
}
