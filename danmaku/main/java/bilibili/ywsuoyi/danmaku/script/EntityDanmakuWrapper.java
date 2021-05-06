package bilibili.ywsuoyi.danmaku.script;

import bilibili.ywsuoyi.danmaku.DanmakuColor;
import bilibili.ywsuoyi.danmaku.DanmakuType;
import bilibili.ywsuoyi.entity.danmakuentity;
import net.minecraft.util.math.MathHelper;

/**
 * @author TartaricAcid
 * @date 2019/11/26 14:41
 **/
public class EntityDanmakuWrapper {
    private danmakuentity danmaku;

    public EntityDanmakuWrapper(WorldWrapper worldIn, EntityLivingBaseWrapper throwerIn, DanmakuType type, DanmakuColor color) {
        this.danmaku = new danmakuentity(worldIn.getWorld(), throwerIn.getLivingBase(), type, color);
    }

    public EntityDanmakuWrapper(WorldWrapper worldIn, EntityLivingBaseWrapper throwerIn, float damage, float gravity, DanmakuType type, DanmakuColor color) {
        this.danmaku = new danmakuentity(worldIn.getWorld(), throwerIn.getLivingBase(), damage, gravity, type, color);
    }

    public void setTicksExisted(int ticksExisted) {
        this.danmaku.setDanmakuTicksExisted(ticksExisted);
    }

    public void setType(DanmakuType type) {
        this.danmaku.setdanmakuType(type);
    }

    public void setColor(DanmakuColor color) {
        this.danmaku.setdanmakuColor(color);
    }


    public void shoot(double x, double y, double z, float velocity) {
        this.danmaku.shoot(x, y, z, velocity);
    }

    public void setPosition(Vec3dWrapper vec3d) {
        this.danmaku.setPos(vec3d.getX(), vec3d.getY(), vec3d.getZ());
    }

    public void setMotion(Vec3dWrapper motion) {
        danmaku.prevX = motion.getX();
        danmaku.prevY = motion.getY();
        danmaku.prevZ = motion.getZ();

        if (danmaku.prevYaw == 0.0F && danmaku.prevPitch == 0.0F) {
            float f = MathHelper.sqrt(danmaku.prevX * danmaku.prevX + danmaku.prevZ * danmaku.prevZ);
            danmaku.yaw = (float) (MathHelper.atan2(danmaku.prevX, danmaku.prevZ) * (180D / Math.PI));
            danmaku.pitch = (float) (MathHelper.atan2(danmaku.prevY, f) * (180D / Math.PI));
            danmaku.prevYaw = danmaku.yaw;
            danmaku.prevPitch = danmaku.pitch;
        }
    }

    public danmakuentity getDanmaku() {
        return this.danmaku;
    }

    public void setDamagesTerrain(boolean canDamages) {
        this.danmaku.setDamagesTerrain(canDamages);
    }
}
