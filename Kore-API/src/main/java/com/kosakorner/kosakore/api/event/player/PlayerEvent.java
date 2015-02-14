package com.kosakorner.kosakore.api.event.player;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.event.Event;

public class PlayerEvent extends Event {

    protected IPlayer player;

    public PlayerEvent(IPlayer player) {
        super();
        this.player = player;
    }

    public IPlayer getPlayer() {
        return player;
    }

}
