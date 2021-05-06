package bilibili.ywsuoyi.client.model;

import bilibili.ywsuoyi.client.resources.animation.AbstractAnimation;
import bilibili.ywsuoyi.client.resources.model.*;
import bilibili.ywsuoyi.entity.maid.MaidEntity;
import com.github.tartaricacid.touhoulittlemaid.client.animation.script.GlStateManager;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.CompositeEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.jetbrains.annotations.Nullable;

import javax.script.Invocable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class MaidModel extends CompositeEntityModel<MaidEntity> {
    private final HashMap<String, JsModelPart> modelMap = new HashMap<>();//模型列表
    private final List<Object> animations;//动画列表
    private final HashMap<String, BonesItem> indexBones = new HashMap<>();//骨骼列表,用于坐标转换
    private final List<ModelPart> shouldRender = new LinkedList<>();//根模型的列表


    public MaidModel(JsonModel jsonModel, List<Object> animation) {
        this.animations = animation;

        JsonModelPart jsonModelPart = jsonModel.getGeometryModel();


        // 材质的长度、宽度
        textureWidth = jsonModelPart.getTexturewidth();
        textureHeight = jsonModelPart.getTextureheight();


        // 往 indexBones 里面注入数据，为后续坐标转换做参考

        for (BonesItem bones : jsonModelPart.getBones()) {
            // 塞索引，这是给后面坐标转换用的
            indexBones.put(bones.getName(), bones);
            // 塞入新建的空 ModelRenderer 实例
            // 因为后面添加 parent 需要，所以先塞空对象，然后二次遍历再进行数据存储
            modelMap.put(bones.getName(), new JsModelPart(this));
        }

        // 开始往 ModelRenderer 实例里面塞数据
        for (BonesItem bones : jsonModelPart.getBones()) {
            // 骨骼名称，注意因为后面动画的需要，头部、手部、腿部等骨骼命名必须是固定死的
            String name = bones.getName();
            // 旋转点，可能为空
            @Nullable List<Float> rotation = bones.getRotation();
            // 父骨骼的名称，可能为空
            @Nullable String parent = bones.getParent();
            // 塞进 HashMap 里面的模型对象
            JsModelPart model = modelMap.get(name);

            // 镜像参数
            model.mirror = bones.isMirror();

            // 旋转点
            model.setPivot(convertPivot(bones, 0), convertPivot(bones, 1), convertPivot(bones, 2));

            // Nullable 检查，设置旋转角度
            if (rotation != null) {
                model.pitch = (float) Math.toRadians(rotation.get(0));
                model.yaw = (float) Math.toRadians(rotation.get(1));
                model.roll = (float) Math.toRadians(rotation.get(2));
            }

            // Null 检查，进行父骨骼绑定
            if (parent != null) {
                modelMap.get(parent).addChild(model);
            } else {
                // 没有父骨骼的模型才进行渲染
                shouldRender.add(model);
            }

            // 我的天，Cubes 还能为空……
            if (bones.getCubes() == null) {
                continue;
            }

            // 塞入 Cube List
            for (CubesItem cubes : bones.getCubes()) {
                List<Float> uv = cubes.getUv();
                List<Float> size = cubes.getSize();
                float extra = cubes.getInflate();
                boolean mirror = cubes.isMirror();
                model.addCuboid(uv.get(0).intValue(), uv.get(1).intValue(),
                        convertOrigin(bones, cubes, 0), convertOrigin(bones, cubes, 1), convertOrigin(bones, cubes, 2),
                        size.get(0), size.get(1), size.get(2),
                        extra, mirror
                );
            }
        }


    }

    @Override
    public Iterable<ModelPart> getParts() {
        return shouldRender;
    }

    @Override
    public void setAngles(MaidEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        if (animations.isEmpty()) {
            return;
        }
        Invocable invocable = (Invocable) ModelManager.scriptEngine;
        if (entity != null) {
            for (Object animation : animations) {
                if (animation instanceof AbstractAnimation) {
                    ((AbstractAnimation) animation).setRotationAngles(limbAngle, limbDistance, animationProgress, headYaw, headPitch, entity, modelMap);
                } else {
                    try {
                        invocable.invokeMethod(animation, "animation",
                                entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch, 0.0625, modelMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (entity.isSleep()) {
                GlStateManager.rotate(180, 0, 1, 0);
                GlStateManager.rotate(-90, 1, 0, 0);
                GlStateManager.translate(0, -1.08, 1.3);
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.push();
        matrices.translate(GlStateManager.translate.getX(),GlStateManager.translate.getY(),GlStateManager.translate.getZ());
        matrices.multiply(GlStateManager.rotate);
        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        matrices.pop();
    }



    /**
     * 基岩版的旋转中心计算方式和 Java 版不太一样，需要进行转换
     * <p>
     * 如果有父模型
     * <li>x，z 方向：本模型坐标 - 父模型坐标
     * <li>y 方向：父模型坐标 - 本模型坐标
     * <p>
     * 如果没有父模型
     * <li>x，z 方向不变
     * <li>y 方向：24 - 本模型坐标
     *
     * @param index 是 xyz 的哪一个，x 是 0，y 是 1，z 是 2
     */
    private float convertPivot(BonesItem bones, int index) {
        if (bones.getParent() != null) {
            if (index == 1) {
                return indexBones.get(bones.getParent()).getPivot().get(index) - bones.getPivot().get(index);
            } else {
                return bones.getPivot().get(index) - indexBones.get(bones.getParent()).getPivot().get(index);
            }
        } else {
            if (index == 1) {
                return 24 - bones.getPivot().get(index);
            } else {
                return bones.getPivot().get(index);
            }
        }
    }

    /**
     * 基岩版和 Java 版本的方块起始坐标也不一致，Java 是相对坐标，而且 y 值方向不一致。
     * 基岩版是绝对坐标，而且 y 方向朝上。
     * 其实两者规律很简单，但是我找了一下午，才明白咋回事。
     * <li>如果是 x，z 轴，那么只需要方块起始坐标减去旋转点坐标
     * <li>如果是 y 轴，旋转点坐标减去方块起始坐标，再减去方块的 y 长度
     *
     * @param index 是 xyz 的哪一个，x 是 0，y 是 1，z 是 2
     */
    private float convertOrigin(BonesItem bones, CubesItem cubes, int index) {
        if (index == 1) {
            return bones.getPivot().get(index) - cubes.getOrigin().get(index) - cubes.getSize().get(index);
        } else {
            return cubes.getOrigin().get(index) - bones.getPivot().get(index);
        }
    }
}

