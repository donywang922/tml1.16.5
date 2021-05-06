package bilibili.ywsuoyi.client.model;

import it.unimi.dsi.fastutil.objects.ObjectList;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.ModelPart;

public class JsModelPart extends ModelPart {
    public float offsetX;
    public float offsetY;
    public float offsetZ;

    public JsModelPart(Model model) {
        super(model);
    }
    public float getRotateAngleX() {
        return this.pitch;
    }

    public void setRotateAngleX(float rotateAngleX) {
        this.pitch = rotateAngleX;
    }

    public float getRotateAngleY() {
        return this.yaw;
    }

    public void setRotateAngleY(float rotateAngleY) {
        this.yaw = rotateAngleY;
    }

    public float getRotateAngleZ() {
        return this.roll;
    }

    public void setRotateAngleZ(float rotateAngleZ) {
        this.roll = rotateAngleZ;
    }

    public float getOffsetX() {
        return this.offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return this.offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public float getOffsetZ() {
        return this.offsetZ;
    }

    public void setOffsetZ(float offsetZ) {
        this.offsetZ = offsetZ;
    }

    public float getRotationPointX() {
        return this.pivotX;
    }

    public float getRotationPointY() {
        return this.pivotY;
    }

    public float getRotationPointZ() {
        return this.pivotZ;
    }

    public boolean isHidden() {
        return !this.visible;
    }

    public void setHidden(boolean hidden) {
        this.visible = !hidden;
    }

    public void addCuboid(int u, int v, float x, float y, float z, float sizeX, float sizeY, float sizeZ, float extra, boolean mirror){
        this.setTextureOffset(u,v);
        this.addCuboid(x,y,z,sizeX,sizeY,sizeZ,extra,mirror);
    }


}
