package me.luligabi.ogmc.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)

public abstract class ClientPlayerEntityMixin {

    ClientPlayerEntity clientPlayerEntity = ((ClientPlayerEntity) (Object) this);

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void sendChatMessage(String message, CallbackInfo callbackInfo) {

        String gameModePrefix = "/gamemode ";
        clientPlayerEntity.networkHandler.sendPacket(new ChatMessageC2SPacket(
                message.replace(gameModePrefix + "0", gameModePrefix + "survival")
                        .replace(gameModePrefix + "1", gameModePrefix + "creative")
                        .replace(gameModePrefix + "2", gameModePrefix + "adventure")
                        .replace(gameModePrefix + "3", gameModePrefix + "spectator")));
        callbackInfo.cancel();
    }
}
