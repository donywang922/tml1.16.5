package bilibili.ywsuoyi;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class qr {
    public static void ri(String name, Item item){
        Registry.register(Registry.ITEM,new Identifier("touhou_little_maid",name),item);
    }
    public static void rb(String name, Block block){
        Registry.register(Registry.BLOCK,new Identifier("touhou_little_maid",name),block);
        Registry.register(Registry.ITEM, new Identifier("touhou_little_maid", name), new BlockItem(block, new Item.Settings().group(ItemGroup.MISC)));
    }
}
