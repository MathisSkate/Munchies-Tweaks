package fr.mathisskate.munchiestweaks.commande;

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

public class DiscordCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> discord =
                Commands.literal("discord")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(DiscordCommand::sendDiscord);
        dispatcher.register(discord);
    }

    private static int sendDiscord(CommandContext<CommandSource> commandContext) {
        Entity player = commandContext.getSource().getEntity();
        ITextComponent link = ForgeHooks.newChatWithLinks(TextFormatting.LIGHT_PURPLE + "Le Discord du Serveur : https://discord.com/invite/QX9suwt9Uk");
        player.sendMessage(link, DUMMY_UUID);
        return Command.SINGLE_SUCCESS;
    }
}
