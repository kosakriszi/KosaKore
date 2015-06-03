package com.kosakorner.kosakore.api.event;

public interface Cancelable {

    boolean isCanceled();

    void setCancelled(boolean canceled);

}
