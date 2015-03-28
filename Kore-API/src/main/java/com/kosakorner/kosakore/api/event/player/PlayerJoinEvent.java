package com.kosakorner.kosakore.api.event.player;

import com.kosakorner.kosakore.api.entity.IPlayer;

public class PlayerJoinEvent extends PlayerEvent {

    public PlayerJoinEvent(IPlayer player, String joinMessage) {
        super(player);
    }

}
