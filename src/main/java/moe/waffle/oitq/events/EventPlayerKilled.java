package moe.waffle.oitq.events;

import moe.waffle.oitq.*;
import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.components.MapLoaderComponent;
import moe.waffle.oitq.core.BroadcastHelper;
import moe.waffle.oitq.core.GameEndEvent;
import moe.waffle.oitq.core.GameEndReason;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerKilled implements Listener {
    public EventPlayerKilled(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnEntityDeath(PlayerDeathEvent ev) {
        if (!GameVarStorage.GameActive) return;

        Player affectedPlayer = ev.getEntity();
        Player affector = affectedPlayer.getKiller();

        // respawn the player at a map's spawnpoint
        affectedPlayer.setBedSpawnLocation(MapLoaderComponent.loadedMap.getRandomSpawnLocation(), true);
        affectedPlayer.spigot().respawn();

        // this should prevent double points
        if (affectedPlayer.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) return;

        ev.setDeathMessage(""); // hide death message.

        Integer KillerCurrentScore = GameVarStorage.kills.get(affector);
        KillerCurrentScore++;

        GameVarStorage.kills.put(affector, KillerCurrentScore);
        GUIComponent.UpdateGUI();
        // check if the player has reached winning score (21):
        if (KillerCurrentScore == 21) {
            GameEndEvent.EndGame(GameEndReason.PLAYER_REACHED_WIN_SCORE);
        }

        // all kills give an arrow
        affector.getInventory().addItem(new ItemStack(Material.ARROW, 1));

        // handle this last cuz its not super important
        BroadcastHelper.BroadcastKillSword(affector, affectedPlayer);
    }
}
