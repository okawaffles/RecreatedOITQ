package moe.waffle.oitq.components;

import moe.waffle.oitq.Main;
import moe.waffle.oitq.core.GameVarStorage;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

import java.awt.*;

public class TimerComponent {
    private static final float TimerGoal = 8.0f;
    private static float TimerCurrent = 0.0f;
    private static BukkitTask task;

    private static Plugin plg;

    public static void SetupTimer(Main plugin) {
        plg = plugin;
    }

    // this function constructs the string for the text that we see at the bottom during the timer

    private static String ConstructTimerGUI() {
        final String GREEN_BLOCK = ChatColor.RESET +""+ ChatColor.GREEN +""+ ChatColor.BOLD + "█";
        final String RED_BLOCK = ChatColor.RESET +""+ ChatColor.RED +""+ ChatColor.BOLD + "█";
        // want 16 segments divided by .5s each

        int segmentsGreen = Math.round(16 / TimerCurrent * 10);
        int segmentsRed = 16 - segmentsGreen;

        StringBuilder progress = new StringBuilder();

        for (int i = 0; i != segmentsGreen; i++) {
            progress.append(GREEN_BLOCK);
        }
        for (int i = 0; i != segmentsRed; i++) {
            progress.append(RED_BLOCK);
        }

        progress.append(" ").append(TimerGoal - TimerCurrent).append(" seconds");
        return progress.toString();
    }

    private static int count = 8;
    public static void StartTimer() {
        count = 8;
        TimerCurrent = 0.0f;
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        scheduler.scheduleSyncDelayedTask(plg, new Runnable(){
            @Override
            public void run() {
                if(count > 0) {
                    // this is not working
                    try {
                        TextComponent t = new TextComponent(ConstructTimerGUI());
                        Bukkit.getOnlinePlayers().forEach(player -> player.spigot().sendMessage(ChatMessageType.ACTION_BAR, t));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    TimerCurrent += 1.0f;
                }
                count--;
            }
        }, 20);
    }
}
