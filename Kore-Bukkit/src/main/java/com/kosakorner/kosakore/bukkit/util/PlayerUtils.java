package com.kosakorner.kosakore.bukkit.util;

import com.evilmidget38.UUIDFetcher;
import com.kosakorner.kosakore.bukkit.Kore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerUtils implements Listener {

    public Map<String, UUID> idMap;
    private File mapFile;

    public PlayerUtils() {
        mapFile = new File(((Kore) Kore.instance()).getDataFolder(), "idMap");
        idMap = readUUIDMap();
    }

    public String getNameFromUUID(UUID id) {
        return getKeyByValue1To1(idMap, id);
    }

    public UUID getUUIDFromName(String name) {
        UUID result = idMap.get(name);
        if (result == null) {
            UUIDFetcher fetcher = new UUIDFetcher(Arrays.asList(name));
            Map<String, UUID> response;
            try {
                response = fetcher.call();
            }
            catch (Exception e) {
                throw new RuntimeException("Can't fetch player UUID", e);
            }
            return response.get(name);
        }
        else {
            return result;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!idMap.containsValue(player.getUniqueId())) {
            idMap.put(player.getName(), player.getUniqueId());
            writeUUIDMap();
        }
    }

    public Map<String, UUID> readUUIDMap() {
        Map<String, UUID> map = new HashMap<String, UUID>();

        if (!mapFile.exists()) {
            return map;
        }
        try {
            FileReader reader = new FileReader(mapFile);
            BufferedReader buffer = new BufferedReader(reader);
            String s;
            while ((s = buffer.readLine()) != null) {
                String[] parts = s.split("\\|");
                map.put(parts[0], UUID.fromString(parts[1]));
            }
            reader.close();
            return map;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public void writeUUIDMap() {
        StringBuilder contents = new StringBuilder();
        for (Map.Entry entry : idMap.entrySet()) {
            contents.append(entry.getKey()).append("|").append(entry.getValue().toString()).append("\n");
        }

        try {
            FileWriter writer = new FileWriter(mapFile);
            writer.write(contents.toString());
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static <T, E> T getKeyByValue1To1(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

}
