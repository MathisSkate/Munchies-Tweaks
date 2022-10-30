package fr.mathisskate.munchiestweaks.event;

import fr.mathisskate.munchiestweaks.registry.ModEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
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
    public void onLivingTargetVanishedPlayer(LivingSetAttackTargetEvent event) {
        if (event.getTarget() != null)
            if (event.getTarget() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getTarget();
                if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null)
                    event.getEntityLiving().setRevengeTarget(null);
            }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPreHideVanishedPlayer(RenderPlayerEvent event) {
        if (event.getPlayer() != null) {
            PlayerEntity player = event.getPlayer();
            if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null)
                event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() != null) {
            PlayerEntity player = event.getPlayer();
            if (player.getActivePotionEffect(ModEffects.VANISH.get()) != null) {
                player.removePotionEffect(ModEffects.VANISH.get());
                player.setInvisible(false);
            }
        }
    }
}
