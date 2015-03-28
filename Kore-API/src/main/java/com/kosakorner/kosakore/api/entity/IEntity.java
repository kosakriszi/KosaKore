package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.type.EntityType;
import com.kosakorner.kosakore.api.world.Location;

public interface IEntity {

    public Location getLocation();

    public void teleport(Location location);

    public EntityType getType();

    public void remove();

}
