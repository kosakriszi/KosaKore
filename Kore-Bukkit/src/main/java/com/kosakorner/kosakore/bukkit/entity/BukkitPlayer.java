package com.kosakorner.kosakore.bukkit.entity;

import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.command.SenderType;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.inventory.IInventory;
import com.kosakorner.kosakore.bukkit.inventory.BukkitInventory;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;

public class BukkitPlayer extends BukkitEntity implements IPlayer, ICommandSender {

    private Player backingPlayer;

    public BukkitPlayer(Player player) {
        super(player);
        backingPlayer = player;
    }

    public UUID getUniqueID() {
        return backingPlayer.getUniqueId();
    }

    public String getName() {
        return backingPlayer.getName();
    }

    public String getDisplayName() {
        return backingPlayer.getDisplayName();
    }

    public void setDisplayName(String name) {
        backingPlayer.setDisplayName(name);
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

    public SenderType getSenderType() {
        return SenderType.PLAYER;
    }

    public void sendMessage(String message) {
        backingPlayer.sendMessage(message);
    }

    public boolean hasPermission(String node) {
        return backingPlayer.hasPermission(node);
    }

}
