package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.util.text.*;

import static net.minecraft.util.Util.DUMMY_UUID;

public class BienvenueCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> bienvenue =
                Commands.literal("b")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .then(Commands.argument("player", EntityArgument.players())
                                .executes(BienvenueCommand::sendBienvenue)
                        );
        dispatcher.register(bienvenue);
    }

    private static int sendBienvenue(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        Entity targetPlayer = EntityArgument.getEntity(commandContext, "player");
        Entity player = commandContext.getSource().getEntity();
        ITextComponent messagebienvenue = new StringTextComponent(TextFormatting.DARK_GREEN + "Bienvenue " + TextFormatting.GOLD + targetPlayer.getDisplayName().getString() + TextFormatting.DARK_GREEN + " !");
        TranslationTextComponent finalmessage = new TranslationTextComponent("chat.type.announcement", commandContext.getSource().getDisplayName(), messagebienvenue);
        if(!player.equals(targetPlayer))
            commandContext.getSource().getServer().getPlayerList().func_232641_a_(finalmessage, ChatType.CHAT, player.getUniqueID());
        else
            player.sendMessage(new StringTextComponent(TextFormatting.DARK_RED + "On ne se dit pas Bienvenue !"), DUMMY_UUID);
        return 1;
    }
}
