package bilibili.ywsuoyi.client;

import bilibili.ywsuoyi.registry.Entities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class YwsuoyiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        Entities.registerRenderer();
    }
}
//15728880
