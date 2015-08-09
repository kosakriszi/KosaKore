package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface IWorldGuardAdapter extends IAdapter {

    void createRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members);

    void updateRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members);

    void removeRegion(String regionID, IWorld world);

    void setGreetMessage(String regionID, IWorld world, String message);

    void setFarewellMessage(String regionID, IWorld world, String message);

    void setPVP(String regionID, IWorld world, boolean state);

    void setEntry(String regionID, IWorld world, boolean state);

    Set<String> getApplicableRegions(Location location);

}
