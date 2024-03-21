package moe.waffle.oitq.core;

import moe.waffle.oitq.components.CfgComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
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
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f); // nice touch i think :3
            player.sendMessage(""+ChatColor.DARK_GREEN + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "=============================================");
            player.sendMessage(ChatColor.WHITE + "Bow and Arrow insta-kills.");
            player.sendMessage(ChatColor.WHITE + "You recieve 1 Arrow per kill.");
            player.sendMessage(ChatColor.WHITE + "Glass blocks are not breakable yet.");
            player.sendMessage(ChatColor.WHITE + "First to 20 kills wins.");
            player.sendMessage("");
            player.sendMessage(ChatColor.GREEN + "Map - " +
                    ChatColor.WHITE + ChatColor.BOLD + CfgComponent.loadedMap.mapName +
                    ChatColor.RESET + ChatColor.GRAY + " by " +
                    ChatColor.WHITE + ChatColor.BOLD + CfgComponent.loadedMap.mapAuthor
            );
            player.sendMessage(""+ChatColor.DARK_GREEN + ChatColor.BOLD + ChatColor.STRIKETHROUGH + "=============================================");
        });
    }
}
