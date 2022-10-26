package fr.mathisskate.munchiestweaks.registry;

import com.mojang.brigadier.CommandDispatcher;
import fr.mathisskate.munchiestweaks.command.*;
import net.minecraft.command.CommandSource;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RegisterCommands {
    @SubscribeEvent
    public static void onRegisterCommandEvent(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSource> dispatcher = event.getDispatcher();
        AnvilCommand.register(dispatcher);
        BienvenueCommand.register(dispatcher);
        DiscordCommand.register(dispatcher);
        EnderChestCommand.register(dispatcher);
        FurnaceCommand.register(dispatcher);
        SiteCommand.register(dispatcher);
        TopCommand.register(dispatcher);
        TppCommand.register(dispatcher);
        VanishCommand.register(dispatcher);
    }
}
