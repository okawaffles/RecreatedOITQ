package moe.waffle.oitq.core;

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

    public static void BroadcastGameStart() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+============= ONE IN THE QUIVER =============+");
            player.sendMessage(ChatColor.DARK_GRAY + "- First to " + ChatColor.BOLD + "21" + ChatColor.RESET +""+ ChatColor.DARK_GRAY + " kills wins.");
            player.sendMessage(ChatColor.DARK_GRAY + "- Bow shots kill instantly.");
            player.sendMessage(ChatColor.DARK_GRAY + "- Melee hits do not kill instantly.");
            player.sendMessage(ChatColor.DARK_GRAY + "- All kills grant one arrow to the killer.");
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+================= GOOD LUCK =================+");
        });
    }
}
