package bilibili.ywsuoyi.client.resources.animation;

import bilibili.ywsuoyi.client.model.JsModelPart;
import bilibili.ywsuoyi.entity.item.*;
import bilibili.ywsuoyi.entity.maid.MaidEntity;
import com.github.tartaricacid.touhoulittlemaid.client.animation.script.GlStateManager;
import com.google.common.collect.Maps;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Date;
import java.util.HashMap;

public final class InnerAnimation {
    private static final HashMap<Identifier, AbstractAnimation<LivingEntity>> LIVING_ENTITY_INNER_ANIMATION = Maps.newHashMap();
    private static final HashMap<Identifier, AbstractAnimation<MaidEntity>> MAID_ENTITY_INNER_ANIMATION = Maps.newHashMap();
    private static final HashMap<Identifier, AbstractAnimation<ChairEntity>> CHAIR_ENTITY_INNER_ANIMATION = Maps.newHashMap();

    public static AbstractAnimation<MaidEntity> getHeadDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart head = modelMap.get("head");
                if (head != null) {
                    head.setRotateAngleX(headPitch * 0.017453292f);
                    head.setRotateAngleY(netHeadYaw * 0.017453292f);
                    if (maid.isSleep()) {
                        head.setRotateAngleX(15 * 0.017453292f);
                    }
                }

