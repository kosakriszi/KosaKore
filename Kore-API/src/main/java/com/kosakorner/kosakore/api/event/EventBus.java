package com.kosakorner.kosakore.api.event;

import com.kosakorner.kosakore.api.IKore;

import java.util.List;

public class EventBus {

    private IKore kore;

    public EventBus(IKore kore) {
        this.kore = kore;
    }

    public <E extends Event> E fire(E event) {
        List<HandlerWrapper> handlers = kore.getHandlerRegistry().getHandlers(event.getClass());
        if (handlers != null) {
            for (HandlerWrapper handler : handlers) {
                try {
                    if (event instanceof Cancelable) {
                        if (!((Cancelable) event).isCanceled()) {
                            handler.getMethod().invoke(handler.getListenerObject(), event);
                        }
                        else {
                            return event;
                        }
                    }
                    else {
                        handler.getMethod().invoke(handler.getListenerObject(), event);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return event;
    }

}
