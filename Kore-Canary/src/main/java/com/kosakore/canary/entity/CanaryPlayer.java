package com.kosakore.canary.entity;

import com.kosakore.canary.inventory.CanaryInventory;
import com.kosakorner.kosakore.api.command.ICommandSender;
import com.kosakorner.kosakore.api.command.SenderType;
import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.inventory.IInventory;
import net.canarymod.api.entity.living.humanoid.Player;

import java.util.UUID;

public class CanaryPlayer extends CanaryEntity implements IPlayer, ICommandSender {

    private Player backingPlayer;

    public CanaryPlayer(Player player) {
        super(player);
        backingPlayer = player;
    }

    public UUID getUniqueID() {
        return backingPlayer.getUUID();
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
        return new CanaryInventory(backingPlayer.getInventory());
    }

    public String getAddress() {
        return backingPlayer.getIP();
    }

    public void kickPlayer(String reason) {
        backingPlayer.kick(reason);
    }

    public void chat(String message) {
        backingPlayer.chat(message);
    }

    public boolean executeCommand(String command) {
        return backingPlayer.executeCommand(new String[]{command});
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
        backingPlayer.addExperience(amount);
    }

    public SenderType getSenderType() {
        return SenderType.PLAYER;
    }

    public void sendMessage(String message) {
        backingPlayer.message(message);
    }

    public boolean hasPermission(String node) {
        return backingPlayer.hasPermission(node);
    }

}
