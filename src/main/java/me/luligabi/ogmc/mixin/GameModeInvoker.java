package me.luligabi.ogmc.mixin;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.GameModeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Collection;

@Mixin(GameModeCommand.class)
public interface GameModeInvoker {

    @Invoker("execute")
    static int execute(CommandContext<ServerCommandSource> context, Collection<ServerPlayerEntity> targets, GameMode gameMode) {
        throw new AssertionError();
    }
}
