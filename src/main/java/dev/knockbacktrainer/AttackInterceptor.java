package dev.knockbacktrainer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class AttackInterceptor {

    private static int tickTimer = 0;
    private static float originalYaw;
    private static float originalPitch;
    private static Entity storedTarget;

    public static void register() {

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {

            if (!KnockbackTrainer.enabled) return ActionResult.PASS;
            if (hand != Hand.MAIN_HAND) return ActionResult.PASS;

            storedTarget = entity;

            originalYaw = player.getYaw();
            originalPitch = player.getPitch();

            // Поворот головы
            player.setYaw(originalYaw + dev.knockbacktrainer.config.KnockbackConfig.yawOffset);

            tickTimer = dev.knockbacktrainer.config.KnockbackConfig.delayTicks;

            return ActionResult.FAIL;
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (tickTimer > 0) {
                tickTimer--;
                return;
            }

            if (storedTarget != null && client.player != null) {
                client.player.setYaw(originalYaw);
                client.player.setPitch(originalPitch);

                MinecraftClient.getInstance()
                        .interactionManager
                        .attackEntity(client.player, storedTarget);
                client.player.swingHand(Hand.MAIN_HAND);

                storedTarget = null;
            }
        });
    }
}
