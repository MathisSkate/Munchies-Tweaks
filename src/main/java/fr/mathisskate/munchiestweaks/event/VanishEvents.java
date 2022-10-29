package fr.mathisskate.munchiestweaks.event;

import fr.mathisskate.munchiestweaks.registry.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VanishEvents {

    @SubscribeEvent
    public void onVanishedPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() != null)
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null)
                    event.setCanceled(true);
            }
    }

    @SubscribeEvent
    public void onVanishedPlayerTarget(LivingAttackEvent event) {
        if (event.getEntity() != null)
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null)
                    event.setCanceled(true);
            }
    }

    @SubscribeEvent
    public void onHideVanishedPlayer(RenderPlayerEvent event) {
        if (event.getPlayer() != null) {
            PlayerEntity player = event.getPlayer();
            if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null)
                event.setCanceled(true);
        }
    }
}
