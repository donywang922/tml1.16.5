package com.github.tartaricacid.touhoulittlemaid.client.animation.script;

import net.minecraft.client.util.math.Vector3f;
import net.minecraft.util.math.Quaternion;

public class GlStateManager {
    public static Vector3f translate = new Vector3f();
    public static Quaternion rotate = new Quaternion(new Vector3f(0, 0, 0), 0, true);

    public static void rotate(double degree, int x, int y, int z) {

        degree *= 0.017453292F;


        float f = (float) Math.sin(degree / 2.0F);
        rotate.set(x * f, y * f, z * f, (float) Math.cos(degree / 2.0F));
    }

    public static void translate(double x, double y, double z) {
        translate.set((float) x, (float) y, (float) z);

    }

    public static void clear(){
        rotate.set(0,0,0,0);
        translate.set(0,0,0);
    }
}
