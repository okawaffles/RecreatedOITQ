package moe.waffle.oitq.core;

import moe.waffle.oitq.components.MapLoaderComponent;
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
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+============= ONE IN THE QUIVER =============+");
            player.sendMessage(ChatColor.BLUE + "- First to " + ChatColor.BOLD + "21" + ChatColor.RESET +""+ ChatColor.DARK_GRAY + " kills wins.");
            player.sendMessage(ChatColor.BLUE + "- Bow shots kill instantly.");
            player.sendMessage(ChatColor.BLUE + "- Melee hits do not kill instantly.");
            player.sendMessage(ChatColor.BLUE + "- All kills grant one arrow to the killer.");
            player.sendMessage(ChatColor.BLUE + "You're playing map \"" + ChatColor.DARK_AQUA + MapLoaderComponent.loadedMap.mapName + ChatColor.BLUE + "\" by " + ChatColor.DARK_AQUA + MapLoaderComponent.loadedMap.mapAuthor);
            player.sendMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "+===========================================+");
        });
    }
}
