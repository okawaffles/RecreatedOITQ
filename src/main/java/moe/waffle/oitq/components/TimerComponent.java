package moe.waffle.oitq.components;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.awt.*;

public class TimerComponent {
    private static final float TimerGoal = 8.0f;
    private static float TimerCurrent = 0.01f;
    private static BukkitTask task;

    private static Plugin plg;

    public static void SetupTimer(Main plugin) {
        plg = plugin;
    }

    // helper function for rounding
    private static double round (double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    // this function constructs the string for the text that we see at the bottom during the timer
    private static String ConstructTimerGUI() {
        final String GREEN_BLOCK = ChatColor.GREEN + "▌";
        final String RED_BLOCK = ChatColor.RED + "▌";

        // want 16 segments divided by .5s each
        int segmentsGreen = Math.round(16 * (TimerCurrent / TimerGoal));
        int segmentsRed = 16 - segmentsGreen;

        StringBuilder progress = new StringBuilder();
        progress.append("Game Start ");

        for (int i = 0; i < segmentsGreen; i++) {
            progress.append(GREEN_BLOCK);
        }
        for (int i = 0; i < segmentsRed; i++) {
            progress.append(RED_BLOCK);
        }

        // remove 0.01 from the timer here so we don't see the stray one at the end affecting the timer.
        progress.append(" ")
                .append(ChatColor.RESET)
                .append(round(TimerGoal - TimerCurrent - 0.01f, 1))
                .append(" seconds");

        return progress.toString();
    }

    private static int count = 0;
    private static int id = 0;
    public static void StartTimer() {
        GameVarStorage.FreezePlayers = true;
        count = 80;
        TimerCurrent = 0.01f; // it must be 0.01 or else divide by zero.
        id = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.Instance, new Runnable() {
            @Override
            public void run() {
                TextComponent t = new TextComponent(ConstructTimerGUI());
                Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, t));

                if (count != 0) {
                    TimerCurrent += 0.1f;
                    count--;
                } else {
                    // stop the timer
                    Bukkit.getServer().getScheduler().cancelTask(id);

                    // give arrow AFTER game starts so that players can't shoot during timer
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
                        player.playSound(player, Sound.BLOCK_PISTON_EXTEND, 0.5f, 2.0f); // nice touch i think :3
                    });
                    // unfreeze!
                    GameVarStorage.FreezePlayers = false;
                }
            }
        }, 0, 2);
    }
}
