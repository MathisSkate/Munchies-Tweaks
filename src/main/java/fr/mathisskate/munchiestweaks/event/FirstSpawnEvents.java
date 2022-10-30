package fr.mathisskate.munchiestweaks.event;

import fr.mathisskate.munchiestweaks.util.FirstSpawnConfig;
import fr.mathisskate.munchiestweaks.util.References;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class FirstSpawnEvents {
    @SubscribeEvent
    public void onPlayerFirstJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getPlayer() != null) {
            PlayerEntity player = event.getPlayer();
            if (!player.getTags().contains(References.MODID + ":firstspawn")) {
                player.addTag(References.MODID + ":firstspawn");
                player.teleportKeepLoaded(FirstSpawnConfig.X.get(), FirstSpawnConfig.Y.get(), FirstSpawnConfig.Z.get());
                player.sendMessage(new StringTextComponent(References.PREFIX + "Bienvenue sur Munchies !"), Util.DUMMY_UUID);
            }
        }
    }
}
