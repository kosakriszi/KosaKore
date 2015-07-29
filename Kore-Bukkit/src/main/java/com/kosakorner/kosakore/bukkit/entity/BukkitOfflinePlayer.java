package com.kosakorner.kosakore.bukkit.entity;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.api.type.EntityType;
import com.kosakorner.kosakore.api.world.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public class BukkitOfflinePlayer implements IPlayer {

    private OfflinePlayer backingPlayer;

    public BukkitOfflinePlayer(OfflinePlayer player) {
        backingPlayer = player;
    }

    public UUID getUniqueID() {
        return backingPlayer.getUniqueId();
    }

    public String getName() {
        return backingPlayer.getName();
    }

    public String getDisplayName() {
        return backingPlayer.getName();
    }

    public void setDisplayName(String name) {

    }

    public IInventory getInventory() {
        return null;
    }

    public String getAddress() {
        return null;
    }

    public void kickPlayer(String reason) {

    }

    public void chat(String message) {

    }

    public boolean executeCommand(String command) {
        return false;
    }

    public boolean isSneaking() {
        return false;
    }

    public void setSneaking(boolean state) {

    }

    public boolean isSprinting() {
        return false;
    }

    public void setSprinting(boolean state) {

    }

    public void giveExp(int amount) {

    }

    public void sendMessage(String message) {

    }

    public boolean hasPermission(String node) {
        return false;
    }

    public Location getLocation() {
        return null;
    }

    public void teleport(Location location) {

    }

    public EntityType getType() {
        return EntityType.PLAYER;
    }

    public void remove() {

    }
}
