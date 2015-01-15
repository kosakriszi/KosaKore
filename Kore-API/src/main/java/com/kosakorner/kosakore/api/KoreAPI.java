package com.kosakorner.kosakore.api;

import java.lang.reflect.Method;

public class KoreAPI {

    public static IKore getKore() {
        Class kore;
        try {
            kore = Class.forName("com.kosakorner.kosakore.bukkit.Kore");
            if (kore != null) {
                Method getInstance = kore.getMethod("instance");
                return (IKore) getInstance.invoke(null);
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
