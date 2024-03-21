package moe.waffle.oitq.events;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventPlayerDamage implements Listener {
    public EventPlayerDamage(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerDamage(EntityDamageEvent ev) {
        Entity ent = ev.getEntity();
        if (!(ent instanceof Player)) {
            return;
        }

        // disable fall damage for players
        if (ev.getCause() == EntityDamageEvent.DamageCause.FALL) {
            ev.setCancelled(true);
        }

        if (ev.getCause() == EntityDamageEvent.DamageCause.CONTACT && GameVarStorage.FreezePlayers) {
            ev.setCancelled(true);
        }
    }
}
