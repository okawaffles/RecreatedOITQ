package moe.waffle.oitq.components;

import moe.waffle.oitq.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Bukkit.getServer;

public class MapLoaderComponent {
    private static FileConfiguration pluginConfig;
    public static MapConfiguration loadedMap = new MapConfiguration();

    public static void LoadConfig(Main plugin) {
        pluginConfig = plugin.getConfig();
    }
    public static boolean LoadMap(String mapId) {
        try {
            pluginConfig.get("maps." + mapId);
            String mapName = pluginConfig.getString("maps." + mapId + ".name");
            String mapAuthor = pluginConfig.getString("maps." + mapId + ".author");
            List<Map<?, ?>> spawns = pluginConfig.getMapList("maps." + mapId + ".spawns");

            // convert spawns to location objects
            List<Location> spawnLocations = new ArrayList<>();
            for (Map<?, ?> spawnMap : spawns) {
                int x = (int) spawnMap.get("x");
                int y = (int) spawnMap.get("y");
                int z = (int) spawnMap.get("z");

                Location loc = new Location(getServer().getWorlds().get(0), x, y, z);
                spawnLocations.add(loc);
            }

            loadedMap.mapName = mapName;
            loadedMap.mapAuthor = mapAuthor;
            loadedMap.spawnLocations = spawnLocations;

        } catch (Exception ex) {
            Bukkit.getLogger().info(ex.getMessage());
            return false;
        }
        return true;
    }
}
