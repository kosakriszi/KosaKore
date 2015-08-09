package com.kosakorner.kosakore.bukkit.entity;

import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.bukkit.inventory.BukkitInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BukkitPlayer extends BukkitEntity implements IPlayer, ICommandSender {

    private Player backingPlayer;

    public BukkitPlayer(Player player) {
        super(player);
        backingPlayer = player;
    }

    public Player getBackingPlayer() {
        return backingPlayer;
    }

    public UUID getUniqueID() {
        return backingPlayer.getUniqueId();
    }

    public String getName() {
        return backingPlayer.getName();
    }

    public String getDisplayName() {
        if (backingPlayer.getDisplayName() != null) {
            return backingPlayer.getDisplayName();
        }
        else {
            return backingPlayer.getName();
        }
    }

    public void setDisplayName(String name) {
        backingPlayer.setDisplayName(name);
    }

    public IInventory getInventory() {
        return new BukkitInventory(backingPlayer.getInventory());
    }

    public String getAddress() {
        return backingPlayer.getAddress().toString();
    }

    public void kickPlayer(String reason) {
        backingPlayer.kickPlayer(reason);
    }

    public void chat(String message) {
        backingPlayer.chat(message);
    }

    public boolean executeCommand(String command) {
        return Bukkit.dispatchCommand(backingPlayer, command);
    }

    public boolean isSneaking() {
        return backingPlayer.isSneaking();
    }

    public void setSneaking(boolean state) {
        backingPlayer.setSneaking(state);
    }

    public boolean isSprinting() {
        return backingPlayer.isSprinting();
    }

    public void setSprinting(boolean state) {
        backingPlayer.setSprinting(state);
    }

    public void giveExp(int amount) {
        backingPlayer.giveExp(amount);
    }

    public void sendMessage(String message) {
        backingPlayer.sendMessage(message);
    }

    public boolean hasPermission(String node) {
        return backingPlayer.hasPermission(node);
    }

}
