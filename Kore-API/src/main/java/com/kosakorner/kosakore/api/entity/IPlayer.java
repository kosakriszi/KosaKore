package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.inventory.IInventory;

import java.util.UUID;

public interface IPlayer extends ICommandSender, IEntity {

    public UUID getUniqueID();

    public String getName();

    public String getDisplayName();

    public void setDisplayName(String name);

    public IInventory getInventory();

    public String getAddress();

    public void kickPlayer(String reason);

    public void chat(String message);

    public boolean executeCommand(String command);

    public boolean isSneaking();

    public void setSneaking(boolean state);

    public boolean isSprinting();

    public void setSprinting(boolean state);

    public void giveExp(int amount);

}
