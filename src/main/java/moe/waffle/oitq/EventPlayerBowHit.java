package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class EventPlayerBowHit implements Listener {
    public EventPlayerBowHit(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerHit(EntityDamageByEntityEvent ev) {
        if (!GameVarStorage.GameActive) return;


        Entity damaged = ev.getEntity();
        Entity damager = ev.getDamager();

        if (!(damager instanceof Arrow)) return;

        // make sure it is arrow
        if (damaged instanceof Player && ev.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            ((Player) damaged).setHealth(0);

            Player killer = (Player) ((Arrow) damager).getShooter();
            killer.getInventory().addItem(new ItemStack(Material.ARROW, 1));

            // handle what WOULD be handled in the death event:
            Integer KillerCurrentScore = GameVarStorage.kills.get(killer);
            KillerCurrentScore++;
            GameVarStorage.kills.put(killer, KillerCurrentScore);
            GUIComponent.UpdateGUI();

            BroadcastHelper.BroadcastKillBow(killer, (Player) damaged);
        } else return;
    }
}
