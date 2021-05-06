package bilibili.ywsuoyi;

import bilibili.ywsuoyi.render.danmakuEntityRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.ArmorStandEntityRenderer;
import net.minecraft.client.render.entity.DragonFireballEntityRenderer;

import static bilibili.ywsuoyi.entity.danmakuentity.DANMAKU_ENTITY;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(DANMAKU_ENTITY, (entityRenderDispatcher, context) -> new danmakuEntityRender(entityRenderDispatcher));
    }
}
