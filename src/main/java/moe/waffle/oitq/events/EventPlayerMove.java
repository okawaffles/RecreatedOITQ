package moe.waffle.oitq.events;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.util.Vector;

public class EventPlayerMove implements Listener {
    public EventPlayerMove(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler(priority = EventPriority.HIGH)
    public void OnPlayerMove(PlayerMoveEvent ev) {
        // for double jumping
        // deprecated method but i assume if youre playing this game you trust people enough to not cheat -_-
        if (ev.getPlayer().isOnGround()) {
            ev.getPlayer().setFlying(false);
            ev.getPlayer().setAllowFlight(true);
        }
    }

    @EventHandler
    public void OnPlayerFlight(PlayerToggleFlightEvent ev) {
        // for double jumping
        Player p = ev.getPlayer();

        p.setFlying(false);
        p.setAllowFlight(false);

        Vector dir = p.getLocation().getDirection();

        p.setVelocity(dir.multiply(1).setY(0.5f));
        p.playSound(p, Sound.ENTITY_BLAZE_SHOOT, 0.5f, 1.0f);
    }
}
