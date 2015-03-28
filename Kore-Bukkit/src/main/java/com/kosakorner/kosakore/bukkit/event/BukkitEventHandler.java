package com.kosakorner.kosakore.bukkit.event;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.bukkit.entity.BukkitPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class BukkitEventHandler implements Listener {

    private IKore kore;

    public BukkitEventHandler(IKore kore) {
        this.kore = kore;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerLogin(PlayerLoginEvent event) {
        kore.getEventBus().fire(new com.kosakorner.kosakore.api.event.player.PlayerLoginEvent(new BukkitPlayer(event.getPlayer()), event.getHostname(), event.getAddress(), event.getKickMessage()));
    }

}
