package bilibili.ywsuoyi.registry;

import bilibili.ywsuoyi.client.render.MaidEntityRender;
import bilibili.ywsuoyi.entity.maid.MaidEntity;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Entities {
    public static final EntityType<MaidEntity> MAID = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("entitytesting", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC, MaidEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.5f)).build()
    );

    public static void initAttribute() {
        FabricDefaultAttributeRegistry.register(MAID, MaidEntity.createMaidAttributes());
    }

    public static void registerRenderer() {
        EntityRendererRegistry.INSTANCE.register(MAID, (dispatcher, context) -> new MaidEntityRender(dispatcher));
    }
}
