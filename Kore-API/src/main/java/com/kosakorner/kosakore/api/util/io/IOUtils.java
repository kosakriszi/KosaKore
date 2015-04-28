package com.kosakorner.kosakore.api.util.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class IOUtils {

    public static List readArrayFromFile(File file) {
        List<Object> array = new ArrayList<Object>();
        if (!file.exists()) {
            return array;
        }
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String s;
            while ((s = buffer.readLine()) != null) {
                array.add(s);
            }
            reader.close();
            return array;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static void writeArrayToFile(List array, File file) {
        StringBuilder contents = new StringBuilder();
        for (Object object : array) {
            contents.append(object).append("\n");
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(contents.toString());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
