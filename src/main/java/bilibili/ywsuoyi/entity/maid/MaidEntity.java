package bilibili.ywsuoyi.entity.maid;

import bilibili.ywsuoyi.entity.task.MaidTask;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MaidEntity extends TameableEntity implements RangedAttackMob {
    public float swingProgress = 0;
    public boolean isDebugBroomShow = false;


    public MaidEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);

    }

    public static DefaultAttributeContainer.Builder createMaidAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 1).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        setOwner(player);
        return ActionResult.SUCCESS;
    }

    @Override
    public void tick() {
        super.tick();
        this.swingProgress = this.handSwingProgress;
    }

    @Override
    public void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(2, new SitGoal(this));
        this.goalSelector.add(2, new FollowOwnerGoal(this, 1.0D, 2, 1, false));
    }

    public boolean isSleep() {
        return false;
    }

    public boolean isBegging() {
        return false;
    }

    public boolean onHurt() {
        return false;
    }

    @Override
    public void attack(LivingEntity target, float pullProgress) {

    }


    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    public MaidTask getTask() {
        return new MaidTask();
    }

    public boolean isSitInJoyBlock() {
        return false;
    }

    public boolean isSwingingArms() {
        return false;
    }

    public boolean isRiding() {
        return this.getVehicle() != null;
    }

    public boolean hasHelmet() {
        return false;
    }

    public boolean hasChestPlate() {
        return false;
    }

    public boolean hasLeggings() {
        return false;
    }

    public boolean hasBoots() {
        return false;
    }

    public String getAtBiomeTemp() {
        return world.getBiome(this.getBlockPos()).getCategory().name();
    }

    public double getArmorValue() {

        return getAttributeValue(EntityAttributes.GENERIC_ARMOR);
    }

    public boolean hasBackpack() {
        return false;
    }


    public int getBackpackLevel() {
        return 0;
    }

    public boolean hasSasimono() {
        return false;
    }

    public Entity getControllingPassenger() {
        return this.getPassengerList().isEmpty() ? null : this.getPassengerList().get(0);
    }
}
