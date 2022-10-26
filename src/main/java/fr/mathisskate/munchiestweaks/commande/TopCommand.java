package fr.mathisskate.munchiestweaks.commande;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.server.ServerWorld;

public class TopCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> top =
                Commands.literal("top")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(TopCommand::teleporteAtTop);
        dispatcher.register(top);
    }

    private static int teleporteAtTop(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        ServerWorld world = commandContext.getSource().getWorld();

        double x = player.getPosX();
        int y = world.getHeight();
        double z = player.getPosZ();

        Chunk chunk = world.getChunk((int) x >> 4, (int) z >> 4);

        while (y > 0) {
            y--;

            BlockPos groundPos = new BlockPos(x, y - 2, z);
            if (!chunk.getBlockState(groundPos).getMaterial().equals(Material.AIR)) {
                BlockPos legPos = new BlockPos(x, y - 1, z);
                if (chunk.getBlockState(legPos).getMaterial().equals(Material.AIR)) {
                    BlockPos headPos = new BlockPos(x, y, z);
                    if (chunk.getBlockState(headPos).getMaterial().equals(Material.AIR)) {
                        break;
                    }
                }
            }
        }
        player.teleport(world, x, y - 1, z, player.getYaw(0), player.getPitch(0));
        return Command.SINGLE_SUCCESS;
    }
}
