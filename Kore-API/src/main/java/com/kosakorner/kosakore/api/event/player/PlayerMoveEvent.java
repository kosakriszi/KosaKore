package com.kosakorner.kosakore.api.event.player;

import com.kosakorner.kosakore.api.entity.IPlayer;
import com.kosakorner.kosakore.api.event.Cancelable;
import com.kosakorner.kosakore.api.world.Location;

public class PlayerMoveEvent extends PlayerEvent implements Cancelable {

    protected static boolean canceled;

    private Location from;
    private Location to;

    public PlayerMoveEvent(IPlayer player, Location from, Location to) {
        super(player);
        canceled = false;
        this.from = from;
        this.to = to;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCancelled(boolean state) {
        canceled = state;
    }
}
