package de.nicfx.joar.mob;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;

import javax.annotation.Nullable;
import java.util.UUID;

public class ActionMob {


    private final EntityType entityClass;
    private boolean isSpawned;
    private UUID mobID;
    private LivingEntity entity;
    private World world;

    public ActionMob(EntityType entityType) {
        this.entityClass = entityType;
    }

    public void spawn(Location location) {
        this.entity = (LivingEntity) world.spawn(location, entityClass.getEntityClass());
        this.isSpawned = true;
        this.mobID = entity.getUniqueId();
    }

    public void teleport(int x, int y, int z) {
        entity.teleport(new Location(world, x, y, z));
    }

    public void teleport(Location location) {
        entity.teleport(location);
    }

    public void sHealth(double health) {
        entity.setHealth(health);
    }

    public void sMaxHealth(double health) {
        entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(health);
    }

    public void sAttackEntity(LivingEntity entity) {
        if (entity instanceof Mob mob) {
            mob.setTarget(entity);
        }
    }


    @Nullable
    public LivingEntity getEntity() {
        if (isSpawned) {
            return entity;
        }
        return null;
    }

    public void delete() {
        this.entity.remove();
        this.isSpawned = false;
    }

    public boolean isSpawned() {
        return isSpawned;
    }
}
