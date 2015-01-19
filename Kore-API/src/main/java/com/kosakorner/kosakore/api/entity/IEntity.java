package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.world.Location;

public interface IEntity {

    public String getDisplayName();

    public void setDisplayName(String name);

    public Location getLocation();

    public void teleport(Location location);

}
