package bilibili.ywsuoyi.model;

import bilibili.ywsuoyi.entity.danmakuentity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class objmodel<T extends danmakuentity> extends EntityModel<T> {
    public final objmodelpart obj;

    public objmodel(Identifier path){
        this.obj= new objmodelpart(this,0,0);
        this.obj.loadobj(path.getPath());
        this.obj.setTextureSize(32,32);
    }

    @Override
    public void setAngles(T entity, float limbAngle, float limbDistance, float customAngle, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        obj.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}
