package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // load commands
        getCommand("start").setExecutor(new CommandStart());

        // load events
        new EventPlayerKilled(this);

        Bukkit.getLogger().info("okawaffles OITQ has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("okawaffles OITQ disabled. Thanks for using trying it out!");
    }
}
