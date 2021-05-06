package bilibili.ywsuoyi.items;

import bilibili.ywsuoyi.danmaku.DanmakuColor;
import bilibili.ywsuoyi.danmaku.DanmakuType;
import bilibili.ywsuoyi.entity.danmakuentity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * @author TartaricAcid
 * @date 2019/12/1 14:08
 * @author Ywsuoyi
 * @date 2020/5/22 16:31
 **/
public class ItemDebugDanmaku extends Item {
    private static final String TYPE_TAG_NAME = "DanmakuType";
    private static final Random RANDOM = new Random();

    public ItemDebugDanmaku(Settings settings) {
        super(settings);
    }

    private static void addType(ItemStack stack) {
        CompoundTag tag;
        if (stack.getTag()!=null) {
            tag = stack.getTag();
            if (tag.contains(TYPE_TAG_NAME)) {
                tag.putInt(TYPE_TAG_NAME, (tag.getInt(TYPE_TAG_NAME) + 1) % DanmakuType.values().length);
            } else {
                tag.putInt(TYPE_TAG_NAME, 0);
            }
        } else {
            tag = new CompoundTag();
            tag.putInt(TYPE_TAG_NAME, 0);
        }
        stack.setTag(tag);
    }

    private static DanmakuType getType(ItemStack stack) {
        if (stack.getTag()!=null && stack.getTag().contains(TYPE_TAG_NAME)) {
            int index = stack.getTag().getInt(TYPE_TAG_NAME);
            return DanmakuType.getType(index);
        } else {
            return DanmakuType.PELLET;
        }
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (hand == Hand.MAIN_HAND) {
            ItemStack stack = user.getStackInHand(hand);
            if (user.isSneaking()) {
                addType(stack);
                if (!world.isClient) {
                    String typeKey = String.format("danmaku_type.touhou_little_maid.%s", getType(stack).name().toLowerCase(Locale.US));
                    user.addChatMessage(new TranslatableText("message.touhou_little_maid.debug_danmaku.type",
                            new TranslatableText(typeKey)), true);
                }
            } else {
                if(!world.isClient){
                DanmakuType type = getType(stack);
                DanmakuColor color = DanmakuColor.getColor(RANDOM.nextInt(DanmakuColor.getLength() + 1));
                System.out.println(type);
                System.out.println(color);
                shootDanmaku(world, user, type, color);
                }

            }
            return TypedActionResult.success(stack);
        }else {
            return TypedActionResult.fail(user.getStackInHand(hand));
        }

    }

    private void shootDanmaku(World world, PlayerEntity player, DanmakuType type, DanmakuColor color) {
        danmakuentity danmaku = new danmakuentity(world, player, 2, 1, type, color);
        world.spawnEntity(danmaku);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, player.getSoundCategory(), 1.0f, 0.8f);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack,World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(new TranslatableText("tooltips.touhou_little_maid.debug_danmaku.desc"));
        tooltip.add(new TranslatableText("message.touhou_little_maid.debug_danmaku.type",
                new TranslatableText(String.format("danmaku_type.touhou_little_maid.%s", getType(stack).name().toLowerCase(Locale.US)))));
    }
}
