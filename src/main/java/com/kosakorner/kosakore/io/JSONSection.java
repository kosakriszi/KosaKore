package com.kosakorner.kosakore.io;

import org.json.simple.JSONObject;

import java.util.Map;

public class JSONSection extends JSONObject {

    public JSONSection() {

    }

    public JSONSection(JSONObject jsonObject) {
        for (Object object : jsonObject.entrySet()) {
            Map.Entry entry = (Map.Entry) object;
            put(entry.getKey(), entry.getValue());
        }
    }

    public Object get(String keyPath) {
        char separator = '.';
        int i1 = -1;
        JSONSection current = this;

        do {
            int i2;
            if ((i1 = keyPath.indexOf(separator, i2 = i1 + 1)) == -1) {
                String key = keyPath.substring(i2);
                if (current == this) {
                    return super.get(key);
                }

                return ((JSONObject) current).get(key);
            }

            JSONObject temp = (JSONObject) super.get(keyPath.substring(i2, i1));
            if (temp != null) {
                current = new JSONSection(temp);
            }
            else {
                current = null;
            }
        }
        while (current != null);

        return null;
    }

    public void set(String keyPath, Object value) {
        char separator = '.';
        int i1 = -1;
        JSONSection current = this;

        int i2;
        String key;
        while ((i1 = keyPath.indexOf(separator, i2 = i1 + 1)) != -1) {
            key = keyPath.substring(i2, i1);

            JSONSection subSection;
            JSONObject temp = (JSONObject) super.get(key);
            if (temp != null) {
                subSection = new JSONSection(temp);
            }
            else {
                subSection = null;
            }

            if (subSection == null) {
                current.put(key, new JSONObject());
            }
            else {
                current = subSection;
            }
        }

        key = keyPath.substring(i2);
        if (current == this) {
            if (value == null) {
                remove(key);
            }
            else {
                put(key, value);
            }
        }
        else {
            current.set(key, value);
        }
    }

}
