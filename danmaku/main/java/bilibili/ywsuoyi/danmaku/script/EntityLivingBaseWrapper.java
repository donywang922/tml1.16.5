package bilibili.ywsuoyi.danmaku.script;

import net.minecraft.entity.LivingEntity;

/**
 * @author TartaricAcid
 * @date 2019/11/26 14:43
 **/
public class EntityLivingBaseWrapper {
    private LivingEntity livingBase;

    public EntityLivingBaseWrapper(LivingEntity livingBase) {
        this.livingBase = livingBase;
    }

    public LivingEntity getLivingBase() {
        return this.livingBase;
    }

    public float getYaw() {
        return this.livingBase.headYaw;
    }

    public float getPitch() {
        return this.livingBase.pitch;
    }

    public Vec3dWrapper getPos() {
        return new Vec3dWrapper(this.livingBase.getPos());
    }
}
