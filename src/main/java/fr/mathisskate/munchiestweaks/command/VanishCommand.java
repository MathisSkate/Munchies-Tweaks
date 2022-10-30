package fr.mathisskate.munchiestweaks.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import fr.mathisskate.munchiestweaks.util.References;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import static net.minecraft.util.Util.DUMMY_UUID;

public class VanishCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        LiteralArgumentBuilder<CommandSource> vanish =
                Commands.literal("v")
                        .requires(commandSource -> commandSource.hasPermissionLevel(1))
                        .executes(VanishCommand::setVanish);
        dispatcher.register(vanish);
    }

    private static int setVanish(CommandContext<CommandSource> commandContext) {
        if (commandContext.getSource().getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) commandContext.getSource().getEntity();
            if (player.getActivePotionEffect(Effects.INVISIBILITY) != null) {
                if (player.getActivePotionEffect(Effects.INVISIBILITY).getAmplifier() == 99) {
                    player.setInvisible(false);
                    player.removePotionEffect(Effects.INVISIBILITY);
                    player.sendMessage(new StringTextComponent(References.PREFIX + TextFormatting.RED + "Tu n'es plus Vanish !"), DUMMY_UUID);
                }
            } else {
                player.setInvisible(true);
                player.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 99999 * 20, 99, false, false));
                player.sendMessage(new StringTextComponent(References.PREFIX + TextFormatting.GREEN + "Tu es Vanish !"), DUMMY_UUID);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}