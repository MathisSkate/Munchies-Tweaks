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

public class TppCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> tpp =
                Commands.literal("tpp")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .then(Commands.argument("player", EntityArgument.player())
                        .executes(TppCommand::teleportToPlayer));
        dispatcher.register(tpp);
    }

    private static int teleportToPlayer(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        ServerPlayerEntity targetPlayer = EntityArgument.getPlayer(commandContext, "player");
        player.teleport(targetPlayer.getServerWorld(), targetPlayer.getPosX(), targetPlayer.getPosY(), targetPlayer.getPosZ(), targetPlayer.rotationYaw, targetPlayer.rotationPitch);
        player.sendMessage(new StringTextComponent(References.PREFIX + "Tu t'es téléporté à " + targetPlayer.getDisplayName().getString()), DUMMY_UUID);
        return Command.SINGLE_SUCCESS;
    }
}
