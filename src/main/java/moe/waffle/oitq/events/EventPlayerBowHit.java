package moe.waffle.oitq.events;

import moe.waffle.oitq.core.BroadcastHelper;
import moe.waffle.oitq.core.GameVarStorage;
import moe.waffle.oitq.Main;
import moe.waffle.oitq.components.GUIComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
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
            if (damaged.getName().equals(damager.getName())) return; // this not working

            ((Player) damaged).setHealth(0);

            Player killer = (Player) ((Arrow) damager).getShooter();
            killer.getInventory().addItem(new ItemStack(Material.ARROW, 1));
            killer.playSound(killer, Sound.BLOCK_PISTON_EXTEND, 0.5f, 2.0f); // nice touch i think :3

            // handle what WOULD be handled in the death event:
            Integer KillerCurrentScore = GameVarStorage.kills.get(killer);
            KillerCurrentScore++;
            GameVarStorage.kills.put(killer, KillerCurrentScore);
            GUIComponent.UpdateGUI();

            BroadcastHelper.BroadcastKillBow(killer, (Player) damaged);
        } else return;
    }
}
