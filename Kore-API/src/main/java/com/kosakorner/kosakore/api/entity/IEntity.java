package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.type.EntityType;
import com.kosakorner.kosakore.api.world.Location;

public interface IEntity {

    Location getLocation();

    void teleport(Location location);

    EntityType getType();

    void remove();

}
