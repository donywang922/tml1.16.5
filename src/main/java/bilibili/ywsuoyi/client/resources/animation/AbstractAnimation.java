package bilibili.ywsuoyi.client.resources.animation;


import bilibili.ywsuoyi.client.model.JsModelPart;
import bilibili.ywsuoyi.client.model.MaidModel;
import bilibili.ywsuoyi.entity.maid.MaidEntity;
import net.minecraft.entity.Entity;

import java.util.HashMap;


public abstract class AbstractAnimation<T extends Entity> {
    /**
     * 实体动画
     */
    public abstract void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
                                   float netHeadYaw, float headPitch, T entityIn, HashMap<String, JsModelPart> modelMap);
}
