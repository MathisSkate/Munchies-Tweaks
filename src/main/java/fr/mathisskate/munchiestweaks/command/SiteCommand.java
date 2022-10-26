package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.ForgeHooks;

import static net.minecraft.util.Util.DUMMY_UUID;

public class SiteCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> site =
                Commands.literal("site")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(SiteCommand::sendSite);
        dispatcher.register(site);
    }

    private static int sendSite(CommandContext<CommandSource> commandContext) {
        Entity player = commandContext.getSource().getEntity();
        ITextComponent link = ForgeHooks.newChatWithLinks(TextFormatting.GREEN + "Le Site du Serveur : https://www.munchies.websr.fr");
        player.sendMessage(link, DUMMY_UUID);
        return Command.SINGLE_SUCCESS;
    }
}
