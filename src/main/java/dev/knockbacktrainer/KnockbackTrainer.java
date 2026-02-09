package dev.knockbacktrainer;

import dev.knockbacktrainer.config.KnockbackConfig;
import net.fabricmc.api.ClientModInitializer;

public class KnockbackTrainer implements ClientModInitializer {
    public static boolean enabled = false;

    @Override
    public void onInitializeClient() {
        ModKeybinds.register();
        AttackInterceptor.register();
        System.out.println("[KnockbackTrainer] Loaded");
    }
}
