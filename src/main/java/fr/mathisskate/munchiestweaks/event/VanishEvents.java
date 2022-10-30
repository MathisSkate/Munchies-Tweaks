package fr.mathisskate.munchiestweaks.event;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class VanishEvents {
    @SubscribeEvent
    public void onVanishedPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() != null)
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (player.getActivePotionEffect(Effects.INVISIBILITY) != null)
                    if (player.getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99)
                        event.setCanceled(true);
            }
    }

    @SubscribeEvent
    public void onLivingTargetVanishedPlayer(LivingSetAttackTargetEvent event) {
        if (event.getTarget() != null)
            if (event.getTarget() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getTarget();
                if (event.getEntityLiving() != null)
                    if (event.getEntityLiving() instanceof MobEntity) {
                        MobEntity attacker = (MobEntity) event.getEntityLiving();
                        if (player.getActivePotionEffect(Effects.INVISIBILITY) != null)
                            if (player.getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99)
                                attacker.setAttackTarget(null);
                    }
            }
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onPreHideVanishedPlayer(RenderPlayerEvent event) {
        if (event.getPlayer() != null) {
            PlayerEntity player = event.getPlayer();
            if (player.getActivePotionEffect(Effects.INVISIBILITY) != null)
                if (player.getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99)
                    event.setCanceled(true);
        }
    }
}
