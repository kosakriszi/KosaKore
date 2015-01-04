package com.kosakorner.kosakore.api.util.io;

import org.json.simple.JSONObject;

import java.util.Map;

public class JSONSection extends JSONObject {

    private JSONSection root;

    public JSONSection() {
        root = this;
    }

    public JSONSection(JSONObject jsonObject) {
        this();
        for (Object object : jsonObject.entrySet()) {
            Map.Entry entry = (Map.Entry) object;
            put(entry.getKey(), entry.getValue());
        }
    }

    protected JSONSection getRoot() {
        return root;
    }

    public JSONSection setRoot(JSONSection parent) {
        root = parent;
        return this;
    }

    public Object get(String keyPath) {
        if (keyPath.length() == 0) {
            return this;
        }
        else {
            JSONSection root = this.getRoot();
            if (root == null) {
                throw new IllegalStateException("Cannot access section without a root");
            }
            else {
                char separator = '.';
                int i1 = -1;
                Object section = this;

                do {
                    int i2;
                    if ((i1 = keyPath.indexOf(separator, i2 = i1 + 1)) == -1) {
                        String key = keyPath.substring(i2);
                        if (section == this) {
                            return super.get(key);
                        }

                        return new JSONSection((JSONObject) section).setRoot(root).get(key);
                    }

                    section = new JSONSection((JSONObject) section).setRoot(root).get(keyPath.substring(i2, i1));
                }
                while (section != null);

                return null;
            }
        }
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
