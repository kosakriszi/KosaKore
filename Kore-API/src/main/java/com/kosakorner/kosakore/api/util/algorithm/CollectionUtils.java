package com.kosakorner.kosakore.api.util.algorithm;

import java.util.Collections;
import java.util.Map;

public class CollectionUtils {

    public static <K, V> Map<V, K> reverseMap(Map<K, V> map) {
        Map<V, K> reversed = Collections.emptyMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            reversed.put(entry.getValue(), entry.getKey());
        }
        return reversed;
    }

}
