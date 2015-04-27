package com.kosakorner.kosakore.api.adapter;

import com.kosakorner.kosakore.api.world.IWorld;
import com.kosakorner.kosakore.api.world.Location;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface IWorldGuardAdapter extends IAdapter {

    public void createRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members);

    public void updateRegion(String regionID, UUID owner, Location vectorTop, Location vectorBottom, Collection members);

    public void removeRegion(String regionID, IWorld world);

    public void setGreetMessage(String regionID, IWorld world, String message);

    public void setFarewellMessage(String regionID, IWorld world, String message);

    public void setPVP(String regionID, IWorld world, boolean state);

    public Set<String> getApplicableRegions(Location location);

}
