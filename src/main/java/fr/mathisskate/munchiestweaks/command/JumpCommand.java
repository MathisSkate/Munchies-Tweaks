package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import fr.mathisskate.munchiestweaks.util.References;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.server.ServerWorld;

import static net.minecraft.util.Util.DUMMY_UUID;

public class JumpCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> jump =
                Commands.literal("jump")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(JumpCommand::jumpToView);
        dispatcher.register(jump);
    }

    private static int jumpToView(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        ServerWorld world = commandContext.getSource().getWorld();

        RayTraceResult block = player.pick(100.0D, 0.0F, false);

        if(block.getType() == RayTraceResult.Type.BLOCK) {
            BlockPos pos = ((BlockRayTraceResult)block).getPos();
            int y = pos.getY();
            int maxY = y + 20;
            while (y <= maxY) {
                BlockPos blockPos = new BlockPos(pos.getX(), y, pos.getZ());
                if (!isAir(world.getBlockState(blockPos)))
                    y++;
                else
                    break;
            }
            if(isAir(world.getBlockState(new BlockPos(pos.getX(), y, pos.getZ()))))
                player.teleport(world, pos.getX(), y + 1, pos.getZ(), player.rotationYaw, player.rotationPitch);
            else
                player.sendMessage(new StringTextComponent(References.PREFIX + "Aucun block disponible à la téléportation !"), DUMMY_UUID);
        } else {
            player.sendMessage(new StringTextComponent(References.PREFIX + "Aucun block disponible à la téléportation !"), DUMMY_UUID);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static boolean isAir(BlockState block) {
        return block.getMaterial().equals(Material.AIR);
    }
}
