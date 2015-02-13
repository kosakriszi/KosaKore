package com.kosakorner.kosakore.api.event.player;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.world.Location;

public class PlayerPortalEvent extends PlayerMoveEvent {

    public PlayerPortalEvent(IPlayer player, Location from, Location to) {
        super(player, from, to);
    }



}
