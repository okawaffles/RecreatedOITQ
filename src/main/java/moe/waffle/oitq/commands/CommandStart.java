package moe.waffle.oitq.commands;

import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.components.CfgComponent;
import moe.waffle.oitq.components.TimerComponent;
import moe.waffle.oitq.core.BroadcastHelper;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command, String label, String[] args) {
        if (args.length == 0) return false;

        sender.sendMessage(ChatColor.DARK_GRAY +""+ ChatColor.ITALIC + "Starting map data load of " + ChatColor.DARK_AQUA + args[0] + "...");
        boolean mapLoadSuccessful = CfgComponent.LoadMap(args[0]);
        if (!mapLoadSuccessful) {
            sender.sendMessage(ChatColor.DARK_RED + "[!] Map load was unsuccessful! Are you sure that map exists?");
            return true;
        }

        GameVarStorage.GameActive = true;
        TimerComponent.StartTimer();

        Bukkit.getOnlinePlayers().forEach(player -> {
            GameVarStorage.kills.put(player, 0);

            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack IRONSWORD = new ItemStack(Material.IRON_SWORD, 1);

            ItemMeta BowItemMeta = BOW.getItemMeta();
            BowItemMeta.setUnbreakable(true);
            BOW.setItemMeta(BowItemMeta);

            ItemMeta SwordMeta = IRONSWORD.getItemMeta();
            SwordMeta.setUnbreakable(true);
            IRONSWORD.setItemMeta(SwordMeta);

            player.getInventory().clear();
            player.getInventory().addItem(
                    IRONSWORD,
                    BOW
            );

            player.spigot().respawn();

            GUIComponent.UpdateGUI();
        });

        BroadcastHelper.BroadcastGameStart();

        return true;
    }
}
