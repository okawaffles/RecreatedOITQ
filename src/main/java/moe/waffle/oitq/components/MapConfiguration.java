package moe.waffle.oitq.components;

import org.bukkit.Location;

import java.util.List;
import java.util.Random;

import static org.bukkit.Bukkit.getServer;

public class MapConfiguration {
    public String mapName = null;
    public String mapAuthor = null;
    public List<Location> spawnLocations = null;

    public Location getRandomSpawnLocation() {
        Random rand = new Random();
        int index = rand.nextInt(spawnLocations.size());

        return spawnLocations.get(index);
    }
}
