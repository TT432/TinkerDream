package oldmoon.dustw.tinkerdream.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import oldmoon.dustw.tinkerdream.TinkerDream;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * @author NmmOC7
 */
@Mod.EventBusSubscriber
public class EntityRegistry {
    public static final ArrayList<EntityEntry> ENTITY_LIST = new ArrayList<>();

    public static int id = 0;

    @SubscribeEvent
    public static void onEntityRegistry(RegistryEvent.Register<EntityEntry> event) {
        entitiesInit();

        event.getRegistry().registerAll(ENTITY_LIST.toArray(new EntityEntry[0]));
    }

    public static void entitiesInit() {
        arrowBuild("seeker_arrow", EntitySeekerArrow.class, EntitySeekerArrow::new);

        projectileBuild(EntityMagicMissile.class, "magic_missile");
    }

    public static <T extends EntityArrow> void arrowBuild(String name, Class<T> arrow, Function<World, EntityArrow> factory) {
        EntityEntry arrowTemp = EntityEntryBuilder
                .<EntityArrow>create()
                .id(TinkerDream.MOD_ID + name, id++)
                .name(name)
                .entity(arrow)
                .factory(factory)
                .tracker(150, 1, true)
                .build();

        ENTITY_LIST.add(arrowTemp);
    }

    private static <T extends Entity> void projectileBuild(Class<T> entityClass, String name){
        ResourceLocation registryName = new ResourceLocation(TinkerDream.MOD_ID, name);
        EntityEntry entityTemp = EntityEntryBuilder
                .<T>create()
                .entity(entityClass)
                .id(registryName, id++)
                .name(registryName.toString())
                .tracker(64, 10, true)
                .build();

        ENTITY_LIST.add(entityTemp);
    }
}
