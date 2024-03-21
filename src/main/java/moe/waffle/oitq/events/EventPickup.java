package moe.waffle.oitq.events;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupArrowEvent;

public class EventPickup implements Listener {
    public EventPickup(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void OnPickup(PlayerPickupArrowEvent ev) {
        if (GameVarStorage.GameActive) {
            ev.setCancelled(true);
        }
    }
}
