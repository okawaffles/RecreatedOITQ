package moe.waffle.oitq.events;

import moe.waffle.oitq.*;
import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.components.CfgComponent;
import moe.waffle.oitq.core.BroadcastHelper;
import moe.waffle.oitq.core.GameEndEvent;
import moe.waffle.oitq.core.GameEndReason;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
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
        affectedPlayer.setBedSpawnLocation(CfgComponent.loadedMap.getRandomSpawnLocation(), true);
        affectedPlayer.spigot().respawn();

        // this should prevent double points
        if (affectedPlayer.getLastDamageCause().getCause() == EntityDamageEvent.DamageCause.PROJECTILE) return;

        ev.setDeathMessage(""); // hide death message.

        Integer KillerCurrentScore = 0;

        try {
            KillerCurrentScore = GameVarStorage.kills.get(affector);
            KillerCurrentScore++;
        } catch (Exception e) {
            // this usually just means that the player killed w/ a bow. so we'll just ignore the error lol
            return;
        }

        GameVarStorage.kills.put(affector, KillerCurrentScore);
        GUIComponent.UpdateGUI();
        // check if the player has reached winning score:
        if (KillerCurrentScore == 20) {
            GameEndEvent.EndGame(GameEndReason.PLAYER_REACHED_WIN_SCORE);
        }

        // all kills give an arrow
        affector.getInventory().addItem(new ItemStack(Material.ARROW, 1));
        affector.playSound(affector, Sound.BLOCK_PISTON_EXTEND, 0.5f, 2.0f); // nice touch i think :3

        // handle this last cuz its not super important
        BroadcastHelper.BroadcastKillSword(affector, affectedPlayer);
    }
}
