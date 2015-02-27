package com.kosakorner.kosakore.api.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HandlerRegistry {

    private HashMap<Class, List<Method>> handlers;

    public HandlerRegistry() {
        handlers = new HashMap<Class, List<Method>>();
    }

    public void registerHandler(Class<? extends Event> eventClass, Method handler) {
        List<Method> handlerList = getHandlers(eventClass);
        if (handlerList == null) {
            List<Method> newHandler = new ArrayList<Method>();
            newHandler.add(handler);
            handlers.put(eventClass, newHandler);
        }
        else {
            handlerList.add(handler);
            handlers.put(eventClass, handlerList);
        }
    }

    public List<Method> getHandlers(Class<? extends Event> eventClass) {
        return handlers.get(eventClass);
    }

}
