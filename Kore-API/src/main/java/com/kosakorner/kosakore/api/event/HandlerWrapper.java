package com.kosakorner.kosakore.api.event;

import java.lang.reflect.Method;

public class HandlerWrapper {

    private Object listenerObject;
    private Method method;

    public HandlerWrapper(Object listenerObject, Method method) {
        this.listenerObject = listenerObject;
        this.method = method;
    }

    public Object getListenerObject() {
        return listenerObject;
    }

    public Method getMethod() {
        return method;
    }

}
