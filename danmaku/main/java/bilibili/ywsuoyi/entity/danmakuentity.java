package bilibili.ywsuoyi.entity;

import bilibili.ywsuoyi.danmaku.DanmakuColor;
import bilibili.ywsuoyi.danmaku.DanmakuType;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class danmakuentity extends LivingEntity {

    public static final TrackedData<Integer> t = DataTracker.registerData(danmakuentity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<Integer> c = DataTracker.registerData(danmakuentity.class, TrackedDataHandlerRegistry.INTEGER);


    public LivingEntity owner;
    public float damage;
    public int time;
    private boolean damages_treeain;

    public static final EntityType<danmakuentity> DANMAKU_ENTITY =
            Registry.register(
                    Registry.ENTITY_TYPE,
                    new Identifier("touhou_little_maid", "damakuentity"),
                    FabricEntityTypeBuilder
                            .create(EntityCategory.MISC, (EntityType.EntityFactory<danmakuentity>) danmakuentity::new)
                            .dimensions(EntityDimensions.fixed(1, 2))
                            .build()
            );

    protected danmakuentity(EntityType<? extends LivingEntity> type, World world) {
        super(type, world);
    }


    public danmakuentity(World world, LivingEntity owner, float d, float s, DanmakuType type, DanmakuColor color) {
        this(DANMAKU_ENTITY, world);
        this.setProperties(owner.pitch, owner.yaw, s + 1);
        this.updatePosition(owner.getX() + this.getVelocity().getX(), owner.getY()+1.5 + this.getVelocity().getY(), owner.getZ() + this.getVelocity().getZ());
//        System.out.println(color);
        this.dataTracker.set(t, type.ordinal());
        this.dataTracker.set(c, color.ordinal());
        this.damage = d;
        this.owner = owner;

    }

    public danmakuentity(World world, LivingEntity owner, DanmakuType type, DanmakuColor color) {
        this(DANMAKU_ENTITY, world);
        this.updatePosition(owner.getX(), owner.getY(), owner.getZ());
        this.owner = owner;
        this.dataTracker.set(t, type.ordinal());
        this.dataTracker.set(c, color.ordinal());
    }

    @Override
    public boolean shouldRender(double distance) {
        return true;
    }

    public void shoot(double x, double y, double z, float s) {
        this.setVelocity(x, y, z, s);
    }

    @Override
    public void tick() {
        super.baseTick();
        this.time++;
    }

    @Override
    public Arm getMainArm() {
        return Arm.RIGHT;
    }

    public DanmakuType getdanmakuType() {
        return DanmakuType.getType(this.dataTracker.get(t));
    }

    public void setdanmakuType(DanmakuType type) {
        this.dataTracker.set(t, type.ordinal());
    }

    public DanmakuColor getdanmakuColor() {
        return DanmakuColor.getColor(this.dataTracker.get(c));
    }

    public void setdanmakuColor(DanmakuColor color) {
        this.dataTracker.set(c, color.ordinal());
    }

    public float getdanmakuDamage() {
        return this.damage;
    }

    public void setdanmakuDamage(float damage) {
        this.damage = damage;
    }

    public int getDanmakuTicksExisted() {
        return this.time;
    }

    public void setDanmakuTicksExisted(int ticksExisted) {
        this.time = ticksExisted;
    }

    public boolean isDamagesTerrain() {
        return this.damages_treeain;
    }

    public void setDamagesTerrain(boolean isDamagesTerrain) {
        this.damages_treeain = isDamagesTerrain;
    }


    @Override
    public void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(c, 0);
        this.dataTracker.startTracking(t, 0);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        dataTracker.set(c, tag.getInt("c"));
        dataTracker.set(t, tag.getInt("t"));
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putInt("t", dataTracker.get(t));
        tag.putInt("c", dataTracker.get(c));

    }

    public void setProperties(float pitch, float yaw, float g) {
        float i = -MathHelper.sin(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        float j = -MathHelper.sin((pitch) * 0.017453292F);
        float k = MathHelper.cos(yaw * 0.017453292F) * MathHelper.cos(pitch * 0.017453292F);
        this.setVelocity(i, j, k, g);
    }

    public void setVelocity(double x, double y, double z, float speed) {
        Vec3d vec3d = (new Vec3d(x, y, z)).normalize().multiply(speed);
        this.setVelocity(vec3d);
        float f = MathHelper.sqrt(squaredHorizontalLength(vec3d));
        this.yaw = (float) (MathHelper.atan2(vec3d.x, vec3d.z) * 57.2957763671875D);
        this.pitch = (float) (MathHelper.atan2(vec3d.y, f) * 57.2957763671875D);
        this.prevYaw = this.yaw;
        this.prevPitch = this.pitch;
    }

    public boolean damage(DamageSource source, float amount) {
        this.remove();
        return false;

    }
}
