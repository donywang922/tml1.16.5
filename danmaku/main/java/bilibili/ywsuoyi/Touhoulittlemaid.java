package bilibili.ywsuoyi;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class Touhoulittlemaid implements ModInitializer {
    public static final ItemGroup touhoulittlemaidf = FabricItemGroupBuilder.build(
            new Identifier("touhou_little_maid", "first"),
            () -> new ItemStack(moditem.ItemDebugDanmaku));
    @Override
    public void onInitialize() {
        moditem.regall();
    }
}
