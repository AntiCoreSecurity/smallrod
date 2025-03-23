package org.burcope.smallrod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.EntityType;
import net.minecraft.text.Text;
import org.burcope.smallrod.render.FishingRodRenderer;
import org.lwjgl.glfw.GLFW;

import java.util.logging.Logger;

public class SmallrodClient implements ClientModInitializer {
    public static boolean enabled = true;
    private static KeyBinding toggleKey;

    public static boolean isEnabled() {
        return enabled;
    }

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "SmallRod 토글",
                GLFW.GLFW_KEY_N,
                "SmallRod"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world == null) return;

            while (toggleKey.wasPressed()) {
                 enabled = !enabled;
                 if (client.player != null) {
                     Text message = Text.literal(enabled ? "§a§l[SmallRod 활성화]" : "§c§l[SmallRod 비활성화]");
                     client.player.sendMessage(message, false);
                 }
            }
        });
        EntityRendererRegistry.register(EntityType.FISHING_BOBBER, FishingRodRenderer::new);
    }
}
