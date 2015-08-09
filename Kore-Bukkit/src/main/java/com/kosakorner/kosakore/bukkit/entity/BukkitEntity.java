package com.kosakorner.kosakore.bukkit.entity;

import com.kosakorner.kosakore.api.entity.IEntity;
import com.kosakorner.kosakore.api.type.EntityType;
import com.kosakorner.kosakore.api.world.Location;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.entity.Entity;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BukkitEntity implements IEntity {

    private Entity backingEntity;

    public BukkitEntity(Entity backingEntity) {
        this.backingEntity = backingEntity;
    }

    public Location getLocation() {
        return ConversionUtils.toKoreLocation(backingEntity.getLocation());
    }

    public void teleport(Location location) {
        backingEntity.teleport(ConversionUtils.fromKoreLocation(location), PlayerTeleportEvent.TeleportCause.NETHER_PORTAL);
    }

    public EntityType getType() {
        return EntityType.get(backingEntity.getType().getTypeId());
    }

    public void remove() {
        backingEntity.remove();
    }

}
