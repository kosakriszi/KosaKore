package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.world.Location;

import java.net.InetSocketAddress;
import java.util.UUID;

public interface IPlayer {

    public String getDisplayName();

    public void setDisplayName(String name);

    public UUID getUniqueID();

    public Location getLocation();

    public IInventory getInventory();

    public InetSocketAddress getAddress();

    public void kickPlayer(String reason);

    public void chat(String message);

    public void sendMessage(String message);

    public boolean executeCommand(String command);

    public boolean isSneaking();

    public void setSneaking(boolean state);

    public boolean isSprinting();

    public void setSprinting(boolean state);

    public void giveExp(int amount);

    public void teleport(Location location);

    public boolean hasPermission(String node);

}
