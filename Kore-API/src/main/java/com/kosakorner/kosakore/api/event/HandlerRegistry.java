package com.kosakorner.kosakore.api.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HandlerRegistry {

    private HashMap<Class, List<HandlerWrapper>> handlers;

    public HandlerRegistry() {
        handlers = new HashMap<Class, List<HandlerWrapper>>();
    }

    public void register(Object listenerObject) {
        for (Method method : listenerObject.getClass().getMethods()) {
            for (Annotation a : method.getAnnotations()) {
                if (a instanceof Handler) {
                    if (Event.class.isAssignableFrom(method.getParameterTypes()[0])) {
                        registerHandler(listenerObject, (Class<? extends Event>) method.getParameterTypes()[0], method);
                    }
                }
            }
        }
    }

    private void registerHandler(Object listenerObject, Class<? extends Event> eventClass, Method handler) {
        List<HandlerWrapper> handlerList = getHandlers(eventClass);
        if (handlerList == null) {
            List<HandlerWrapper> newHandler = new ArrayList<HandlerWrapper>();
            newHandler.add(new HandlerWrapper(listenerObject, handler));
            handlers.put(eventClass, newHandler);
        }
        else {
            handlerList.add(new HandlerWrapper(listenerObject, handler));
            handlers.put(eventClass, handlerList);
        }
    }

    public List<HandlerWrapper> getHandlers(Class<? extends Event> eventClass) {
        return handlers.get(eventClass);
    }

}
