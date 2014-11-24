package com.kosakorner.kosakore.io;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Set;

public class JSONConfig {

    private File        backingFile;
    private JSONSection parsed;

    public JSONConfig(File file) {
        backingFile = file;
        try {
            if (!backingFile.getParentFile().exists()) {
                backingFile.getParentFile().mkdirs();
            }

            if (backingFile.exists()) {
                JSONParser parser = new JSONParser();
                parsed = new JSONSection((JSONObject) parser.parse(new FileReader(backingFile)));
            }
            else {
                parsed = new JSONSection();
                save();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public File getBackingFile() {
        return backingFile;
    }

    public Set<Map.Entry> getEntrySet() {
        return parsed.entrySet();
    }

    public JSONArray getArray(String keyPath) {
        JSONArray toReturn = (JSONArray) parsed.get(keyPath);
        if (toReturn == null) {
            return new JSONArray();
        }
        else {
            return toReturn;
        }
    }

    public void setArray(String keyPath, JSONArray value) {
        parsed.set(keyPath, value);
    }

    public String getString(String keyPath) {
        String toReturn = (String) parsed.get(keyPath);
        if (toReturn == null) {
            return "";
        }
        else {
            return toReturn;
        }
    }

    public void setString(String keyPath, String value) {
        parsed.set(keyPath, value);
    }

    public boolean getBoolean(String keyPath) {
        Boolean toReturn = (Boolean) parsed.get(keyPath);
        if (toReturn == null) {
            return false;
        }
        else {
            return toReturn;
        }
    }

    public void setBoolean(String keyPath, boolean value) {
        parsed.set(keyPath, value);
    }

    public int getInt(String keyPath) {
        Long toReturn = (Long) parsed.get(keyPath);
        if (toReturn == null) {
            return -1;
        }
        else {
            return toReturn.intValue();
        }
    }

    public void setInt(String keyPath, int value) {
        parsed.set(keyPath, value);
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(backingFile);
            writer.write(parsed.toJSONString());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
