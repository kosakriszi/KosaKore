package com.kosakorner.kosakore.api.event;

import java.lang.reflect.Method;
import java.util.List;

public interface IHandlerRegistry {

    public void registerHandler(Class<? extends Event> eventClass, Method handler);

    public List<Method> getHandlers(Class<? extends Event> eventClass);

}
