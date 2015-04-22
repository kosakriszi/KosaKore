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

                        return ((JSONSection) section).get(key);
                    }

                    section = ((JSONSection) section).getSubSection(keyPath.substring(i2, i1));
                }
                while (section != null);

                return null;
            }
        }
    }

    public void set(String keyPath, Object value) {
        JSONSection root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot use section without a root");
        } else {
            char separator = '.';
            int i1 = -1;
            Object section = this;

            int i2;
            String key;
            while ((i1 = keyPath.indexOf(separator, i2 = i1 + 1)) != -1) {
                key = keyPath.substring(i2, i1);
                JSONSection result;
                JSONObject temp = (JSONObject) super.get(key);
                if (temp != null) {
                    result = new JSONSection(temp);
                } else {
                    result = null;
                }
                if (result == null) {
                    section = ((JSONSection) section).createSubSection(key);
                } else {
                    section = result;
                }
            }

            key = keyPath.substring(i2);
            if (section == this) {
                if (value == null) {
                    remove(key);
                } else {
                    put(key, value);
                }
            }
            else {
                ((JSONSection) section).set(key, value);
            }
        }
    }

    public JSONSection getSubSection(String keyPath) {
        Object val = this.get(keyPath);
        if (val != null) {
            return val instanceof JSONObject ? new JSONSection((JSONObject) val) : null;
        } else {
            val = this.get(keyPath);
            return val instanceof JSONObject ? this.createSubSection(keyPath) : null;
        }
    }

    public JSONSection createSubSection(String keyPath) {
        JSONSection root = this.getRoot();
        if (root == null) {
            throw new IllegalStateException("Cannot create section without a root");
        } else {
            char separator = '.';
            int i1 = -1;
            Object section = this;

            int i2;
            String key;
            while ((i1 = keyPath.indexOf(separator, i2 = i1 + 1)) != -1) {
                key = keyPath.substring(i2, i1);
                JSONSection result;
                JSONObject temp = (JSONObject) super.get(key);
                if (temp != null) {
                    result = new JSONSection(temp);
                } else {
                    result = null;
                }
                if (result == null) {
                    section = ((JSONSection) section).createSubSection(key);
                } else {
                    section = result;
                }
            }

            key = keyPath.substring(i2);
            if (section == this) {
                JSONSection result1 = new JSONSection();
                put(key, result1);
                return result1;
            }
            else {
                return ((JSONSection) section).createSubSection(key);
            }
        }
    }

}
