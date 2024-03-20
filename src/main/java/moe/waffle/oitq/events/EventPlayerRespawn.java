package moe.waffle.oitq.events;

import moe.waffle.oitq.core.GameVarStorage;
import moe.waffle.oitq.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EventPlayerRespawn implements Listener {
    public EventPlayerRespawn(Main plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void OnPlayerRespawn(PlayerRespawnEvent ev) {
        if (!GameVarStorage.GameActive) return;

        Player respawnee = ev.getPlayer();

        //respawnee.teleport(MapLoaderComponent.loadedMap.getRandomSpawnLocation());

        respawnee.setHealth(20);

        ItemStack BOW = new ItemStack(Material.BOW, 1);
        ItemStack IRONSWORD = new ItemStack(Material.IRON_SWORD, 1);

        ItemMeta BowItemMeta = BOW.getItemMeta();
        BowItemMeta.setUnbreakable(true);
        BOW.setItemMeta(BowItemMeta);

        ItemMeta SwordMeta = IRONSWORD.getItemMeta();
        SwordMeta.setUnbreakable(true);
        IRONSWORD.setItemMeta(SwordMeta);

        respawnee.getInventory().clear();
        respawnee.getInventory().addItem(
                IRONSWORD,
                BOW,
                new ItemStack(Material.ARROW, 1)
        );
    }
}
