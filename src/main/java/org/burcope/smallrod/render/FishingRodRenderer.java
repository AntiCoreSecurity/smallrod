package org.burcope.smallrod.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.burcope.smallrod.client.SmallrodClient;

public class FishingRodRenderer extends FishingBobberEntityRenderer {

    public FishingRodRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public void render(FishingBobberEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        // 사이즈조정
        float scale = SmallrodClient.isEnabled() ? 0.4f : 1.0f;
        matrices.scale(scale, scale, scale);

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        matrices.pop();
    }
    @Override
    public Identifier getTexture(FishingBobberEntity entity) {
        return new Identifier("minecraft", "textures/entity/fishing_hook.png");
    }
}