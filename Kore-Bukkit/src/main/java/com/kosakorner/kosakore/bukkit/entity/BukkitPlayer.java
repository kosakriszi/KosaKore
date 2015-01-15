package com.kosakorner.kosakore.bukkit.entity;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.world.Location;
import com.kosakorner.kosakore.bukkit.inventory.BukkitInventory;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;

public class BukkitPlayer implements IPlayer {

    private Player backingPlayer;

    public BukkitPlayer(Player player) {
        backingPlayer = player;
    }

    public String getDisplayName() {
        return backingPlayer.getDisplayName();
    }

    public void setDisplayName(String name) {
        backingPlayer.setDisplayName(name);
    }

    public UUID getUniqueID() {
        return backingPlayer.getUniqueId();
    }

    public Location getLocation() {
        return ConversionUtils.toKoreLocation(backingPlayer.getLocation());
    }

    public IInventory getInventory() {
        return new BukkitInventory(backingPlayer.getInventory());
    }

    public InetSocketAddress getAddress() {
        return backingPlayer.getAddress();
    }

    public void kickPlayer(String reason) {
        backingPlayer.kickPlayer(reason);
    }

    public void chat(String message) {
        backingPlayer.chat(message);
    }

    public void sendMessage(String message) {
        backingPlayer.sendMessage(message);
    }

    public boolean executeCommand(String command) {
        return backingPlayer.performCommand(command);
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

    public void teleport(Location location) {
        backingPlayer.teleport(ConversionUtils.fromKoreLocation(location));
    }

    public boolean hasPermission(String node) {
        return backingPlayer.hasPermission(node);
    }

}
