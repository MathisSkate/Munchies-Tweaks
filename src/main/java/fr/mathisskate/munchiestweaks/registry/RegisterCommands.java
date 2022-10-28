package fr.mathisskate.munchiestweaks.registry;

import com.mojang.brigadier.CommandDispatcher;
import fr.mathisskate.munchiestweaks.command.*;
import fr.mathisskate.munchiestweaks.util.Config;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommands {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        if (Config.ANVIL.get())
            AnvilCommand.register(dispatcher);
        if (Config.BIENVENUE.get())
            BienvenueCommand.register(dispatcher);
        if (Config.DISCORD.get())
            DiscordCommand.register(dispatcher);
        if (Config.ENDERCHEST.get())
            EnderChestCommand.register(dispatcher);
        if (Config.FURNACE.get())
            FurnaceCommand.register(dispatcher);
        if (Config.JUMP.get())
            JumpCommand.register(dispatcher);
        if (Config.SITE.get())
            SiteCommand.register(dispatcher);
        if (Config.TOP.get())
            TopCommand.register(dispatcher);
        if (Config.TPHERE.get())
            TpHereCommand.register(dispatcher);
        if (Config.TPP.get())
            TppCommand.register(dispatcher);
        if (Config.VANISH.get())
            VanishCommand.register(dispatcher);
    }
}
