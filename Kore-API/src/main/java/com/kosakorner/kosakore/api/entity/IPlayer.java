package com.kosakorner.kosakore.api.entity;

import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.inventory.IInventory;

import java.util.UUID;

public interface IPlayer extends ICommandSender, IEntity {

    UUID getUniqueID();

    String getName();

    String getDisplayName();

    void setDisplayName(String name);

    IInventory getInventory();

    String getAddress();

    void kickPlayer(String reason);

    void chat(String message);

    boolean executeCommand(String command);

    boolean isSneaking();

    void setSneaking(boolean state);

    boolean isSprinting();

    void setSprinting(boolean state);

    void giveExp(int amount);

}
