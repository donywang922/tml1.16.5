package bilibili.ywsuoyi.render;

import bilibili.ywsuoyi.danmaku.DanmakuColor;
import bilibili.ywsuoyi.danmaku.DanmakuType;
import bilibili.ywsuoyi.entity.danmakuentity;
import bilibili.ywsuoyi.model.objmodel;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.Matrix3f;
import net.minecraft.client.util.math.Matrix4f;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class danmakuEntityRender extends EntityRenderer<danmakuentity> {
    public static final float uunit = 0.076923f;
    public static final float vunit = 0.166666f;
    public static final String MOD_ID = "touhou_little_maid";
    private static final Identifier SINGLE_PLANE_DANMAKU = new Identifier(MOD_ID, "textures/entity/singe_plane_danmaku.png");
    private static final Identifier WHITE = new Identifier(MOD_ID, "textures/white.png");
    private static final Identifier AMULET_DANMAKU = new Identifier(MOD_ID, "textures/entity/amulet_danmaku.png");
    private static final Identifier STAR_DANMAKU = new Identifier(MOD_ID, "textures/entity/star_danmaku.png");
    private static final Identifier PETAL_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/petal.obj");
    private static final Identifier JELLYBEAN = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/jellybean.obj");
    private static final Identifier KNIFE_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/knife.obj");
    private static final Identifier BULLET_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/bullet_danmaku.obj");
    private static final Identifier RING = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/ring.obj");
    private static final Identifier GOSSIP = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/gossip.obj");
    private static final Identifier KUNAI_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/kunai.obj");
    private static final Identifier RAINDROP_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/raindrop.obj");
    private static final Identifier ARROWHEAD_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/arrowhead.obj");
    private static final Identifier BUTTERFLY_DANMAKU = new Identifier(MOD_ID, "/assets/touhou_little_maid/models/danmaku/butterfly.obj");
    private static final Identifier GLOWEY_BALL_DANMAKU = new Identifier(MOD_ID, "textures/entity/glowey_ball.png");
    private static objmodel<danmakuentity> PETAL_QUADS;
    private static objmodel<danmakuentity> JELLYBEAN_QUADS;
    private static objmodel<danmakuentity> KNIFE_QUADS;
    private static objmodel<danmakuentity> BULLET_QUADS;
    private static objmodel<danmakuentity> RING_QUADS;
    private static objmodel<danmakuentity> GOSSIP_QUADS;
    private static objmodel<danmakuentity> KUNAI_QUADS;
    private static objmodel<danmakuentity> RAINDROP_QUADS;
    private static objmodel<danmakuentity> ARROWHEAD_QUADS;
    private static objmodel<danmakuentity> BUTTERFLY_QUADS;

    public danmakuEntityRender(EntityRenderDispatcher renderManagerIn) {
        super(renderManagerIn);
        PETAL_QUADS = new objmodel<>(PETAL_DANMAKU);
        JELLYBEAN_QUADS = new objmodel<>(JELLYBEAN);
        KNIFE_QUADS = new objmodel<>(KNIFE_DANMAKU);
        BULLET_QUADS = new objmodel<>(BULLET_DANMAKU);
        RING_QUADS = new objmodel<>(RING);
        GOSSIP_QUADS = new objmodel<>(GOSSIP);
        KUNAI_QUADS = new objmodel<>(KUNAI_DANMAKU);
        RAINDROP_QUADS = new objmodel<>(RAINDROP_DANMAKU);
        ARROWHEAD_QUADS = new objmodel<>(ARROWHEAD_DANMAKU);
        BUTTERFLY_QUADS = new objmodel<>(BUTTERFLY_DANMAKU);
    }


    @Override
    public boolean shouldRender(danmakuentity entity, Frustum visibleRegion, double cameraX, double cameraY, double cameraZ) {
        if (entity.getdanmakuType() == DanmakuType.MASTER_SPARK) {
            return true;
        }
        return super.shouldRender(entity, visibleRegion, cameraX, cameraY, cameraZ);
    }

    @Override
    public void render(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        DanmakuType type = entity.getdanmakuType();
        switch (type) {
            case PELLET:
            case BALL:
            case ORBS:
            case BIG_BALL:
            case BUBBLE:
            case HEART:
                renderSinglePlaneDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, type);
                break;
            case JELLYBEAN:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, JELLYBEAN_QUADS,1.5f,0.01f);
                break;
            case AMULET:
                renderAmuletDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light);
                break;
            case STAR:
            case BIG_STAR:
                renderStarDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light);
                break;
            case PETAL:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, PETAL_QUADS,1.5f,0.01f);
                break;
            case KNIFE:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, KNIFE_QUADS,1.5f,-0.21f);
                break;
            case MASTER_SPARK:
                renderMasterSparkDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light);
                break;
            case BULLET:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, BULLET_QUADS,1.5f,-0.01f);
                break;
            case KUNAI:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, KUNAI_QUADS,1.5f,0.045f);
                break;
            case RAINDROP:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, RAINDROP_QUADS,1.5f,0.05f);
                break;
            case ARROWHEAD:
                renderObjDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light, ARROWHEAD_QUADS,1.3f,0.02f);
                break;
            case BUTTERFLY:
                renderButterflyDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light);
                break;
            case GLOWEY_BALL:
                renderGloweyBallDanmaku(entity, yaw, tickDelta, matrices, vertexConsumers, light);
                break;
            default:
                super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
        }
    }


    private static void method_23837(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, int i, float f, float j, float k, float l) {
        vertexConsumer
                .vertex(matrix4f, f - 0.5F, j - 0.5f, 0.0F)
                .color(255, 255, 255, 255)
                .texture(k, l)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(i)
                .normal(0.0F, 1.0F, 0.0F)
                .next();
    }

    private static void method_23838(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, int i, float f, float j, double k, int l) {
        vertexConsumer
                .vertex(matrix4f, f - 0.5f, 0, j - 0.5f)
                .color(255, 255, 255, 255)
                .texture((float) k, (float) l)
                .overlay(OverlayTexture.DEFAULT_UV)
                .light(i)
                .normal(matrix3f, 0.0F, 1.0F, 0.0F)
                .next();
    }

    private void renderSinglePlaneDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, DanmakuType type) {
        // 获取相关数据
        DanmakuColor color = entity.getdanmakuColor();
//        // 材质宽度
//        int w = 416;
//        // 材质长度
//        int l = 192;

        // 依据类型颜色开始定位材质位置（材质块都是 32 * 32 大小）
        float u = uunit * color.ordinal() + 1;
        float v = vunit * type.ordinal() + 1;

        matrixStack.push();
        matrixStack.scale(1.0F, 1.0F, 1.0F);
        matrixStack.multiply(this.renderManager.getRotation());
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucentCull(danmakuEntityRender.SINGLE_PLANE_DANMAKU));
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 0, u, v + vunit);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 0, u + uunit, v + vunit);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 1, u + uunit, v);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 1, u, v);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);

    }

    private void renderAmuletDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                     VertexConsumerProvider vertexConsumerProvider, int light) {
        DanmakuColor color = entity.getdanmakuColor();
        // 材质宽度
        float u = uunit * color.ordinal();
        start(entity, tickDelta, matrixStack);
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(AMULET_DANMAKU));
        method_23838(vertexConsumer, matrix4f, matrix3f, light, 0, 0, u, 1);
        method_23838(vertexConsumer, matrix4f, matrix3f, light, 1, 0, u + uunit, 1);
        method_23838(vertexConsumer, matrix4f, matrix3f, light, 1, 1, u + uunit, 0);
        method_23838(vertexConsumer, matrix4f, matrix3f, light, 0, 1, u, 0);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);

    }

    private void start(danmakuentity entity, float tickDelta, MatrixStack matrixStack) {
        matrixStack.push();
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw)));
        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(0 - MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)));
    }

    private void startobj(danmakuentity entity, float tickDelta, MatrixStack matrixStack) {
        matrixStack.push();
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw)));
        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(90 - MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)));


    }

    private void renderStarDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                   VertexConsumerProvider vertexConsumerProvider, int light) {
        // 获取相关数据
        DanmakuColor color = entity.getdanmakuColor();
        DanmakuType type = entity.getdanmakuType();

        float u = uunit * color.ordinal();
        float v = 0.5f * (type == DanmakuType.BIG_STAR ? 0 : 1);

        matrixStack.push();
        matrixStack.scale(1.0F, 1.0F, 1.0F);
        matrixStack.multiply(this.renderManager.getRotation());
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((entity.time) * 5));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucentCull(danmakuEntityRender.STAR_DANMAKU));
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 0, u, v + 0.5f);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 0, u + uunit, v + 0.5f);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 1, u + uunit, v);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 1, u, v);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }


    private void renderMasterSparkDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                          VertexConsumerProvider vertexConsumerProvider, int light) {

        DanmakuColor color = entity.getdanmakuColor();
        float num = (float) (20 * (Math.atan((entity.time - 50f) / 2) + 1.6));

        matrixStack.push();
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) - 90.0F));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)+90));

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(GOSSIP_QUADS.getLayer(WHITE));

        matrixStack.scale(16, 16, 16);
        GOSSIP_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 0.1f, 0.1f, 0.1f, 1.0F);

        vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(WHITE));

        matrixStack.translate(0, -0.1, 0);
        matrixStack.scale(num * 0.15f, num * 0.15f, num * 0.15f);
        RING_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1.0F, 0, 0, 1.0F);

        matrixStack.translate(0, -0.1, 0);
        matrixStack.scale(1.01f, 1.01f,1.01f);
        RING_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 0, 1.0F, 0, 1.0F);

        matrixStack.translate(0, -0.1, 0);
        matrixStack.scale(1.01f, 1.01f, 1.01f);
        RING_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 00F, 0, 1.0F, 1.0F);

        float back = 1/(num*0.15f);
        matrixStack.scale(0.99f,0.99f,0.99f);
        matrixStack.translate(0, 0.1, 0);
        matrixStack.scale(0.99f,0.99f,0.99f);
        matrixStack.translate(0, 0.1, 0);
        matrixStack.scale(back,back,back);
        matrixStack.translate(0, 0.05, 0);

        matrixStack.scale(num * 0.1f, 16f, num * 0.1f);
        vertexConsumer = vertexConsumerProvider.getBuffer(BULLET_QUADS.getLayer(WHITE));
        BULLET_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1,1,1, 1.0F);
        vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        BULLET_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, 1,1,1, 1.0F);
        matrixStack.translate(0, 0.001, 0);
        matrixStack.scale(1.2f, 1.2f, 1.2f);
        vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        BULLET_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(),  1.0F);
        matrixStack.translate(0, 0.001, 0);
        matrixStack.scale(1.2f, 1.2f, 1.2f);
        BULLET_QUADS.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(), 1.0F);

        matrixStack.pop();

        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);

    }

    private void renderObjDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                  VertexConsumerProvider vertexConsumerProvider, int light, objmodel<danmakuentity> model,float s, float t) {
        DanmakuColor color = entity.getdanmakuColor();
        startobj(entity, tickDelta, matrixStack);
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV,1, 1, 1, 1.0F);
        vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        matrixStack.scale(s,s,s);
        matrixStack.translate(0,-t,0);
        model.render(matrixStack, vertexConsumer, light, OverlayTexture.DEFAULT_UV, color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(), 1.0F);

        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);

    }

    private void renderButterflyDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                        VertexConsumerProvider vertexConsumerProvider, int light) {
        DanmakuColor color = entity.getdanmakuColor();
//        System.out.println(color);
        matrixStack.push();
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(MathHelper.lerp(tickDelta, entity.prevYaw, entity.yaw) - 180));
        matrixStack.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(0 - MathHelper.lerp(tickDelta, entity.prevPitch, entity.pitch)));
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(0 - (10 * MathHelper.cos((entity.time) / 4f) + 20)));
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucent(WHITE));
        BUTTERFLY_QUADS.render(matrixStack, vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV, color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(), 1.0F);
        matrixStack.scale(-1, 1, 1);
        matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(-(20 * MathHelper.cos((entity.time) / 4f) + 40)));
        BUTTERFLY_QUADS.render(matrixStack, vertexConsumer, 15728880, OverlayTexture.DEFAULT_UV, color.getFloatRed(), color.getFloatGreen(), color.getFloatBlue(), 1.0F);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }

    private void renderGloweyBallDanmaku(danmakuentity entity, float yaw, float tickDelta, MatrixStack matrixStack,
                                         VertexConsumerProvider vertexConsumerProvider, int light) {
        DanmakuColor color = entity.getdanmakuColor();

        float u = uunit * color.ordinal();

        matrixStack.push();
        matrixStack.scale(1.0F, 1.0F, 1.0F);
        matrixStack.multiply(this.renderManager.getRotation());
        matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
        MatrixStack.Entry entry = matrixStack.peek();
        Matrix4f matrix4f = entry.getModel();
        Matrix3f matrix3f = entry.getNormal();
        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getEntityTranslucentCull(danmakuEntityRender.GLOWEY_BALL_DANMAKU));
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 0, u, 1);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 0, u + uunit, 1);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 1, 1, u + uunit, 0);
        method_23837(vertexConsumer, matrix4f, matrix3f, light, 0, 1, u, 0);
        matrixStack.pop();
        super.render(entity, yaw, tickDelta, matrixStack, vertexConsumerProvider, light);
    }

    public int getBlockLight(danmakuentity entity, float tickDelta) {
        return 15;
    }

    @Override
    public Identifier getTexture(danmakuentity entity) {
        DanmakuType type = entity.getdanmakuType();
        switch (type) {
            case PELLET:
            case BALL:
            case ORBS:
            case BIG_BALL:
            case BUBBLE:
            case HEART:
                return SINGLE_PLANE_DANMAKU;
            case AMULET:
                return AMULET_DANMAKU;
            case STAR:
            case BIG_STAR:
                return STAR_DANMAKU;
            case GLOWEY_BALL:
                return GLOWEY_BALL_DANMAKU;
            default:
                return WHITE;
        }
    }
}
