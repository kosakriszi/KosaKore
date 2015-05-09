package com.kosakore.canary.entity;

import com.kosakore.canary.util.ConversionUtils;
import com.kosakorner.kosakore.api.entity.IEntity;
import com.kosakorner.kosakore.api.type.EntityType;
import com.kosakorner.kosakore.api.world.Location;
import net.canarymod.api.entity.Entity;

public class CanaryEntity implements IEntity {

    private Entity backingEntity;

    public CanaryEntity(Entity backingEntity) {
        this.backingEntity = backingEntity;
    }

    public Location getLocation() {
        return ConversionUtils.toKoreLocation(backingEntity.getLocation());
    }

    public void teleport(Location location) {
        backingEntity.setX(location.getX());
        backingEntity.setY(location.getY());
        backingEntity.setZ(location.getZ());
    }

    public EntityType getType() {
        return EntityType.get(backingEntity.getEntityType().getEntityID());
    }

    public void remove() {
        backingEntity.destroy();
    }

}
