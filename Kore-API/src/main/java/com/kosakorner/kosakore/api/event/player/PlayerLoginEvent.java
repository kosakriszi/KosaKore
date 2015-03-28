package com.kosakorner.kosakore.api.event.player;

import com.kosakorner.kosakore.api.entity.IPlayer;

import java.net.InetAddress;

public class PlayerLoginEvent extends PlayerEvent {

    private final InetAddress address;
    private final String      hostname;
    private       String      message;

    public PlayerLoginEvent(IPlayer player, String hostname, InetAddress address, String message) {
        super(player);
        this.hostname = hostname;
        this.address = address;
        this.message = message;
    }

    public InetAddress getAddress() {
        return address;
    }

    public String getHostname() {
        return hostname;
    }

    public String getKickMessage() {
        return message;
    }

}
