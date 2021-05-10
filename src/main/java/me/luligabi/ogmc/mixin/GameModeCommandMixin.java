package me.luligabi.ogmc.mixin;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.GameModeCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Collections;

@Mixin(GameModeCommand.class)
public abstract class GameModeCommandMixin {

    @Inject(method = "register",
            at = @At("HEAD"),
            cancellable = true)
    private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CallbackInfo callbackInfo) {
        LiteralArgumentBuilder<ServerCommandSource> literalArgumentBuilder = (LiteralArgumentBuilder) CommandManager.literal("gamemode").requires((serverCommandSource) ->
                serverCommandSource.hasPermissionLevel(2));
        GameMode[] var2 = GameMode.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            GameMode gameMode = var2[var4];
            if (gameMode != GameMode.NOT_SET) {
                literalArgumentBuilder.then(((LiteralArgumentBuilder)CommandManager.literal(String.valueOf(gameMode.getId())).executes((commandContext) ->
                        GameModeInvoker.execute(commandContext, Collections.singleton(commandContext.getSource().getPlayer()), gameMode)))
                        .then(CommandManager.argument("target", EntityArgumentType.players()).executes((commandContext) ->
                        GameModeInvoker.execute(commandContext, EntityArgumentType.getPlayers(commandContext, "target"), gameMode))));
            }
        }

        dispatcher.register(literalArgumentBuilder);

        callbackInfo.cancel();
    }
}