                JsModelPart hat = modelMap.get("hat");
                if (hat != null) {
                    hat.setHidden(maid.isSleep());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHeadBlink() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart blink = modelMap.get("blink");

                if (blink != null) {
                    if (maid.isSleep()) {
                        blink.setHidden(false);
                        return;
                    }
                    float remainder = ageInTicks % 60;
                    blink.setHidden(!(55 < remainder && remainder < 60));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHeadBeg() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart head = modelMap.get("head");
                JsModelPart ahoge = modelMap.get("ahoge");

                if (maid.isBegging()) {
                    if (head != null) {
                        head.setRotateAngleZ(0.139f);
                    }
                    if (ahoge != null) {
                        ahoge.setRotateAngleX((float) (Math.cos(ageInTicks * 1.0) * 0.05));
                        ahoge.setRotateAngleZ((float) (Math.sin(ageInTicks * 1.0) * 0.05));
                    }
                } else {
                    if (head != null) {
                        head.setRotateAngleZ(0);
                    }
                    if (ahoge != null) {
                        ahoge.setRotateAngleZ(0);
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHeadExtra() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart headExtraA = modelMap.get("headExtraA");
                if (headExtraA != null) {
                    headExtraA.setRotateAngleX(headPitch * 0.017453292f);
                    headExtraA.setRotateAngleY(netHeadYaw * 0.017453292f);
                }

                JsModelPart headExtraB = modelMap.get("headExtraB");
                if (headExtraB != null) {
                    headExtraB.setRotateAngleX(headPitch * 0.017453292f);
                    headExtraB.setRotateAngleY(netHeadYaw * 0.017453292f);
                }

                JsModelPart headExtraC = modelMap.get("headExtraC");
                if (headExtraC != null) {
                    headExtraC.setRotateAngleX(headPitch * 0.017453292f);
                    headExtraC.setRotateAngleY(netHeadYaw * 0.017453292f);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHeadHurt() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart hurtBlink = modelMap.get("hurtBlink");

                if (hurtBlink != null) {
                    hurtBlink.setHidden(!maid.onHurt());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHeadReverseBlink() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart reverseBlink = modelMap.get("_bink");
                if (reverseBlink != null) {
                    float remainder = ageInTicks % 60;
                    reverseBlink.setHidden(55 < remainder && remainder < 60);
                }
            }
        };
    }


    public static AbstractAnimation<MaidEntity> getHeadMusicShake() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart head = modelMap.get("head");

                if (head != null) {
                    if (isPortableAudioPlay(maid)) {
                        head.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.4) * 0.06));
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getLegDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart legLeft = modelMap.get("legLeft");
                JsModelPart legRight = modelMap.get("legRight");

                boolean isFarm = "farm".equals(maid.getTask().getUid().getPath()) && maid.swingProgress > 0.0;

                if (isFarm) {
                    GlStateManager.translate(0, 0.0713625, -0.35876875);
                    GlStateManager.rotate(22.5f, 1, 0, 0);
                }

                if (legLeft != null) {
                    double leftRad = Math.cos(limbSwing * 0.67) * 0.3 * limbSwingAmount;
                    if (isFarm) {
                        leftRad -= 0.3927;
                    }
                    legLeft.setRotateAngleX((float) leftRad);
                    legLeft.setRotateAngleY(0);
                    legLeft.setRotateAngleZ(0);
                }
                if (legRight != null) {
                    double rightRad = -Math.cos(limbSwing * 0.67) * 0.3 * limbSwingAmount;
                    if (isFarm) {
                        rightRad -= 0.3927;
                    }
                    legRight.setRotateAngleX((float) rightRad);
                    legRight.setRotateAngleY(0);
                    legRight.setRotateAngleZ(0);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getLegExtra() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart legLeftExtraA = modelMap.get("legLeftExtraA");
                JsModelPart legRightExtraA = modelMap.get("legRightExtraA");

                if (legLeftExtraA != null) {
                    legLeftExtraA.setRotateAngleX((float) (Math.cos(limbSwing * 0.67) * 0.3 * limbSwingAmount));
                    legLeftExtraA.setRotateAngleY(0);
                    legLeftExtraA.setRotateAngleZ(0);
                }
                if (legRightExtraA != null) {
                    legRightExtraA.setRotateAngleX((float) (-Math.cos(limbSwing * 0.67) * 0.3 * limbSwingAmount));
                    legRightExtraA.setRotateAngleY(0);
                    legRightExtraA.setRotateAngleZ(0);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getLegVertical() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart legLeftVertical = modelMap.get("legLeftVertical");
                JsModelPart legLeft = modelMap.get("legLeft");
                if (legLeftVertical != null) {
                    if (legLeft != null) {
                        legLeftVertical.setRotateAngleX(-legLeft.getRotateAngleX());
                        legLeftVertical.setRotateAngleZ(-legLeft.getRotateAngleZ());
                    }
                }

                JsModelPart legRightVertical = modelMap.get("legRightVertical");
                JsModelPart legRight = modelMap.get("legRight");
                if (legRightVertical != null) {
                    if (legRight != null) {
                        legRightVertical.setRotateAngleX(-legRight.getRotateAngleX());
                        legRightVertical.setRotateAngleZ(-legRight.getRotateAngleZ());
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                double f1 = 1.0 - Math.pow(1.0 - maid.swingProgress, 4);
                double f2 = Math.sin(f1 * Math.PI);
                double f3 = Math.sin(maid.swingProgress * Math.PI) * -0.7 * 0.75;

                float[] rotation;
                if (armLeft != null) {
                    if (maid.isSitInJoyBlock()) {
                        armLeft.setRotateAngleX(-1.3f);
                    } else if (isHoldTrolley(maid)) {
                        armLeft.setRotateAngleX(0.5f);
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ(-0.395f);
                    } else if (isHoldVehicle(maid)) {
                        rotation = getLeftHandRotation(maid);
                        armLeft.setRotateAngleX(rotation[0]);
                        armLeft.setRotateAngleY(rotation[1]);
                        armLeft.setRotateAngleZ(rotation[2]);
                    } else {
                        armLeft.setRotateAngleX((float) (-Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.05) * 0.05 - 0.4));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && isSwingLeftHand(maid)) {
                            armLeft.setRotateAngleX((float) (armLeft.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armLeft.setRotateAngleZ((float) (armLeft.getRotateAngleZ() + Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }

                if (armRight != null) {
                    if (maid.isSitInJoyBlock()) {
                        armRight.setRotateAngleX(-1.3f);
                    } else if (isHoldVehicle(maid)) {
                        rotation = getRightHandRotation(maid);
                        armRight.setRotateAngleX(rotation[0]);
                        armRight.setRotateAngleY(rotation[1]);
                        armRight.setRotateAngleZ(rotation[2]);
                    } else {
                        armRight.setRotateAngleX((float) (Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armRight.setRotateAngleY(0);
                        armRight.setRotateAngleZ((float) (-Math.cos(ageInTicks * 0.05) * 0.05 + 0.4));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && !isSwingLeftHand(maid)) {
                            armRight.setRotateAngleX((float) (armRight.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armRight.setRotateAngleZ((float) (armRight.getRotateAngleZ() + Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmExtra() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeft = modelMap.get("armLeftExtraA");
                JsModelPart armRight = modelMap.get("armRightExtraA");

                double f1 = 1.0 - Math.pow(1.0 - maid.swingProgress, 4);
                double f2 = Math.sin(f1 * Math.PI);
                double f3 = Math.sin(maid.swingProgress * Math.PI) * -0.7 * 0.75;

                float[] rotation;
                if (armLeft != null) {
                    if (isHoldTrolley(maid)) {
                        armLeft.setRotateAngleX(0.5f);
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ(-0.395f);
                    } else if (isHoldVehicle(maid)) {
                        rotation = getLeftHandRotation(maid);
                        armLeft.setRotateAngleX(rotation[0]);
                        armLeft.setRotateAngleY(rotation[1]);
                        armLeft.setRotateAngleZ(rotation[2]);
                    } else {
                        armLeft.setRotateAngleX((float) (-Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.05) * 0.05 - 0.4));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && isSwingLeftHand(maid)) {
                            armLeft.setRotateAngleX((float) (armLeft.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armLeft.setRotateAngleZ((float) (armLeft.getRotateAngleZ() + Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }

                if (armRight != null) {
                    if (isHoldVehicle(maid)) {
                        rotation = getRightHandRotation(maid);
                        armRight.setRotateAngleX(rotation[0]);
                        armRight.setRotateAngleY(rotation[1]);
                        armRight.setRotateAngleZ(rotation[2]);
                    } else {
                        armRight.setRotateAngleX((float) (Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armRight.setRotateAngleY(0);
                        armRight.setRotateAngleZ((float) (-Math.cos(ageInTicks * 0.05) * 0.05 + 0.4));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && !isSwingLeftHand(maid)) {
                            armRight.setRotateAngleX((float) (armRight.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armRight.setRotateAngleZ((float) (armRight.getRotateAngleZ() + Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmSwing() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                if (maid.isSwingingArms()) {
                    if (armLeft != null) {
                        armLeft.setRotateAngleX(-1.396f);
                        armLeft.setRotateAngleY(0.785f);
                    }
                    if (armRight != null) {
                        armRight.setRotateAngleX(-1.396f);
                        armRight.setRotateAngleY(-0.174f);
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmVertical() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeftVertical = modelMap.get("armLeftVertical");
                JsModelPart armLeft = modelMap.get("armLeft");
                if (armLeftVertical != null) {
                    if (armLeft != null) {
                        armLeftVertical.setRotateAngleX(-armLeft.getRotateAngleX());
                        armLeftVertical.setRotateAngleZ(-armLeft.getRotateAngleZ());
                    }
                }

                JsModelPart armRightVertical = modelMap.get("armRightVertical");
                JsModelPart armRight = modelMap.get("armRight");
                if (armRightVertical != null) {
                    if (armRight != null) {
                        armRightVertical.setRotateAngleX(-armRight.getRotateAngleX());
                        armRightVertical.setRotateAngleZ(-armRight.getRotateAngleZ());
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSitDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart head = modelMap.get("head");
                JsModelPart legLeft = modelMap.get("legLeft");
                JsModelPart legRight = modelMap.get("legRight");
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                // 头部复位
                if (head != null) {
                    head.setOffsetY(0);
                }

                if (isRidingMarisaBroom(maid)) {
                    // 坐在扫帚上时，应用待命的动作
                    ridingBroomPosture(head, armLeft, armRight, legLeft, legRight);
                } else if (maid.isRiding()) {
                    ridingPosture(legLeft, legRight);
                } else if (maid.isSitting()) {
                    sittingPosture(armLeft, armRight, legLeft, legRight);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSitSkirtHidden() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sittingHiddenSkirt = modelMap.get("sittingHiddenSkirt");
                if (sittingHiddenSkirt != null) {
                    sittingHiddenSkirt.setHidden(isRidingMarisaBroom(maid) || maid.isRiding() || maid.isSitting());
                }

                JsModelPart reverseSittingHiddenSkirt = modelMap.get("_sittingHiddenSkirt");
                if (reverseSittingHiddenSkirt != null) {
                    reverseSittingHiddenSkirt.setHidden(!isRidingMarisaBroom(maid) && !maid.isRiding() && !maid.isSitting());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSitSkirtRotation() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sittingRotationSkirt = modelMap.get("sittingRotationSkirt");
                if (sittingRotationSkirt != null) {
                    if (isRidingMarisaBroom(maid) || maid.isRiding() || maid.isSitting()) {
                        sittingRotationSkirt.setRotateAngleX(-0.567f);
                    } else {
                        sittingRotationSkirt.setRotateAngleX(0);
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmet = modelMap.get("helmet");
                JsModelPart chestPlate = modelMap.get("chestPlate");
                JsModelPart chestPlateLeft = modelMap.get("chestPlateLeft");
                JsModelPart chestPlateMiddle = modelMap.get("chestPlateMiddle");
                JsModelPart chestPlateRight = modelMap.get("chestPlateRight");
                JsModelPart leggings = modelMap.get("leggings");
                JsModelPart leggingsLeft = modelMap.get("leggingsLeft");
                JsModelPart leggingsMiddle = modelMap.get("leggingsMiddle");
                JsModelPart leggingsRight = modelMap.get("leggingsRight");
                JsModelPart bootsLeft = modelMap.get("bootsLeft");
                JsModelPart bootsRight = modelMap.get("bootsRight");

                if (helmet != null) {
                    helmet.setHidden(!maid.hasHelmet());
                }
                if (chestPlate != null) {
                    chestPlate.setHidden(!maid.hasChestPlate());
                }
                if (chestPlateLeft != null) {
                    chestPlateLeft.setHidden(!maid.hasChestPlate());
                }
                if (chestPlateMiddle != null) {
                    chestPlateMiddle.setHidden(!maid.hasChestPlate());
                }
                if (chestPlateRight != null) {
                    chestPlateRight.setHidden(!maid.hasChestPlate());
                }
                if (leggings != null) {
                    leggings.setHidden(!maid.hasLeggings());
                }
                if (leggingsLeft != null) {
                    leggingsLeft.setHidden(!maid.hasLeggings());
                }
                if (leggingsMiddle != null) {
                    leggingsMiddle.setHidden(!maid.hasLeggings());
                }
                if (leggingsRight != null) {
                    leggingsRight.setHidden(!maid.hasLeggings());
                }
                if (bootsLeft != null) {
                    bootsLeft.setHidden(!maid.hasBoots());
                }
                if (bootsRight != null) {
                    bootsRight.setHidden(!maid.hasBoots());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorReverse() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart reverseHelmet = modelMap.get("_helmet");
                JsModelPart reverseChestPlate = modelMap.get("_chestPlate");
                JsModelPart reverseChestPlateLeft = modelMap.get("_chestPlateLeft");
                JsModelPart reverseChestPlateMiddle = modelMap.get("_chestPlateMiddle");
                JsModelPart reverseChestPlateRight = modelMap.get("_chestPlateRight");
                JsModelPart reverseLeggings = modelMap.get("_leggings");
                JsModelPart reverseLeggingsLeft = modelMap.get("_leggingsLeft");
                JsModelPart reverseLeggingsMiddle = modelMap.get("_leggingsMiddle");
                JsModelPart reverseLeggingsRight = modelMap.get("_leggingsRight");
                JsModelPart reverseBootsLeft = modelMap.get("_bootsLeft");
                JsModelPart reverseBootsRight = modelMap.get("_bootsRight");

                if (reverseHelmet != null) {
                    reverseHelmet.setHidden(maid.hasHelmet());
                }
                if (reverseChestPlate != null) {
                    reverseChestPlate.setHidden(maid.hasChestPlate());
                }
                if (reverseChestPlateLeft != null) {
                    reverseChestPlateLeft.setHidden(maid.hasChestPlate());
                }
                if (reverseChestPlateMiddle != null) {
                    reverseChestPlateMiddle.setHidden(maid.hasChestPlate());
                }
                if (reverseChestPlateRight != null) {
                    reverseChestPlateRight.setHidden(maid.hasChestPlate());
                }
                if (reverseLeggings != null) {
                    reverseLeggings.setHidden(maid.hasLeggings());
                }
                if (reverseLeggingsLeft != null) {
                    reverseLeggingsLeft.setHidden(maid.hasLeggings());
                }
                if (reverseLeggingsMiddle != null) {
                    reverseLeggingsMiddle.setHidden(maid.hasLeggings());
                }
                if (reverseLeggingsRight != null) {
                    reverseLeggingsRight.setHidden(maid.hasLeggings());
                }
                if (reverseBootsLeft != null) {
                    reverseBootsLeft.setHidden(maid.hasBoots());
                }
                if (reverseBootsRight != null) {
                    reverseBootsRight.setHidden(maid.hasBoots());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorTempCold() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetTempCold = modelMap.get("helmetTempCold");
                JsModelPart chestPlateTempCold = modelMap.get("chestPlateTempCold");
                JsModelPart chestPlateLeftTempCold = modelMap.get("chestPlateLeftTempCold");
                JsModelPart chestPlateMiddleTempCold = modelMap.get("chestPlateMiddleTempCold");
                JsModelPart chestPlateRightTempCold = modelMap.get("chestPlateRightTempCold");
                JsModelPart leggingsTempCold = modelMap.get("leggingsTempCold");
                JsModelPart leggingsLeftTempCold = modelMap.get("leggingsLeftTempCold");
                JsModelPart leggingsMiddleTempCold = modelMap.get("leggingsMiddleTempCold");
                JsModelPart leggingsRightTempCold = modelMap.get("leggingsRightTempCold");
                JsModelPart bootsLeftTempCold = modelMap.get("bootsLeftTempCold");
                JsModelPart bootsRightTempCold = modelMap.get("bootsRightTempCold");

                boolean tempIsCold = "COLD".equals(maid.getAtBiomeTemp());

                if (helmetTempCold != null) {
                    helmetTempCold.setHidden(!tempIsCold);
                }
                if (chestPlateTempCold != null) {
                    chestPlateTempCold.setHidden(!tempIsCold);
                }
                if (chestPlateLeftTempCold != null) {
                    chestPlateLeftTempCold.setHidden(!tempIsCold);
                }
                if (chestPlateMiddleTempCold != null) {
                    chestPlateMiddleTempCold.setHidden(!tempIsCold);
                }
                if (chestPlateRightTempCold != null) {
                    chestPlateRightTempCold.setHidden(!tempIsCold);
                }
                if (leggingsTempCold != null) {
                    leggingsTempCold.setHidden(!tempIsCold);
                }
                if (leggingsLeftTempCold != null) {
                    leggingsLeftTempCold.setHidden(!tempIsCold);
                }
                if (leggingsMiddleTempCold != null) {
                    leggingsMiddleTempCold.setHidden(!tempIsCold);
                }
                if (leggingsRightTempCold != null) {
                    leggingsRightTempCold.setHidden(!tempIsCold);
                }
                if (bootsLeftTempCold != null) {
                    bootsLeftTempCold.setHidden(!tempIsCold);
                }
                if (bootsRightTempCold != null) {
                    bootsRightTempCold.setHidden(!tempIsCold);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorTempMedium() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetTempMedium = modelMap.get("helmetTempMedium");
                JsModelPart chestPlateTempMedium = modelMap.get("chestPlateTempMedium");
                JsModelPart chestPlateLeftTempMedium = modelMap.get("chestPlateLeftTempMedium");
                JsModelPart chestPlateMiddleTempMedium = modelMap.get("chestPlateMiddleTempMedium");
                JsModelPart chestPlateRightTempMedium = modelMap.get("chestPlateRightTempMedium");
                JsModelPart leggingsTempMedium = modelMap.get("leggingsTempMedium");
                JsModelPart leggingsLeftTempMedium = modelMap.get("leggingsLeftTempMedium");
                JsModelPart leggingsMiddleTempMedium = modelMap.get("leggingsMiddleTempMedium");
                JsModelPart leggingsRightTempMedium = modelMap.get("leggingsRightTempMedium");
                JsModelPart bootsLeftTempMedium = modelMap.get("bootsLeftTempMedium");
                JsModelPart bootsRightTempMedium = modelMap.get("bootsRightTempMedium");

                boolean tempIsMedium = "MEDIUM".equals(maid.getAtBiomeTemp());

                if (helmetTempMedium != null) {
                    helmetTempMedium.setHidden(!tempIsMedium);
                }
                if (chestPlateTempMedium != null) {
                    chestPlateTempMedium.setHidden(!tempIsMedium);
                }
                if (chestPlateLeftTempMedium != null) {
                    chestPlateLeftTempMedium.setHidden(!tempIsMedium);
                }
                if (chestPlateMiddleTempMedium != null) {
                    chestPlateMiddleTempMedium.setHidden(!tempIsMedium);
                }
                if (chestPlateRightTempMedium != null) {
                    chestPlateRightTempMedium.setHidden(!tempIsMedium);
                }
                if (leggingsTempMedium != null) {
                    leggingsTempMedium.setHidden(!tempIsMedium);
                }
                if (leggingsLeftTempMedium != null) {
                    leggingsLeftTempMedium.setHidden(!tempIsMedium);
                }
                if (leggingsMiddleTempMedium != null) {
                    leggingsMiddleTempMedium.setHidden(!tempIsMedium);
                }
                if (leggingsRightTempMedium != null) {
                    leggingsRightTempMedium.setHidden(!tempIsMedium);
                }
                if (bootsLeftTempMedium != null) {
                    bootsLeftTempMedium.setHidden(!tempIsMedium);
                }
                if (bootsRightTempMedium != null) {
                    bootsRightTempMedium.setHidden(!tempIsMedium);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorTempOcean() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetTempOcean = modelMap.get("helmetTempOcean");
                JsModelPart chestPlateTempOcean = modelMap.get("chestPlateTempOcean");
                JsModelPart chestPlateLeftTempOcean = modelMap.get("chestPlateLeftTempOcean");
                JsModelPart chestPlateMiddleTempOcean = modelMap.get("chestPlateMiddleTempOcean");
                JsModelPart chestPlateRightTempOcean = modelMap.get("chestPlateRightTempOcean");
                JsModelPart leggingsTempOcean = modelMap.get("leggingsTempOcean");
                JsModelPart leggingsLeftTempOcean = modelMap.get("leggingsLeftTempOcean");
                JsModelPart leggingsMiddleTempOcean = modelMap.get("leggingsMiddleTempOcean");
                JsModelPart leggingsRightTempOcean = modelMap.get("leggingsRightTempOcean");
                JsModelPart bootsLeftTempOcean = modelMap.get("bootsLeftTempOcean");
                JsModelPart bootsRightTempOcean = modelMap.get("bootsRightTempOcean");

                boolean tempIsOcean = "OCEAN".equals(maid.getAtBiomeTemp());

                if (helmetTempOcean != null) {
                    helmetTempOcean.setHidden(!tempIsOcean);
                }
                if (chestPlateTempOcean != null) {
                    chestPlateTempOcean.setHidden(!tempIsOcean);
                }
                if (chestPlateLeftTempOcean != null) {
                    chestPlateLeftTempOcean.setHidden(!tempIsOcean);
                }
                if (chestPlateMiddleTempOcean != null) {
                    chestPlateMiddleTempOcean.setHidden(!tempIsOcean);
                }
                if (chestPlateRightTempOcean != null) {
                    chestPlateRightTempOcean.setHidden(!tempIsOcean);
                }
                if (leggingsTempOcean != null) {
                    leggingsTempOcean.setHidden(!tempIsOcean);
                }
                if (leggingsLeftTempOcean != null) {
                    leggingsLeftTempOcean.setHidden(!tempIsOcean);
                }
                if (leggingsMiddleTempOcean != null) {
                    leggingsMiddleTempOcean.setHidden(!tempIsOcean);
                }
                if (leggingsRightTempOcean != null) {
                    leggingsRightTempOcean.setHidden(!tempIsOcean);
                }
                if (bootsLeftTempOcean != null) {
                    bootsLeftTempOcean.setHidden(!tempIsOcean);
                }
                if (bootsRightTempOcean != null) {
                    bootsRightTempOcean.setHidden(!tempIsOcean);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorTempWarm() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetTempWarm = modelMap.get("helmetTempWarm");
                JsModelPart chestPlateTempWarm = modelMap.get("chestPlateTempWarm");
                JsModelPart chestPlateLeftTempWarm = modelMap.get("chestPlateLeftTempWarm");
                JsModelPart chestPlateMiddleTempWarm = modelMap.get("chestPlateMiddleTempWarm");
                JsModelPart chestPlateRightTempWarm = modelMap.get("chestPlateRightTempWarm");
                JsModelPart leggingsTempWarm = modelMap.get("leggingsTempWarm");
                JsModelPart leggingsLeftTempWarm = modelMap.get("leggingsLeftTempWarm");
                JsModelPart leggingsMiddleTempWarm = modelMap.get("leggingsMiddleTempWarm");
                JsModelPart leggingsRightTempWarm = modelMap.get("leggingsRightTempWarm");
                JsModelPart bootsLeftTempWarm = modelMap.get("bootsLeftTempWarm");
                JsModelPart bootsRightTempWarm = modelMap.get("bootsRightTempWarm");

                boolean tempIsWarm = "WARM".equals(maid.getAtBiomeTemp());

                if (helmetTempWarm != null) {
                    helmetTempWarm.setHidden(!tempIsWarm);
                }
                if (chestPlateTempWarm != null) {
                    chestPlateTempWarm.setHidden(!tempIsWarm);
                }
                if (chestPlateLeftTempWarm != null) {
                    chestPlateLeftTempWarm.setHidden(!tempIsWarm);
                }
                if (chestPlateMiddleTempWarm != null) {
                    chestPlateMiddleTempWarm.setHidden(!tempIsWarm);
                }
                if (chestPlateRightTempWarm != null) {
                    chestPlateRightTempWarm.setHidden(!tempIsWarm);
                }
                if (leggingsTempWarm != null) {
                    leggingsTempWarm.setHidden(!tempIsWarm);
                }
                if (leggingsLeftTempWarm != null) {
                    leggingsLeftTempWarm.setHidden(!tempIsWarm);
                }
                if (leggingsMiddleTempWarm != null) {
                    leggingsMiddleTempWarm.setHidden(!tempIsWarm);
                }
                if (leggingsRightTempWarm != null) {
                    leggingsRightTempWarm.setHidden(!tempIsWarm);
                }
                if (bootsLeftTempWarm != null) {
                    bootsLeftTempWarm.setHidden(!tempIsWarm);
                }
                if (bootsRightTempWarm != null) {
                    bootsRightTempWarm.setHidden(!tempIsWarm);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorValueFull() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetValueFull = modelMap.get("helmetValueFull");
                JsModelPart chestPlateValueFull = modelMap.get("chestPlateValueFull");
                JsModelPart chestPlateLeftValueFull = modelMap.get("chestPlateLeftValueFull");
                JsModelPart chestPlateMiddleValueFull = modelMap.get("chestPlateMiddleValueFull");
                JsModelPart chestPlateRightValueFull = modelMap.get("chestPlateRightValueFull");
                JsModelPart leggingsValueFull = modelMap.get("leggingsValueFull");
                JsModelPart leggingsLeftValueFull = modelMap.get("leggingsLeftValueFull");
                JsModelPart leggingsMiddleValueFull = modelMap.get("leggingsMiddleValueFull");
                JsModelPart leggingsRightValueFull = modelMap.get("leggingsRightValueFull");
                JsModelPart bootsLeftValueFull = modelMap.get("bootsLeftValueFull");
                JsModelPart bootsRightValueFull = modelMap.get("bootsRightValueFull");

                boolean valueIsFull = 15 < maid.getArmorValue();

                if (helmetValueFull != null) {
                    helmetValueFull.setHidden(!valueIsFull);
                }
                if (chestPlateValueFull != null) {
                    chestPlateValueFull.setHidden(!valueIsFull);
                }
                if (chestPlateLeftValueFull != null) {
                    chestPlateLeftValueFull.setHidden(!valueIsFull);
                }
                if (chestPlateMiddleValueFull != null) {
                    chestPlateMiddleValueFull.setHidden(!valueIsFull);
                }
                if (chestPlateRightValueFull != null) {
                    chestPlateRightValueFull.setHidden(!valueIsFull);
                }
                if (leggingsValueFull != null) {
                    leggingsValueFull.setHidden(!valueIsFull);
                }
                if (leggingsLeftValueFull != null) {
                    leggingsLeftValueFull.setHidden(!valueIsFull);
                }
                if (leggingsMiddleValueFull != null) {
                    leggingsMiddleValueFull.setHidden(!valueIsFull);
                }
                if (leggingsRightValueFull != null) {
                    leggingsRightValueFull.setHidden(!valueIsFull);
                }
                if (bootsLeftValueFull != null) {
                    bootsLeftValueFull.setHidden(!valueIsFull);
                }
                if (bootsRightValueFull != null) {
                    bootsRightValueFull.setHidden(!valueIsFull);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorValueHigh() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetValueHigh = modelMap.get("helmetValueHigh");
                JsModelPart chestPlateValueHigh = modelMap.get("chestPlateValueHigh");
                JsModelPart chestPlateLeftValueHigh = modelMap.get("chestPlateLeftValueHigh");
                JsModelPart chestPlateMiddleValueHigh = modelMap.get("chestPlateMiddleValueHigh");
                JsModelPart chestPlateRightValueHigh = modelMap.get("chestPlateRightValueHigh");
                JsModelPart leggingsValueHigh = modelMap.get("leggingsValueHigh");
                JsModelPart leggingsLeftValueHigh = modelMap.get("leggingsLeftValueHigh");
                JsModelPart leggingsMiddleValueHigh = modelMap.get("leggingsMiddleValueHigh");
                JsModelPart leggingsRightValueHigh = modelMap.get("leggingsRightValueHigh");
                JsModelPart bootsLeftValueHigh = modelMap.get("bootsLeftValueHigh");
                JsModelPart bootsRightValueHigh = modelMap.get("bootsRightValueHigh");

                boolean valueIsHigh = (10 < maid.getArmorValue()) && (maid.getArmorValue() <= 15);

                if (helmetValueHigh != null) {
                    helmetValueHigh.setHidden(!valueIsHigh);
                }
                if (chestPlateValueHigh != null) {
                    chestPlateValueHigh.setHidden(!valueIsHigh);
                }
                if (chestPlateLeftValueHigh != null) {
                    chestPlateLeftValueHigh.setHidden(!valueIsHigh);
                }
                if (chestPlateMiddleValueHigh != null) {
                    chestPlateMiddleValueHigh.setHidden(!valueIsHigh);
                }
                if (chestPlateRightValueHigh != null) {
                    chestPlateRightValueHigh.setHidden(!valueIsHigh);
                }
                if (leggingsValueHigh != null) {
                    leggingsValueHigh.setHidden(!valueIsHigh);
                }
                if (leggingsLeftValueHigh != null) {
                    leggingsLeftValueHigh.setHidden(!valueIsHigh);
                }
                if (leggingsMiddleValueHigh != null) {
                    leggingsMiddleValueHigh.setHidden(!valueIsHigh);
                }
                if (leggingsRightValueHigh != null) {
                    leggingsRightValueHigh.setHidden(!valueIsHigh);
                }
                if (bootsLeftValueHigh != null) {
                    bootsLeftValueHigh.setHidden(!valueIsHigh);
                }
                if (bootsRightValueHigh != null) {
                    bootsRightValueHigh.setHidden(!valueIsHigh);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorValueLow() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetValueLow = modelMap.get("helmetValueLow");
                JsModelPart chestPlateValueLow = modelMap.get("chestPlateValueLow");
                JsModelPart chestPlateLeftValueLow = modelMap.get("chestPlateLeftValueLow");
                JsModelPart chestPlateMiddleValueLow = modelMap.get("chestPlateMiddleValueLow");
                JsModelPart chestPlateRightValueLow = modelMap.get("chestPlateRightValueLow");
                JsModelPart leggingsValueLow = modelMap.get("leggingsValueLow");
                JsModelPart leggingsLeftValueLow = modelMap.get("leggingsLeftValueLow");
                JsModelPart leggingsMiddleValueLow = modelMap.get("leggingsMiddleValueLow");
                JsModelPart leggingsRightValueLow = modelMap.get("leggingsRightValueLow");
                JsModelPart bootsLeftValueLow = modelMap.get("bootsLeftValueLow");
                JsModelPart bootsRightValueLow = modelMap.get("bootsRightValueLow");

                boolean valueIsLow = (0 < maid.getArmorValue()) && (maid.getArmorValue() <= 5);

                if (helmetValueLow != null) {
                    helmetValueLow.setHidden(!valueIsLow);
                }
                if (chestPlateValueLow != null) {
                    chestPlateValueLow.setHidden(!valueIsLow);
                }
                if (chestPlateLeftValueLow != null) {
                    chestPlateLeftValueLow.setHidden(!valueIsLow);
                }
                if (chestPlateMiddleValueLow != null) {
                    chestPlateMiddleValueLow.setHidden(!valueIsLow);
                }
                if (chestPlateRightValueLow != null) {
                    chestPlateRightValueLow.setHidden(!valueIsLow);
                }
                if (leggingsValueLow != null) {
                    leggingsValueLow.setHidden(!valueIsLow);
                }
                if (leggingsLeftValueLow != null) {
                    leggingsLeftValueLow.setHidden(!valueIsLow);
                }
                if (leggingsMiddleValueLow != null) {
                    leggingsMiddleValueLow.setHidden(!valueIsLow);
                }
                if (leggingsRightValueLow != null) {
                    leggingsRightValueLow.setHidden(!valueIsLow);
                }
                if (bootsLeftValueLow != null) {
                    bootsLeftValueLow.setHidden(!valueIsLow);
                }
                if (bootsRightValueLow != null) {
                    bootsRightValueLow.setHidden(!valueIsLow);
                }
            }
        };
    }


    public static AbstractAnimation<MaidEntity> getArmorValueNormal() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetValueNormal = modelMap.get("helmetValueNormal");
                JsModelPart chestPlateValueNormal = modelMap.get("chestPlateValueNormal");
                JsModelPart chestPlateLeftValueNormal = modelMap.get("chestPlateLeftValueNormal");
                JsModelPart chestPlateMiddleValueNormal = modelMap.get("chestPlateMiddleValueNormal");
                JsModelPart chestPlateRightValueNormal = modelMap.get("chestPlateRightValueNormal");
                JsModelPart leggingsValueNormal = modelMap.get("leggingsValueNormal");
                JsModelPart leggingsLeftValueNormal = modelMap.get("leggingsLeftValueNormal");
                JsModelPart leggingsMiddleValueNormal = modelMap.get("leggingsMiddleValueNormal");
                JsModelPart leggingsRightValueNormal = modelMap.get("leggingsRightValueNormal");
                JsModelPart bootsLeftValueNormal = modelMap.get("bootsLeftValueNormal");
                JsModelPart bootsRightValueNormal = modelMap.get("bootsRightValueNormal");

                boolean valueIsNormal = (0 < maid.getArmorValue()) && (maid.getArmorValue() <= 5);

                if (helmetValueNormal != null) {
                    helmetValueNormal.setHidden(!valueIsNormal);
                }
                if (chestPlateValueNormal != null) {
                    chestPlateValueNormal.setHidden(!valueIsNormal);
                }
                if (chestPlateLeftValueNormal != null) {
                    chestPlateLeftValueNormal.setHidden(!valueIsNormal);
                }
                if (chestPlateMiddleValueNormal != null) {
                    chestPlateMiddleValueNormal.setHidden(!valueIsNormal);
                }
                if (chestPlateRightValueNormal != null) {
                    chestPlateRightValueNormal.setHidden(!valueIsNormal);
                }
                if (leggingsValueNormal != null) {
                    leggingsValueNormal.setHidden(!valueIsNormal);
                }
                if (leggingsLeftValueNormal != null) {
                    leggingsLeftValueNormal.setHidden(!valueIsNormal);
                }
                if (leggingsMiddleValueNormal != null) {
                    leggingsMiddleValueNormal.setHidden(!valueIsNormal);
                }
                if (leggingsRightValueNormal != null) {
                    leggingsRightValueNormal.setHidden(!valueIsNormal);
                }
                if (bootsLeftValueNormal != null) {
                    bootsLeftValueNormal.setHidden(!valueIsNormal);
                }
                if (bootsRightValueNormal != null) {
                    bootsRightValueNormal.setHidden(!valueIsNormal);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getArmorWeatherRainging() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetWeatherRainging = modelMap.get("helmetWeatherRainging");
                JsModelPart chestPlateWeatherRainging = modelMap.get("chestPlateWeatherRainging");
                JsModelPart chestPlateLeftWeatherRainging = modelMap.get("chestPlateLeftWeatherRainging");
                JsModelPart chestPlateMiddleWeatherRainging = modelMap.get("chestPlateMiddleWeatherRainging");
                JsModelPart chestPlateRightWeatherRainging = modelMap.get("chestPlateRightWeatherRainging");
                JsModelPart leggingsWeatherRainging = modelMap.get("leggingsWeatherRainging");
                JsModelPart leggingsLeftWeatherRainging = modelMap.get("leggingsLeftWeatherRainging");
                JsModelPart leggingsMiddleWeatherRainging = modelMap.get("leggingsMiddleWeatherRainging");
                JsModelPart leggingsRightWeatherRainging = modelMap.get("leggingsRightWeatherRainging");
                JsModelPart bootsLeftWeatherRainging = modelMap.get("bootsLeftWeatherRainging");
                JsModelPart bootsRightWeatherRainging = modelMap.get("bootsRightWeatherRainging");

                boolean weatherIsRainging = maid.world.isRaining();

                if (helmetWeatherRainging != null) {
                    helmetWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (chestPlateWeatherRainging != null) {
                    chestPlateWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (chestPlateLeftWeatherRainging != null) {
                    chestPlateLeftWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (chestPlateMiddleWeatherRainging != null) {
                    chestPlateMiddleWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (chestPlateRightWeatherRainging != null) {
                    chestPlateRightWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (leggingsWeatherRainging != null) {
                    leggingsWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (leggingsLeftWeatherRainging != null) {
                    leggingsLeftWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (leggingsMiddleWeatherRainging != null) {
                    leggingsMiddleWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (leggingsRightWeatherRainging != null) {
                    leggingsRightWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (bootsLeftWeatherRainging != null) {
                    bootsLeftWeatherRainging.setHidden(!weatherIsRainging);
                }
                if (bootsRightWeatherRainging != null) {
                    bootsRightWeatherRainging.setHidden(!weatherIsRainging);
                }
            }
        };
    }


    public static AbstractAnimation<MaidEntity> getArmorWeatherThundering() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart helmetWeatherThundering = modelMap.get("helmetWeatherThundering");
                JsModelPart chestPlateWeatherThundering = modelMap.get("chestPlateWeatherThundering");
                JsModelPart chestPlateLeftWeatherThundering = modelMap.get("chestPlateLeftWeatherThundering");
                JsModelPart chestPlateMiddleWeatherThundering = modelMap.get("chestPlateMiddleWeatherThundering");
                JsModelPart chestPlateRightWeatherThundering = modelMap.get("chestPlateRightWeatherThundering");
                JsModelPart leggingsWeatherThundering = modelMap.get("leggingsWeatherThundering");
                JsModelPart leggingsLeftWeatherThundering = modelMap.get("leggingsLeftWeatherThundering");
                JsModelPart leggingsMiddleWeatherThundering = modelMap.get("leggingsMiddleWeatherThundering");
                JsModelPart leggingsRightWeatherThundering = modelMap.get("leggingsRightWeatherThundering");
                JsModelPart bootsLeftWeatherThundering = modelMap.get("bootsLeftWeatherThundering");
                JsModelPart bootsRightWeatherThundering = modelMap.get("bootsRightWeatherThundering");

                boolean weatherIsThundering = maid.world.isThundering();

                if (helmetWeatherThundering != null) {
                    helmetWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (chestPlateWeatherThundering != null) {
                    chestPlateWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (chestPlateLeftWeatherThundering != null) {
                    chestPlateLeftWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (chestPlateMiddleWeatherThundering != null) {
                    chestPlateMiddleWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (chestPlateRightWeatherThundering != null) {
                    chestPlateRightWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (leggingsWeatherThundering != null) {
                    leggingsWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (leggingsLeftWeatherThundering != null) {
                    leggingsLeftWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (leggingsMiddleWeatherThundering != null) {
                    leggingsMiddleWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (leggingsRightWeatherThundering != null) {
                    leggingsRightWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (bootsLeftWeatherThundering != null) {
                    bootsLeftWeatherThundering.setHidden(!weatherIsThundering);
                }
                if (bootsRightWeatherThundering != null) {
                    bootsRightWeatherThundering.setHidden(!weatherIsThundering);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHealthLessShow() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart healthLessQuarterShow = modelMap.get("healthLessQuarterShow");
                JsModelPart healthLessHalfShow = modelMap.get("healthLessHalfShow");
                JsModelPart healthLessThreeQuartersShow = modelMap.get("healthLessThreeQuartersShow");

                double i = maid.getHealth() / maid.getMaxHealth();

                if (healthLessQuarterShow != null) {
                    healthLessQuarterShow.setHidden(i > 0.25);
                }

                if (healthLessHalfShow != null) {
                    healthLessHalfShow.setHidden(i > 0.5);
                }

                if (healthLessThreeQuartersShow != null) {
                    healthLessThreeQuartersShow.setHidden(i > 0.75);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHealthMoreShow() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart healthMoreQuarterShow = modelMap.get("healthMoreQuarterShow");
                JsModelPart healthMoreHalfShow = modelMap.get("healthMoreHalfShow");
                JsModelPart healthMoreThreeQuartersShow = modelMap.get("healthMoreThreeQuartersShow");

                double i = maid.getHealth() / maid.getMaxHealth();

                if (healthMoreQuarterShow != null) {
                    healthMoreQuarterShow.setHidden(i <= 0.25);
                }

                if (healthMoreHalfShow != null) {
                    healthMoreHalfShow.setHidden(i <= 0.5);
                }

                if (healthMoreThreeQuartersShow != null) {
                    healthMoreThreeQuartersShow.setHidden(i <= 0.75);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getHealthRotation() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart healthRotationX90 = modelMap.get("healthRotationX90");

                if (healthRotationX90 != null) {
                    double deg = (Math.PI / 4) - (Math.PI / 2) * (maid.getHealth() / maid.getMaxHealth());
                    healthRotationX90.setRotateAngleX((float) deg);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getStatusBackpack() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart backpackShow = modelMap.get("backpackShow");
                JsModelPart backpackHidden = modelMap.get("backpackHidden");

                if (backpackShow != null) {
                    backpackShow.setHidden(!maid.hasBackpack());
                }
                if (backpackHidden != null) {
                    backpackHidden.setHidden(maid.hasBackpack());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getStatusBackpackLevel() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart backpackLevelEmpty = modelMap.get("backpackLevelEmpty");
                JsModelPart backpackLevelSmall = modelMap.get("backpackLevelSmall");
                JsModelPart backpackLevelMiddle = modelMap.get("backpackLevelMiddle");
                JsModelPart backpackLevelBig = modelMap.get("backpackLevelBig");

                int level = maid.getBackpackLevel();

                if (backpackLevelEmpty != null) {
                    backpackLevelEmpty.setHidden(level != 0);
                }

                if (backpackLevelSmall != null) {
                    backpackLevelSmall.setHidden(level != 1);
                }

                if (backpackLevelMiddle != null) {
                    backpackLevelMiddle.setHidden(level != 2);
                }

                if (backpackLevelBig != null) {
                    backpackLevelBig.setHidden(level != 3);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getStatusMoving() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart movingShow = modelMap.get("movingShow");
                JsModelPart movingHidden = modelMap.get("movingHidden");

                if (movingShow != null) {
                    movingShow.setHidden(limbSwing <= 0);
                }
                if (movingHidden != null) {
                    movingHidden.setHidden(limbSwing > 0);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getStatusSasimono() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sasimonoShow = modelMap.get("sasimonoShow");
                JsModelPart sasimonoHidden = modelMap.get("sasimonoHidden");

                if (sasimonoShow != null) {
                    sasimonoShow.setHidden(!maid.hasSasimono());
                }
                if (sasimonoHidden != null) {
                    sasimonoHidden.setHidden(maid.hasSasimono());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTailDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart tail = modelMap.get("tail");

                if (tail != null) {
                    tail.setRotateAngleX((float) (Math.sin(ageInTicks * 0.2) * 0.05));
                    tail.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.2) * 0.1));

                    tail.setHidden(maid.isSleep());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskAttack() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart attackHidden = modelMap.get("attackHidden");

                if (attackHidden != null) {
                    attackHidden.setHidden("attack".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart attackShow = modelMap.get("attackShow");
                if (attackShow != null) {
                    attackShow.setHidden(!"attack".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskDanmakuAttack() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart danmakuAttackHidden = modelMap.get("danmakuAttackHidden");

                if (danmakuAttackHidden != null) {
                    danmakuAttackHidden.setHidden("danmaku_attack".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart danmakuAttackShow = modelMap.get("danmakuAttackShow");
                if (danmakuAttackShow != null) {
                    danmakuAttackShow.setHidden(!"danmaku_attack".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskFarm() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart farmHidden = modelMap.get("farmHidden");

                if (farmHidden != null) {
                    farmHidden.setHidden("farm".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart farmShow = modelMap.get("farmShow");
                if (farmShow != null) {
                    farmShow.setHidden(!"farm".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskFeedAnimal() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart feed_animalHidden = modelMap.get("feedAnimalHidden");

                if (feed_animalHidden != null) {
                    feed_animalHidden.setHidden("feed_animal".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart feed_animalShow = modelMap.get("feedAnimalShow");
                if (feed_animalShow != null) {
                    feed_animalShow.setHidden(!"feed_animal".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskIdle() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart idleHidden = modelMap.get("idleHidden");

                if (idleHidden != null) {
                    idleHidden.setHidden("idle".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart idleShow = modelMap.get("idleShow");
                if (idleShow != null) {
                    idleShow.setHidden(!"idle".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskMilk() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart milkHidden = modelMap.get("milkHidden");

                if (milkHidden != null) {
                    milkHidden.setHidden("milk".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart milkShow = modelMap.get("milkShow");
                if (milkShow != null) {
                    milkShow.setHidden(!"milk".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskShears() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart shearsHidden = modelMap.get("shearsHidden");

                if (shearsHidden != null) {
                    shearsHidden.setHidden("shears".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart shearsShow = modelMap.get("shearsShow");
                if (shearsShow != null) {
                    shearsShow.setHidden(!"shears".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskSugarCane() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sugar_caneHidden = modelMap.get("sugarCaneHidden");

                if (sugar_caneHidden != null) {
                    sugar_caneHidden.setHidden("sugar_cane".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart sugar_caneShow = modelMap.get("sugarCaneShow");
                if (sugar_caneShow != null) {
                    sugar_caneShow.setHidden(!"sugar_cane".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskCocoa() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart cocoaHidden = modelMap.get("cocoaHidden");

                if (cocoaHidden != null) {
                    cocoaHidden.setHidden("cocoa".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart cocoaShow = modelMap.get("cocoaShow");
                if (cocoaShow != null) {
                    cocoaShow.setHidden(!"cocoa".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskExtinguishing() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart extinguishingHidden = modelMap.get("extinguishingHidden");

                if (extinguishingHidden != null) {
                    extinguishingHidden.setHidden("extinguishing".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart extinguishingShow = modelMap.get("extinguishingShow");
                if (extinguishingShow != null) {
                    extinguishingShow.setHidden(!"extinguishing".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskFeed() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart feedHidden = modelMap.get("feedHidden");

                if (feedHidden != null) {
                    feedHidden.setHidden("feed".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart feedShow = modelMap.get("feedShow");
                if (feedShow != null) {
                    feedShow.setHidden(!"feed".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskGrass() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart grassHidden = modelMap.get("grassHidden");

                if (grassHidden != null) {
                    grassHidden.setHidden("grass".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart grassShow = modelMap.get("grassShow");
                if (grassShow != null) {
                    grassShow.setHidden(!"grass".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskMelon() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart melonHidden = modelMap.get("melonHidden");

                if (melonHidden != null) {
                    melonHidden.setHidden("melon".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart melonShow = modelMap.get("melonShow");
                if (melonShow != null) {
                    melonShow.setHidden(!"melon".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskRangedAttack() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart ranged_attackHidden = modelMap.get("ranged_attackHidden");

                if (ranged_attackHidden != null) {
                    ranged_attackHidden.setHidden("ranged_attack".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart ranged_attackShow = modelMap.get("ranged_attackShow");
                if (ranged_attackShow != null) {
                    ranged_attackShow.setHidden(!"ranged_attack".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskSnow() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart snowHidden = modelMap.get("snowHidden");

                if (snowHidden != null) {
                    snowHidden.setHidden("snow".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart snowShow = modelMap.get("snowShow");
                if (snowShow != null) {
                    snowShow.setHidden(!"snow".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getTaskTorch() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart torchHidden = modelMap.get("torchHidden");

                if (torchHidden != null) {
                    torchHidden.setHidden("torch".equals(maid.getTask().getUid().getPath()));
                }

                JsModelPart torchShow = modelMap.get("torchShow");
                if (torchShow != null) {
                    torchShow.setHidden(!"torch".equals(maid.getTask().getUid().getPath()));
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getWingDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart wingLeft = modelMap.get("wingLeft");
                JsModelPart wingRight = modelMap.get("wingRight");

                if (wingLeft != null) {
                    wingLeft.setRotateAngleY((float) (-Math.cos(ageInTicks * 0.3) * 0.2 + 1.0));
                    wingLeft.setHidden(maid.isSleep());
                }
                if (wingRight != null) {
                    wingRight.setRotateAngleY((float) (Math.cos(ageInTicks * 0.3) * 0.2 - 1.0));
                    wingRight.setHidden(maid.isSleep());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSleepDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sleepHide = modelMap.get("sleepHide");
                JsModelPart sleepShow = modelMap.get("sleepShow");

                if (sleepHide != null) {
                    sleepHide.setHidden(maid.isSleep());
                }

                if (sleepShow != null) {
                    sleepShow.setHidden(!maid.isSleep());
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSpecialHecatia() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart earthHair = modelMap.get("earthHair");
                JsModelPart logoEarth = modelMap.get("logoEarth");
                JsModelPart earthTop = modelMap.get("earthTop");
                JsModelPart earthSideLeft = modelMap.get("earthSideLeft");
                JsModelPart earthSideRight = modelMap.get("earthSideRight");

                JsModelPart moonHair = modelMap.get("moonHair");
                JsModelPart logoMoon = modelMap.get("logoMoon");
                JsModelPart moonTop = modelMap.get("moonTop");
                JsModelPart moonSideLeft = modelMap.get("moonSideLeft");
                JsModelPart moonSideRight = modelMap.get("moonSideRight");

                JsModelPart otherHair = modelMap.get("otherHair");
                JsModelPart logoOther = modelMap.get("logoOther");
                JsModelPart otherTop = modelMap.get("otherTop");
                JsModelPart otherSideLeft = modelMap.get("otherSideLeft");
                JsModelPart otherSideRight = modelMap.get("otherSideRight");

                int dim = getDimension(maid);
                if (dim == 0) {
                    earthHair.setHidden(false);
                    logoEarth.setHidden(false);
                    earthTop.setHidden(false);
                    earthSideLeft.setHidden(true);
                    earthSideRight.setHidden(true);

                    moonHair.setHidden(true);
                    logoMoon.setHidden(true);
                    moonTop.setHidden(true);
                    moonSideLeft.setHidden(true);
                    moonSideRight.setHidden(false);

                    otherHair.setHidden(true);
                    logoOther.setHidden(true);
                    otherTop.setHidden(true);
                    otherSideLeft.setHidden(false);
                    otherSideRight.setHidden(true);
                } else if (dim == 1) {
                    earthHair.setHidden(true);
                    logoEarth.setHidden(true);
                    earthTop.setHidden(true);
                    earthSideLeft.setHidden(false);
                    earthSideRight.setHidden(true);

                    moonHair.setHidden(false);
                    logoMoon.setHidden(false);
                    moonTop.setHidden(false);
                    moonSideLeft.setHidden(true);
                    moonSideRight.setHidden(true);

                    otherHair.setHidden(true);
                    logoOther.setHidden(true);
                    otherTop.setHidden(true);
                    otherSideLeft.setHidden(true);
                    otherSideRight.setHidden(false);
                } else {
                    earthHair.setHidden(true);
                    logoEarth.setHidden(true);
                    earthTop.setHidden(true);
                    earthSideLeft.setHidden(false);
                    earthSideRight.setHidden(true);

                    moonHair.setHidden(true);
                    logoMoon.setHidden(true);
                    moonTop.setHidden(true);
                    moonSideLeft.setHidden(true);
                    moonSideRight.setHidden(false);

                    otherHair.setHidden(false);
                    logoOther.setHidden(false);
                    otherTop.setHidden(false);
                    otherSideLeft.setHidden(true);
                    otherSideRight.setHidden(true);
                }

                if (maid.hasHelmet()) {
                    earthTop.setHidden(true);
                    moonTop.setHidden(true);
                    otherTop.setHidden(true);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getSpecialWakasagihime() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                if (maid.isSitting()) {
                    if (armLeft != null) {
                        armLeft.setRotateAngleX(-0.798f);
                        armLeft.setRotateAngleZ(0.274f);
                    }
                    if (armRight != null) {
                        armRight.setRotateAngleX(-0.798f);
                        armRight.setRotateAngleZ(-0.274f);
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getPlayerArmDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                double f1 = 1.0 - Math.pow(1.0 - maid.swingProgress, 4);
                double f2 = Math.sin(f1 * Math.PI);
                double f3 = Math.sin(maid.swingProgress * Math.PI) * -0.7 * 0.75;

                if (armLeft != null) {
                    if (maid.isSitInJoyBlock()) {
                        armLeft.setRotateAngleX(-1.8f);
                    } else if (isHoldTrolley(maid)) {
                        armLeft.setRotateAngleX(0.5f);
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ(-0.395f);
                    } else if (isHoldVehicle(maid)) {
                        float[] rotation = getLeftHandRotation(maid);
                        armLeft.setRotateAngleX(rotation[0]);
                        armLeft.setRotateAngleY(rotation[1]);
                        armLeft.setRotateAngleZ(rotation[2]);
                    } else {
                        armLeft.setRotateAngleX((float) (-Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armLeft.setRotateAngleY(0);
                        armLeft.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.05) * 0.025 - 0.05));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && isSwingLeftHand(maid)) {
                            armLeft.setRotateAngleX((float) (armLeft.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armLeft.setRotateAngleZ(armLeft.getRotateAngleZ() + (float) (Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }

                if (armRight != null) {
                    if (maid.isSitInJoyBlock()) {
                        armRight.setRotateAngleX(-1.8f);
                    } else if (isHoldVehicle(maid)) {
                        float[] rotation = getRightHandRotation(maid);
                        armRight.setRotateAngleX(rotation[0]);
                        armRight.setRotateAngleY(rotation[1]);
                        armRight.setRotateAngleZ(rotation[2]);
                    } else {
                        armRight.setRotateAngleX((float) (Math.cos(limbSwing * 0.67) * 0.7 * limbSwingAmount));
                        armRight.setRotateAngleY(0);
                        armRight.setRotateAngleZ((float) (-Math.cos(ageInTicks * 0.05) * 0.025 + 0.05));
                        // 手部使用动画
                        if (maid.swingProgress > 0.0 && !isSwingLeftHand(maid)) {
                            armRight.setRotateAngleX((float) (armRight.getRotateAngleX() - (f2 * 1.2 + f3)));
                            armRight.setRotateAngleZ((float) (armRight.getRotateAngleZ() + Math.sin(maid.swingProgress * Math.PI) * -0.4));
                        }
                    }
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getPlayerSitDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                JsModelPart head = modelMap.get("head");
                JsModelPart legLeft = modelMap.get("legLeft");
                JsModelPart legRight = modelMap.get("legRight");
                JsModelPart armLeft = modelMap.get("armLeft");
                JsModelPart armRight = modelMap.get("armRight");

                // 头部复位
                if (head != null) {
                    head.setOffsetY(0);
                }

                if (isRidingMarisaBroom(maid)) {
                    // 坐在扫帚上时，应用待命的动作
                    playerRidingBroomPosture(head, armLeft, armRight, legLeft, legRight);
                } else if (maid.isRiding()) {
                    playerRidingPosture(legLeft, legRight);
                } else if (maid.isSitting()) {
                    playerSittingPosture(armLeft, armRight, legLeft, legRight);
                }
            }
        };
    }

    public static AbstractAnimation<MaidEntity> getMaidDefault() {
        return new AbstractAnimation<MaidEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, MaidEntity maid, HashMap<String, JsModelPart> modelMap) {
                getHeadDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getHeadBlink().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getHeadBeg().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getHeadMusicShake().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getLegDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getArmDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getArmSwing().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getArmVertical().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getSitDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getArmorDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getArmorReverse().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getWingDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getTailDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getSitSkirtRotation().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
                getBaseFloatDefault().setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, maid, modelMap);
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseDimDefault() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                int dim = getDimension(entity);

                JsModelPart overWorldHidden = modelMap.get("overWorldHidden");
                JsModelPart overWorldShow = modelMap.get("overWorldShow");
                JsModelPart netherWorldHidden = modelMap.get("netherWorldHidden");
                JsModelPart netherWorldShow = modelMap.get("netherWorldShow");
                JsModelPart endWorldHidden = modelMap.get("endWorldHidden");
                JsModelPart endWorldShow = modelMap.get("endWorldShow");

                if (overWorldHidden != null) {
                    overWorldHidden.setHidden(dim == 0);
                }
                if (overWorldShow != null) {
                    overWorldShow.setHidden(dim != 0);
                }
                if (netherWorldHidden != null) {
                    netherWorldHidden.setHidden(dim == -1);
                }
                if (netherWorldShow != null) {
                    netherWorldShow.setHidden(dim != -1);
                }
                if (endWorldHidden != null) {
                    endWorldHidden.setHidden(dim == 1);
                }
                if (endWorldShow != null) {
                    endWorldShow.setHidden(dim != 1);
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseFloatDefault() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart sinFloat = modelMap.get("sinFloat");
                JsModelPart cosFloat = modelMap.get("cosFloat");
                JsModelPart negativeSinFloat = modelMap.get("_sinFloat");
                JsModelPart negativeCosFloat = modelMap.get("_cosFloat");

                if (sinFloat != null) {
                    sinFloat.setOffsetY((float) (Math.sin(ageInTicks * 0.1) * 0.05));
                }
                if (cosFloat != null) {
                    cosFloat.setOffsetY((float) (Math.cos(ageInTicks * 0.1) * 0.05));
                }
                if (negativeSinFloat != null) {
                    negativeSinFloat.setOffsetY((float) (-Math.sin(ageInTicks * 0.1) * 0.05));
                }
                if (negativeCosFloat != null) {
                    negativeCosFloat.setOffsetY((float) (-Math.cos(ageInTicks * 0.1) * 0.05));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseTimeDayNight() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                World world = entity.world;
                JsModelPart dayShow = modelMap.get("dayShow");
                JsModelPart nightShow = modelMap.get("nightShow");

                if (dayShow != null) {
                    dayShow.setHidden(world.getTime() >= 13000);
                }

                if (nightShow != null) {
                    nightShow.setHidden(world.getTime() < 13000);
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseTimeGameRotation() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                long time = entity.world.getTime();
                float hourDeg = (float) (Math.PI + ((time / 1000) % 12) * (Math.PI / 6));
                float minDeg = (float) (((time % 1000) / (50 / 3)) * (Math.PI / 30));

                JsModelPart gameHourRotationX = modelMap.get("gameHourRotationX");
                JsModelPart gameMinuteRotationX = modelMap.get("gameMinuteRotationX");
                JsModelPart gameHourRotationY = modelMap.get("gameHourRotationY");
                JsModelPart gameMinuteRotationY = modelMap.get("gameMinuteRotationY");
                JsModelPart gameHourRotationZ = modelMap.get("gameHourRotationZ");
                JsModelPart gameMinuteRotationZ = modelMap.get("gameMinuteRotationZ");

                if (gameHourRotationX != null) {
                    gameHourRotationX.setRotateAngleX(hourDeg);
                }

                if (gameMinuteRotationX != null) {
                    gameMinuteRotationX.setRotateAngleX(minDeg);
                }

                if (gameHourRotationY != null) {
                    gameHourRotationY.setRotateAngleY(hourDeg);
                }

                if (gameMinuteRotationY != null) {
                    gameMinuteRotationY.setRotateAngleY(minDeg);
                }

                if (gameHourRotationZ != null) {
                    gameHourRotationZ.setRotateAngleZ(hourDeg);
                }

                if (gameMinuteRotationZ != null) {
                    gameMinuteRotationZ.setRotateAngleZ(minDeg);
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseTimeSysRotation() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                Date date = new Date();
                float hourDeg = (float) (((date.getHours() + date.getMinutes() / 60) % 12) * (Math.PI / 6));
                float minDeg = (float) ((date.getMinutes() + date.getSeconds() / 60) * (Math.PI / 30));
                float secDeg = (float) (date.getSeconds() * (Math.PI / 30));

                JsModelPart systemHourRotationX = modelMap.get("systemHourRotationX");
                JsModelPart systemMinuteRotationX = modelMap.get("systemMinuteRotationX");
                JsModelPart systemSecondRotationX = modelMap.get("systemSecondRotationX");
                JsModelPart systemHourRotationY = modelMap.get("systemHourRotationY");
                JsModelPart systemMinuteRotationY = modelMap.get("systemMinuteRotationY");
                JsModelPart systemSecondRotationY = modelMap.get("systemSecondRotationY");
                JsModelPart systemHourRotationZ = modelMap.get("systemHourRotationZ");
                JsModelPart systemMinuteRotationZ = modelMap.get("systemMinuteRotationZ");
                JsModelPart systemSecondRotationZ = modelMap.get("systemSecondRotationZ");

                if (systemHourRotationX != null) {
                    systemHourRotationX.setRotateAngleX(hourDeg);
                }

                if (systemMinuteRotationX != null) {
                    systemMinuteRotationX.setRotateAngleX(minDeg);
                }

                if (systemSecondRotationX != null) {
                    systemSecondRotationX.setRotateAngleX(secDeg);
                }

                if (systemHourRotationY != null) {
                    systemHourRotationY.setRotateAngleY(hourDeg);
                }

                if (systemMinuteRotationY != null) {
                    systemMinuteRotationY.setRotateAngleY(minDeg);
                }

                if (systemSecondRotationY != null) {
                    systemSecondRotationY.setRotateAngleY(secDeg);
                }

                if (systemHourRotationZ != null) {
                    systemHourRotationZ.setRotateAngleZ(hourDeg);
                }

                if (systemMinuteRotationZ != null) {
                    systemMinuteRotationZ.setRotateAngleZ(minDeg);
                }

                if (systemSecondRotationZ != null) {
                    systemSecondRotationZ.setRotateAngleZ(secDeg);
                }
            }
        };
    }

    public static AbstractAnimation<ChairEntity> getPassengerHidden() {
        return new AbstractAnimation<ChairEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, ChairEntity chair, HashMap<String, JsModelPart> modelMap) {
                JsModelPart passengerHidden = modelMap.get("passengerHidden");
                JsModelPart passengerShow = modelMap.get("passengerShow");

                if (passengerHidden != null) {
                    passengerHidden.setHidden(chair.hasPassengers());
                }
                if (passengerShow != null) {
                    passengerShow.setHidden(!chair.hasPassengers());
                }
            }
        };
    }

    public static AbstractAnimation<ChairEntity> getPassengerRotation() {
        return new AbstractAnimation<ChairEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, ChairEntity chair, HashMap<String, JsModelPart> modelMap) {
                JsModelPart passengerRotationYaw = modelMap.get("passengerRotationYaw");
                JsModelPart passengerRotationPitch = modelMap.get("passengerRotationPitch");

                if (passengerRotationYaw != null) {
                    passengerRotationYaw.setRotateAngleY((chair.getPassengerYaw() - chair.getYaw(1)) * 0.017453292f);
                }
                if (passengerRotationPitch != null) {
                    passengerRotationPitch.setRotateAngleX(chair.getPassengerPitch() * 0.017453292f);
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationReciprocate() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart xReciprocate = modelMap.get("xReciprocate");
                JsModelPart yReciprocate = modelMap.get("yReciprocate");
                JsModelPart zReciprocate = modelMap.get("zReciprocate");

                if (xReciprocate != null) {
                    xReciprocate.setRotateAngleX((float) (Math.cos(ageInTicks * 0.3) * 0.2));
                }
                if (yReciprocate != null) {
                    yReciprocate.setRotateAngleY((float) (Math.cos(ageInTicks * 0.3) * 0.2));
                }
                if (zReciprocate != null) {
                    zReciprocate.setRotateAngleZ((float) (Math.cos(ageInTicks * 0.3) * 0.2));
                }
            }
        };
    }


    public static AbstractAnimation<LivingEntity> getBaseRotationXH() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart xRotationHighA = modelMap.get("xRotationHighA");
                if (xRotationHighA != null) {
                    xRotationHighA.setRotateAngleX((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationHighB = modelMap.get("xRotationHighB");
                if (xRotationHighB != null) {
                    xRotationHighB.setRotateAngleX((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationHighC = modelMap.get("xRotationHighC");
                if (xRotationHighC != null) {
                    xRotationHighC.setRotateAngleX((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationHighD = modelMap.get("xRotationHighD");
                if (xRotationHighD != null) {
                    xRotationHighD.setRotateAngleX((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationHighE = modelMap.get("xRotationHighE");
                if (xRotationHighE != null) {
                    xRotationHighE.setRotateAngleX((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationXN() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netNeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart xRotationNormalA = modelMap.get("xRotationNormalA");
                if (xRotationNormalA != null) {
                    xRotationNormalA.setRotateAngleX((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart xRotationNormalB = modelMap.get("xRotationNormalB");
                if (xRotationNormalB != null) {
                    xRotationNormalB.setRotateAngleX((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart xRotationNormalC = modelMap.get("xRotationNormalC");
                if (xRotationNormalC != null) {
                    xRotationNormalC.setRotateAngleX((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart xRotationNormalD = modelMap.get("xRotationNormalD");
                if (xRotationNormalD != null) {
                    xRotationNormalD.setRotateAngleX((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart xRotationNormalE = modelMap.get("xRotationNormalE");
                if (xRotationNormalE != null) {
                    xRotationNormalE.setRotateAngleX((float) (ageInTicks % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationXL() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netLeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart xRotationLowA = modelMap.get("xRotationLowA");
                if (xRotationLowA != null) {
                    xRotationLowA.setRotateAngleX((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationLowB = modelMap.get("xRotationLowB");
                if (xRotationLowB != null) {
                    xRotationLowB.setRotateAngleX((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationLowC = modelMap.get("xRotationLowC");
                if (xRotationLowC != null) {
                    xRotationLowC.setRotateAngleX((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationLowD = modelMap.get("xRotationLowD");
                if (xRotationLowD != null) {
                    xRotationLowD.setRotateAngleX((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart xRotationLowE = modelMap.get("xRotationLowE");
                if (xRotationLowE != null) {
                    xRotationLowE.setRotateAngleX((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationYH() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart yRotationHighA = modelMap.get("yRotationHighA");
                if (yRotationHighA != null) {
                    yRotationHighA.setRotateAngleY((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationHighB = modelMap.get("yRotationHighB");
                if (yRotationHighB != null) {
                    yRotationHighB.setRotateAngleY((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationHighC = modelMap.get("yRotationHighC");
                if (yRotationHighC != null) {
                    yRotationHighC.setRotateAngleY((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationHighD = modelMap.get("yRotationHighD");
                if (yRotationHighD != null) {
                    yRotationHighD.setRotateAngleY((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationHighE = modelMap.get("yRotationHighE");
                if (yRotationHighE != null) {
                    yRotationHighE.setRotateAngleY((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationYN() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netNeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart yRotationNormalA = modelMap.get("yRotationNormalA");
                if (yRotationNormalA != null) {
                    yRotationNormalA.setRotateAngleY((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart yRotationNormalB = modelMap.get("yRotationNormalB");
                if (yRotationNormalB != null) {
                    yRotationNormalB.setRotateAngleY((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart yRotationNormalC = modelMap.get("yRotationNormalC");
                if (yRotationNormalC != null) {
                    yRotationNormalC.setRotateAngleY((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart yRotationNormalD = modelMap.get("yRotationNormalD");
                if (yRotationNormalD != null) {
                    yRotationNormalD.setRotateAngleY((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart yRotationNormalE = modelMap.get("yRotationNormalE");
                if (yRotationNormalE != null) {
                    yRotationNormalE.setRotateAngleY((float) (ageInTicks % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationYL() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netLeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart yRotationLowA = modelMap.get("yRotationLowA");
                if (yRotationLowA != null) {
                    yRotationLowA.setRotateAngleY((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationLowB = modelMap.get("yRotationLowB");
                if (yRotationLowB != null) {
                    yRotationLowB.setRotateAngleY((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationLowC = modelMap.get("yRotationLowC");
                if (yRotationLowC != null) {
                    yRotationLowC.setRotateAngleY((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationLowD = modelMap.get("yRotationLowD");
                if (yRotationLowD != null) {
                    yRotationLowD.setRotateAngleY((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart yRotationLowE = modelMap.get("yRotationLowE");
                if (yRotationLowE != null) {
                    yRotationLowE.setRotateAngleY((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationZH() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart zRotationHighA = modelMap.get("zRotationHighA");
                if (zRotationHighA != null) {
                    zRotationHighA.setRotateAngleZ((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationHighB = modelMap.get("zRotationHighB");
                if (zRotationHighB != null) {
                    zRotationHighB.setRotateAngleZ((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationHighC = modelMap.get("zRotationHighC");
                if (zRotationHighC != null) {
                    zRotationHighC.setRotateAngleZ((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationHighD = modelMap.get("zRotationHighD");
                if (zRotationHighD != null) {
                    zRotationHighD.setRotateAngleZ((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationHighE = modelMap.get("zRotationHighE");
                if (zRotationHighE != null) {
                    zRotationHighE.setRotateAngleZ((float) ((ageInTicks * 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationZN() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netNeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart zRotationNormalA = modelMap.get("zRotationNormalA");
                if (zRotationNormalA != null) {
                    zRotationNormalA.setRotateAngleZ((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart zRotationNormalB = modelMap.get("zRotationNormalB");
                if (zRotationNormalB != null) {
                    zRotationNormalB.setRotateAngleZ((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart zRotationNormalC = modelMap.get("zRotationNormalC");
                if (zRotationNormalC != null) {
                    zRotationNormalC.setRotateAngleZ((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart zRotationNormalD = modelMap.get("zRotationNormalD");
                if (zRotationNormalD != null) {
                    zRotationNormalD.setRotateAngleZ((float) (ageInTicks % 360 * 0.017453292));
                }

                JsModelPart zRotationNormalE = modelMap.get("zRotationNormalE");
                if (zRotationNormalE != null) {
                    zRotationNormalE.setRotateAngleZ((float) (ageInTicks % 360 * 0.017453292));
                }
            }
        };
    }

    public static AbstractAnimation<LivingEntity> getBaseRotationZL() {
        return new AbstractAnimation<LivingEntity>() {
            @Override
            public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netLeadYaw, float headPitch, LivingEntity entity, HashMap<String, JsModelPart> modelMap) {
                JsModelPart zRotationLowA = modelMap.get("zRotationLowA");
                if (zRotationLowA != null) {
                    zRotationLowA.setRotateAngleZ((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationLowB = modelMap.get("zRotationLowB");
                if (zRotationLowB != null) {
                    zRotationLowB.setRotateAngleZ((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationLowC = modelMap.get("zRotationLowC");
                if (zRotationLowC != null) {
                    zRotationLowC.setRotateAngleZ((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationLowD = modelMap.get("zRotationLowD");
                if (zRotationLowD != null) {
                    zRotationLowD.setRotateAngleZ((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }

                JsModelPart zRotationLowE = modelMap.get("zRotationLowE");
                if (zRotationLowE != null) {
                    zRotationLowE.setRotateAngleZ((float) ((ageInTicks / 4) % 360 * 0.017453292));
                }
            }
        };
    }

    public static void playerRidingPosture(JsModelPart legLeft, JsModelPart legRight) {
        if (legLeft != null) {
            legLeft.setRotateAngleX(-1.4f);
            legLeft.setRotateAngleY(-0.4f);
        }

        if (legRight != null) {
            legRight.setRotateAngleX(-1.4f);
            legRight.setRotateAngleY(0.4f);
        }

        GlStateManager.translate(0, 0.3, 0);
    }

    public static void playerSittingPosture(JsModelPart armLeft, JsModelPart armRight, JsModelPart legLeft, JsModelPart legRight) {
        if (armLeft != null) {
            armLeft.setRotateAngleX(-0.798f);
            armLeft.setRotateAngleZ(0.274f);
        }

        if (armRight != null) {
            armRight.setRotateAngleX(-0.798f);
            armRight.setRotateAngleZ(-0.274f);
        }

        ridingPosture(legLeft, legRight);
    }

    public static void playerRidingBroomPosture(JsModelPart head, JsModelPart armLeft, JsModelPart armRight, JsModelPart legLeft, JsModelPart legRight) {
        sittingPosture(armLeft, armRight, legLeft, legRight);
        if (head != null) {
            head.setRotateAngleX((float) (head.getRotateAngleX() - 30 * Math.PI / 180));
            head.setOffsetY(0.0625f);
        }

        GlStateManager.rotate(30, 1, 0, 0);
        GlStateManager.translate(0, -0.45, -0.3);
    }

    public static boolean isRidingMarisaBroom(MaidEntity maid) {
        return maid.getControllingPassenger() instanceof MarisaBroomEntity || maid.isDebugBroomShow;
    }

    public static void ridingPosture(JsModelPart legLeft, JsModelPart legRight) {
        if (legLeft != null) {
            legLeft.setRotateAngleX(-1.134f);
            legLeft.setRotateAngleZ(-0.262f);
        }
        if (legRight != null) {
            legRight.setRotateAngleX(-1.134f);
            legRight.setRotateAngleZ(0.262f);
        }
        GlStateManager.translate(0, 0.3, 0);
    }

    public static void sittingPosture(JsModelPart armLeft, JsModelPart armRight, JsModelPart legLeft, JsModelPart legRight) {
        if (armLeft != null) {
            armLeft.setRotateAngleX(-0.798f);
            armLeft.setRotateAngleZ(0.274f);
        }
        if (armRight != null) {
            armRight.setRotateAngleX(-0.798f);
            armRight.setRotateAngleZ(-0.274f);
        }
        ridingPosture(legLeft, legRight);
    }

    public static void ridingBroomPosture(JsModelPart head, JsModelPart armLeft, JsModelPart armRight, JsModelPart legLeft, JsModelPart legRight) {
        sittingPosture(armLeft, armRight, legLeft, legRight);
        if (head != null) {
            head.setRotateAngleX((float) (head.getRotateAngleX() - 30 * Math.PI / 180));
            head.setOffsetY(0.0625f);
        }
        GlStateManager.rotate(30, 1, 0, 0);
        GlStateManager.translate(0, -0.4, -0.3);
    }

    public static boolean isPortableAudioPlay(MaidEntity maid) {
        if (maid.getControllingPassenger() instanceof PortableAudioEntity) {
            PortableAudioEntity audio = (PortableAudioEntity) maid.getControllingPassenger();
            return audio.isPlaying();
        }
        return false;
    }

    public static boolean isHoldTrolley(MaidEntity maid) {
        return maid.getControllingPassenger() instanceof AbstractTrolleyEntity;
    }

    public static boolean isHoldVehicle(MaidEntity maid) {
        return maid.getControllingPassenger() instanceof MaidEntityVehicle;
    }

    public static boolean isSwingLeftHand(MaidEntity maid) {
        return maid.preferredHand == Hand.OFF_HAND;
    }

    public static float[] getLeftHandRotation(MaidEntity maid) {
        if (maid.getControllingPassenger() instanceof MaidEntityVehicle) {
            return ((MaidEntityVehicle) maid.getControllingPassenger()).getMaidHandRotation(Hand.OFF_HAND);
        }
        return new float[]{0, 0, 0};
    }

    public static float[] getRightHandRotation(MaidEntity maid) {
        if (maid.getControllingPassenger() instanceof MaidEntityVehicle) {
            return ((MaidEntityVehicle) maid.getControllingPassenger()).getMaidHandRotation(Hand.MAIN_HAND);
        }
        return new float[]{0, 0, 0};
    }

    public static int getDimension(LivingEntity entity){
        switch (entity.world.getDimension().getSkyProperties().getPath()){
            case "the_nether":return 1;
            case "the_end":return 2;
            default:return 0;
        }
    }

    public static HashMap<Identifier, AbstractAnimation<MaidEntity>> getMaidEntityInnerAnimation() {
        return MAID_ENTITY_INNER_ANIMATION;
    }

    public static HashMap<Identifier, AbstractAnimation<ChairEntity>> getChairEntityInnerAnimation() {
        return CHAIR_ENTITY_INNER_ANIMATION;
    }

    public static HashMap<Identifier, AbstractAnimation<LivingEntity>> getLivingEntityInnerAnimation() {
        return LIVING_ENTITY_INNER_ANIMATION;
    }
    public static void init() {
        MAID_ENTITY_INNER_ANIMATION.clear();
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/arm/default.js"), getArmDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/arm/extra.js"), getArmExtra());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/arm/swing.js"), getArmSwing());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/arm/vertical.js"), getArmVertical());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/default.js"), getArmorDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/reverse.js"), getArmorReverse());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/temp/cold.js"), getArmorTempCold());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/temp/medium.js"), getArmorTempMedium());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/temp/ocean.js"), getArmorTempOcean());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/temp/warm.js"), getArmorTempWarm());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/value/value_full.js"), getArmorValueFull());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/value/value_high.js"), getArmorValueHigh());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/value/value_low.js"), getArmorValueLow());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/value/value_normal.js"), getArmorValueNormal());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/weather/raining.js"), getArmorWeatherRainging());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/armor/weather/thundering.js"), getArmorWeatherThundering());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/beg.js"), getHeadBeg());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/blink.js"), getHeadBlink());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/default.js"), getHeadDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/extra.js"), getHeadExtra());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/hurt.js"), getHeadHurt());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/music_shake.js"), getHeadMusicShake());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/head/reverse_blink.js"), getHeadReverseBlink());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/health/less_show.js"), getHealthLessShow());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/health/more_show.js"), getHealthMoreShow());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/health/rotation.js"), getHealthRotation());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/leg/default.js"), getLegDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/leg/extra.js"), getLegExtra());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/leg/vertical.js"), getLegVertical());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/sit/default.js"), getSitDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/sit/skirt_hidden.js"), getSitSkirtHidden());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/sit/skirt_rotation.js"), getSitSkirtRotation());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/status/backpack.js"), getStatusBackpack());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/status/backpack_level.js"), getStatusBackpackLevel());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/status/moving.js"), getStatusMoving());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/status/sasimono.js"), getStatusSasimono());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/tail/default.js"), getTailDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/attack.js"), getTaskAttack());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/danmaku_attack.js"), getTaskDanmakuAttack());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/farm.js"), getTaskFarm());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/feed_animal.js"), getTaskFeedAnimal());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/idle.js"), getTaskIdle());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/milk.js"), getTaskMilk());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/shears.js"), getTaskShears());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/sugar_cane.js"), getTaskSugarCane());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/cocoa.js"), getTaskCocoa());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/extinguishing.js"), getTaskExtinguishing());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/feed.js"), getTaskFeed());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/grass.js"), getTaskGrass());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/melon.js"), getTaskMelon());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/ranged_attack.js"), getTaskRangedAttack());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/snow.js"), getTaskSnow());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/task/torch.js"), getTaskTorch());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/wing/default.js"), getWingDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/wing/default.js"), getWingDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/default/sleep/default.js"), getSleepDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid/player/arm/default.js"), getPlayerArmDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/maid.default.js"), getMaidDefault());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/special/hecatia_dimension.js"), getSpecialHecatia());
        MAID_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/special/wakasagihime_sit.js"), getSpecialWakasagihime());

        CHAIR_ENTITY_INNER_ANIMATION.clear();
        CHAIR_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/chair/passenger/hidden.js"), getPassengerHidden());
        CHAIR_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/chair/passenger/rotation.js"), getPassengerRotation());

        LIVING_ENTITY_INNER_ANIMATION.clear();
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/dimension/default.js"), getBaseDimDefault());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/float/default.js"), getBaseFloatDefault());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/time/day_night_hidden.js"), getBaseTimeDayNight());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/time/game_rotation.js"), getBaseTimeGameRotation());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/time/system_rotation.js"), getBaseTimeSysRotation());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/reciprocate.js"), getBaseRotationReciprocate());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/x_high_speed.js"), getBaseRotationXH());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/x_normal_speed.js"), getBaseRotationXN());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/x_low_speed.js"), getBaseRotationXL());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/y_high_speed.js"), getBaseRotationYH());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/y_normal_speed.js"), getBaseRotationYN());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/y_low_speed.js"), getBaseRotationYL());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/z_high_speed.js"), getBaseRotationZH());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/z_normal_speed.js"), getBaseRotationZN());
        LIVING_ENTITY_INNER_ANIMATION.put(new Identifier("touhou_little_maid:animation/base/rotation/z_low_speed.js"), getBaseRotationZL());
    }
}
