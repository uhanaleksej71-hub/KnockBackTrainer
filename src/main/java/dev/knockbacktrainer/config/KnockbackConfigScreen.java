package dev.knockbacktrainer.config;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class KnockbackConfigScreen extends Screen {

    public KnockbackConfigScreen() {
        super(Text.of("Knockback Trainer Settings"));
    }

    @Override
    protected void init() {
        int y = 40;

        this.addDrawableChild(new SliderWidget(20, y, 200, 20,
                Text.of("Yaw Offset: " + KnockbackConfig.yawOffset),
                0f, 90f, KnockbackConfig.yawOffset,
                slider -> KnockbackConfig.yawOffset = slider.getValue()));
        y += 30;

        this.addDrawableChild(new SliderWidget(20, y, 200, 20,
                Text.of("Delay Ticks: " + KnockbackConfig.delayTicks),
                0, 5, KnockbackConfig.delayTicks,
                slider -> KnockbackConfig.delayTicks = (int) slider.getValue()));
        y += 30;

        this.addDrawableChild(new ButtonWidget(20, y, 200, 20,
                Text.of("Back"), button -> this.client.setScreen(null)));
    }
}
