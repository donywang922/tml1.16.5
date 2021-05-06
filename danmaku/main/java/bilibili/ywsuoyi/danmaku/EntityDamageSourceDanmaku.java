package bilibili.ywsuoyi.danmaku;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.EntityDamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;


public class EntityDamageSourceDanmaku extends EntityDamageSource {
    public EntityDamageSourceDanmaku(Entity source, Entity indirectEntityIn) {
        super("magic", source);
        this.setBypassesArmor();
        this.setUsesMagic();
        this.setProjectile();
    }

    @Override
    public Text getDeathMessage(LivingEntity victim) {
        if (getAttacker() != null) {
            int index = victim.world.random.nextInt(3) + 1;
            return new TranslatableText(String.format("death.touhou_little_maid.attack.danmaku.%d", index),
                    victim.getDisplayName(), getAttacker().getDisplayName());
        }
        return new TranslatableText("death.touhou_little_maid.attack.danmaku.4",victim.getDisplayName());
    }
}
