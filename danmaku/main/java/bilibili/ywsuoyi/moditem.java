package bilibili.ywsuoyi;

import bilibili.ywsuoyi.items.ItemDebugDanmaku;
import net.minecraft.item.Item;

public class moditem {
    public static final ItemDebugDanmaku ItemDebugDanmaku=new ItemDebugDanmaku(new Item.Settings().group(Touhoulittlemaid.touhoulittlemaidf));
    public static void regall(){
        qr.ri("debug_danmaku",ItemDebugDanmaku);
    }
}
