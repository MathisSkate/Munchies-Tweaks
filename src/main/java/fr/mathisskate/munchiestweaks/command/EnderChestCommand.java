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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnderChestCommand {
    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> enderchest =
                Commands.literal("ec")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(EnderChestCommand::openEnderchest);
        dispatcher.register(enderchest);
        LiteralArgumentBuilder<CommandSource> enderchestsee =
                Commands.literal("ecsee")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .then(Commands.argument("player", EntityArgument.player())
                                .executes(EnderChestCommand::openEnderchestOther));
        dispatcher.register(enderchestsee);
    }

    private static int openEnderchestOther(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        ServerPlayerEntity targetPlayer = EntityArgument.getPlayer(commandContext, "player");
        if (!player.equals(targetPlayer)) {
            NetworkHooks.openGui(player, new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return new StringTextComponent(targetPlayer.getDisplayName().getString() + " Ender Chest");
                }

                @Override
                public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                    return new ChestContainer(ContainerType.GENERIC_9X3, id, player.inventory, targetPlayer.getInventoryEnderChest(), 3);
                }
            });
        } else {
            player.sendMessage(new StringTextComponent(References.PREFIX + TextFormatting.RED + "Tu ne peux pas regarder ton enderchest comme ceci, utilise /ec"), Util.DUMMY_UUID);
        }
        return Command.SINGLE_SUCCESS;
    }

    private static int openEnderchest(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        ServerPlayerEntity player = commandContext.getSource().asPlayer();
        NetworkHooks.openGui(player, new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new StringTextComponent(player.getDisplayName().getString() + " Ender Chest");
            }

            @Override
            public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new ChestContainer(ContainerType.GENERIC_9X3, id, player.inventory, player.getInventoryEnderChest(), 3);
            }
        });
        return Command.SINGLE_SUCCESS;
    }
}
