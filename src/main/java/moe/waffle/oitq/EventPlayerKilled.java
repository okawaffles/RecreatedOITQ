package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerKilled implements Listener {
    public EventPlayerKilled(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerDeath(PlayerDeathEvent ev) {
        if (!GameVarStorage.GameActive) return;

        Player affectedPlayer = ev.getEntity();
        Player affectingPlayer = affectedPlayer.getKiller();
        String killingWeapon = affectedPlayer.getItemInUse().getType().name();

        Integer KillerCurrentScore = GameVarStorage.kills.get(affectingPlayer);
        KillerCurrentScore++;

        GameVarStorage.kills.put(affectingPlayer, KillerCurrentScore);

        // all kills give an arrow
        affectingPlayer.getInventory().addItem(new ItemStack(Material.ARROW, 1));

        // handle this last cuz its not super important
        if (killingWeapon.equals("IRON_SWORD")) {
            BroadcastHelper.BroadcastKillSword(affectingPlayer, affectedPlayer);
        } else if (killingWeapon.equals("BOW")) {
            BroadcastHelper.BroadcastKillBow(affectingPlayer, affectedPlayer);
        } else {
            // if all else fails, use the sword kill
            BroadcastHelper.BroadcastKillSword(affectingPlayer, affectedPlayer);
        }

        // check if the player has reached winning score (21):
        if (KillerCurrentScore == 21) {
            GameEndEvent.EndGame(GameEndReason.PLAYER_REACHED_WIN_SCORE);
        }
    }
}
