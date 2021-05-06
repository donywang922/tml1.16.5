package bilibili.ywsuoyi.item;

import bilibili.ywsuoyi.client.resources.model.ModelManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.io.IOException;

public class testitem extends Item {
    public testitem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            try {
                ModelManager.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}
