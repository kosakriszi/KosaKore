package com.kosakorner.kosakore.api.event;

public interface Cancelable {

    public boolean isCanceled();

    public void setCancelled(boolean canceled);

}
