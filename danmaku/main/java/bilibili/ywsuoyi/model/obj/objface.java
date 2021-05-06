package bilibili.ywsuoyi.model.obj;

import bilibili.ywsuoyi.model.objmodelpart;
import net.minecraft.client.model.ModelPart;

public class objface {
    public final facepoint[] face = new facepoint[4];

    public objface(String[] face) {
        this.face[0] = new facepoint(face[1].split("/"));
        this.face[1] = new facepoint(face[2].split("/"));
        this.face[2] = new facepoint(face[3].split("/"));
        if(face.length<5){
            this.face[3] = this.face[2];
        }else {
            this.face[3] = new facepoint(face[4].split("/"));
        }
    }
    public static class facepoint {
        public facepoint(int v,int n, int uv){
            this.v=v;
            this.n=n;
            this.uv=uv;
        }
        public facepoint(String...i){
            this.v=Integer.parseInt(i[0])-1;
            this.uv=Integer.parseInt(i[1])-1;
            this.n=Integer.parseInt(i[2])-1;
        }

        public int v;
        public int uv;
        public int n;
    }
}
