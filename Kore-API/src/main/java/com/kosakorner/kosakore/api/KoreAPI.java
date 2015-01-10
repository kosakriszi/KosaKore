package com.kosakorner.kosakore.api;

public class KoreAPI {

    public IKore getKore() {
        Class kore;
        try {
            kore = Class.forName("com.kosakorner.bukkit.Kore");
            if (kore != null) {
                return (IKore) kore.cast(IKore.class);
            }
            else {
                // put other platforms here
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
