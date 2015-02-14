package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.inventory.IInventory;

import java.net.InetSocketAddress;
import java.util.UUID;

public interface IPlayer extends IEntity {

    public UUID getUniqueID();

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

    public boolean hasPermission(String node);

}
