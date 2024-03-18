package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BroadcastHelper {
    public static void BroadcastKillBow(Player killer, Player killed) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColor.DARK_AQUA + killer.getName() +
                    ChatColor.DARK_GRAY + " shot " +
                    ChatColor.DARK_AQUA + killed.getName()
                );
        });
    }

    public static void BroadcastKillSword(Player killer, Player killed) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColor.DARK_AQUA + killer.getName() +
                    ChatColor.DARK_GRAY + " melee'd " +
                    ChatColor.DARK_AQUA + killed.getName()
            );
        });
    }

    public static void BroadcastMessage(String message) {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(message);
        });
    }
}
