package bilibili.ywsuoyi.danmaku;

import bilibili.ywsuoyi.entity.danmakuentity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public final class DanmakuShoot {
    private static Random RANDOM = new Random();

    /**
     * 自机狙弹幕
     *
     * @param worldIn    发射者所处世界
     * @param thrower    发射者
     * @param speed   弹幕速度
     * @param target     发射朝向的目标
     * @param damage     弹幕伤害数值
     * @param type       弹幕类型
     * @param color      弹幕颜色
     */
    public static void aimedShot(World worldIn, LivingEntity thrower, LivingEntity target, float damage, float speed, DanmakuType type, DanmakuColor color) {
        if (!worldIn.isClient) {
            danmakuentity danmaku = new danmakuentity(worldIn, thrower, damage, speed, type, color);
            float offset = 0.3f / target.getHeight();
            danmaku.shoot(target.getX() - thrower.getX(), target.getY() - thrower.getY() - offset, target.getZ() - thrower.getZ(), speed);
            worldIn.spawnEntity(danmaku);
        }
        worldIn.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, thrower.getSoundCategory(), 1.0f, 0.8f);
    }


    /**
     * 扇形弹弹幕
     *
     * @param worldIn    发射者所处世界
     * @param thrower    发射者
     * @param target     发射朝向的目标
     * @param damage     弹幕伤害数值
     * @param speed   弹幕速度
     * @param type       弹幕类型
     * @param color      弹幕颜色
     * @param yawTotal   偏航总角度，弧度表示（以发射者和发射目标为中心左右对称）
     * @param fanNum     扇形弹链数（fanNum >=2）
     */
    public static void fanShapedShot(World worldIn, LivingEntity thrower, LivingEntity target, float damage, Float speed, DanmakuType type, DanmakuColor color, double yawTotal, int fanNum) {
        if (yawTotal < 0 || yawTotal > 2 * Math.PI || fanNum < 2) {
            return;
        }

        if (!worldIn.isClient) {
            float offset = 0.3f / target.getHeight();
            Vec3d v = new Vec3d(target.getX() - thrower.getX(), target.getY() - thrower.getY() - offset, target.getZ() - thrower.getZ());
            double yaw = -(yawTotal / 2);
            double addYaw = yawTotal / (fanNum - 1);
            for (int i = 1; i <= fanNum; i++) {
                Vec3d v1 = v.rotateX((float) yaw);
                yaw = yaw + addYaw;

                danmakuentity danmaku = new danmakuentity(worldIn, thrower, damage, speed, type, color);
                danmaku.shoot(v1.x, v1.y, v1.z, speed);
                worldIn.spawnEntity(danmaku);
            }
        }
        worldIn.playSound(null, thrower.getX(), thrower.getY(), thrower.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, thrower.getSoundCategory(), 1.0f, 0.8f);
    }
}
