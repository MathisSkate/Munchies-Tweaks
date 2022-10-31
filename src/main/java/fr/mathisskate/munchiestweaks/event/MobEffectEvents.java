package fr.mathisskate.munchiestweaks.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.PillagerEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class MobEffectEvents {
    @SubscribeEvent
    public static void playerHurtEvent(LivingDamageEvent event) {
        Entity entity = event.getEntityLiving();
        DamageSource source = event.getSource();
        if (source.getTrueSource() != null) {
            EntityType<?> type = source.getTrueSource().getType();
            if (entity.getType() == EntityType.PLAYER) {
                PlayerEntity player = (PlayerEntity) entity;
                if (!player.getActiveItemStack().isShield(player))
                    if (type == EntityType.ZOMBIE)
                        player.addPotionEffect(new EffectInstance(Effects.HUNGER, 15 * 20, 0, false, false));
                    else if (type == EntityType.BLAZE)
                        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 15 * 20, 0, false, false));
                    else if (type == EntityType.CREEPER)
                        player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 15 * 20, 0, false, false));
                    else if (type == EntityType.DRAGON_FIREBALL)
                        player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 15 * 20, 0, false, false));
                    else if (type == EntityType.ENDERMAN)
                        player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.ENDERMITE)
                        player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.END_CRYSTAL)
                        player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 5 * 20, 0, false, false));
                    else if (type == EntityType.ENDER_DRAGON)
                        player.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 15 * 20, 9, false, false));
                    else if (type == EntityType.EVOKER)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.EVOKER_FANGS)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.FIREBALL)
                        player.addPotionEffect(new EffectInstance(Effects.NAUSEA, 10 * 20, 0, false, false));
                    else if (type == EntityType.GIANT)
                        player.addPotionEffect(new EffectInstance(Effects.HUNGER, 10 * 20, 9, false, false));
                    else if (type == EntityType.GUARDIAN)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.MAGMA_CUBE)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.ZOMBIFIED_PIGLIN)
                        player.addPotionEffect(new EffectInstance(Effects.HUNGER, 15 * 20, 0, false, false));
                    else if (type == EntityType.SILVERFISH)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.SLIME)
                        player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 10 * 20, 0, false, false));
                    else if (type == EntityType.SPIDER)
                        player.addPotionEffect(new EffectInstance(Effects.POISON, 15 * 20, 0, false, false));
                    else if (type == EntityType.VEX)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.ZOMBIE_VILLAGER)
                        player.addPotionEffect(new EffectInstance(Effects.HUNGER, 15 * 20, 0, false, false));
                    else if (type == EntityType.SMALL_FIREBALL)
                        player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 15 * 20, 0, false, false));
                    else if (type == EntityType.HOGLIN)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0, false, false));
                    else if (type == EntityType.SKELETON)
                        player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 15 * 20, 0, false, false));
            }
        }
    }

    @SubscribeEvent
    public void onArrowHit(LivingHurtEvent event) {
        if (event.getSource().getTrueSource() != null) {
            if (event.getEntity() instanceof PlayerEntity) {
                PlayerEntity player = (PlayerEntity) event.getEntity();
                if (event.getSource().getTrueSource().getType().equals(EntityType.ARROW) || event.getSource().getTrueSource().getType().equals(EntityType.TRIDENT)) {
                    ArrowEntity arrow = (ArrowEntity) event.getSource().getTrueSource();
                    if (arrow.getShooter() instanceof SkeletonEntity || arrow.getShooter() instanceof DrownedEntity || arrow.getShooter() instanceof PillagerEntity) {
                        if (!player.getActiveItemStack().isShield(player)) {
                            player.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 10 * 20, 0));
                        }
                    }
                }
            }
        }
    }
}
