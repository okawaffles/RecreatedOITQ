package moe.waffle.oitq.events;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class EventPlayerMove implements Listener {
    public EventPlayerMove(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void OnPlayerMove(PlayerMoveEvent ev) {
        if (GameVarStorage.FreezePlayers) {
            ev.getPlayer().teleport(ev.getFrom());
        }
    }
}
