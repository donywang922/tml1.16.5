package bilibili.ywsuoyi;

import bilibili.ywsuoyi.client.resources.model.ModelManager;
import bilibili.ywsuoyi.item.testitem;
import bilibili.ywsuoyi.registry.Entities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.IOException;

public class Ywsuoyi implements ModInitializer {
    public static final String MOD_ID = "touhou_little_maid";
    @Override
    public void onInitialize() {
        Entities.initAttribute();

        Registry.register(Registry.ITEM,new Identifier("tml","test"),new testitem(new Item.Settings().group(ItemGroup.FOOD)));

        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            try {
                ModelManager.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> {
            try {
                ModelManager.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}
