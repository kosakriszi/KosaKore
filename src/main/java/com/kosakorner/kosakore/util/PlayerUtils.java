package com.kosakorner.kosakore.util;

import com.evilmidget38.UUIDFetcher;
import com.kosakorner.kosakore.Kore;
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
    File mapFile = new File(Kore.instance().getDataFolder(), "idMap");

    public PlayerUtils() {
        idMap = readUUIDMap();
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
        String contents = "";
        for (Map.Entry entry : idMap.entrySet()) {
            contents += entry.getKey() + "|" + entry.getValue().toString() + "\n";
        }

        try {
            FileWriter writer = new FileWriter(mapFile);
            writer.write(contents);
            writer.flush();
            writer.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
