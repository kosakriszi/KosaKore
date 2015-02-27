package com.kosakorner.kosakore.bukkit.event;

import com.kosakorner.kosakore.api.IKore;
import com.kosakorner.kosakore.api.event.Event;
import com.kosakorner.kosakore.api.event.IEventBus;

import java.lang.reflect.Method;
import java.util.List;

public class BukkitEventBus implements IEventBus {

    private IKore kore;

    public BukkitEventBus(IKore kore) {
        this.kore = kore;
    }

    public void fire(Event event) {
        List<Method> handlers = kore.getHandlerRegistry().getHandlers(event.getClass());
        for (Method handler : handlers) {
            try {
                handler.invoke(null, event);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
