package com.kosakorner.kosakore.bukkit.event;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.event.Cancelable;
import com.kosakorner.kosakore.api.event.Event;
import com.kosakorner.kosakore.bukkit.entity.BukkitPlayer;
import com.kosakorner.kosakore.bukkit.util.ConversionUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class BukkitEventHandler implements Listener {

    private IKore kore;

    public BukkitEventHandler(IKore kore) {
        this.kore = kore;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        Event koreEvent = new com.kosakorner.kosakore.api.event.player.PlayerLoginEvent(new BukkitPlayer(event.getPlayer()), event.getHostname(), event.getAddress(), event.getKickMessage());
        kore.getEventBus().fire(koreEvent);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerPortal(PlayerPortalEvent event) {
        com.kosakorner.kosakore.api.event.player.PlayerPortalEvent koreEvent = new com.kosakorner.kosakore.api.event.player.PlayerPortalEvent(new BukkitPlayer(event.getPlayer()), ConversionUtils.toKoreLocation(event.getFrom()), ConversionUtils.toKoreLocation(event.getTo()));
        com.kosakorner.kosakore.api.event.player.PlayerPortalEvent returned = kore.getEventBus().fire(koreEvent);
        event.setFrom(ConversionUtils.fromKoreLocation(returned.getFrom()));
        event.setTo(ConversionUtils.fromKoreLocation(returned.getTo()));
        event.setCancelled(returned.isCanceled());
    }

}
