package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.mathisskate.munchiestweaks.util.References;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import static net.minecraft.util.Util.DUMMY_UUID;

public class TpHereCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> tp =
                Commands.literal("tphere")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .then(Commands.argument("player", EntityArgument.player())
                        .executes(TpHereCommand::teleportAPlayer));
        dispatcher.register(tp);
    }

    private static int teleportAPlayer(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        ServerPlayerEntity targetPlayer = EntityArgument.getPlayer(commandContext, "player");
        targetPlayer.teleport(player.getServerWorld(), player.getPosX(), player.getPosY(), player.getPosZ(), player.rotationYaw, player.rotationPitch);
        targetPlayer.sendMessage(new StringTextComponent(References.PREFIX + "Tu as été téléporté à " + player.getDisplayName().getString()), DUMMY_UUID);
        return Command.SINGLE_SUCCESS;
    }
}
