package dev.knockbacktrainer;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;

public class ModKeybinds {
    private static KeyBinding toggle;

    public static void register() {
        toggle = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.knockbacktrainer.toggle",
                GLFW.GLFW_KEY_R,
                "category.knockbacktrainer"
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggle.wasPressed()) {
                KnockbackTrainer.enabled = !KnockbackTrainer.enabled;
                System.out.println("KnockbackTrainer: " + (KnockbackTrainer.enabled ? "ON" : "OFF"));
            }
        });
    }
}
