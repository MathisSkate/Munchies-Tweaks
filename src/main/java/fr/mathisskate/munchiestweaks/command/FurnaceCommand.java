package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.Hand;
import net.minecraftforge.items.ItemHandlerHelper;

public class FurnaceCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> furnace =
                Commands.literal("furnace")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(FurnaceCommand::smeltItem);
        dispatcher.register(furnace);
    }

    private static int smeltItem(CommandContext<CommandSource> commandContext) {
        Entity entity = commandContext.getSource().getEntity();
        PlayerEntity player = (PlayerEntity) entity;
        ItemStack item = player.getHeldItem(Hand.MAIN_HAND);
        player.setHeldItem(Hand.MAIN_HAND, getSmelt(player, item));
        return Command.SINGLE_SUCCESS;
    }

    public static ItemStack getSmelt(PlayerEntity player, ItemStack item) {
        return player.getEntityWorld().getRecipeManager().getRecipe(IRecipeType.SMELTING, new Inventory(item), player.getEntityWorld())
                .map(FurnaceRecipe::getRecipeOutput)
                .filter(itemStack -> !itemStack.isEmpty())
                .map(itemStack -> ItemHandlerHelper.copyStackWithSize(itemStack, item.getCount() * itemStack.getCount()))
                .orElse(item);
    }
}
