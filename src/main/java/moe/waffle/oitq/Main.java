package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // load commands
        getCommand("start").setExecutor(new CommandStart());
        getCommand("endgame").setExecutor(new CommandEndGame());

        // load events
        new EventPlayerKilled(this);
        new EventPlayerBowHit(this);
        new EventPlayerRespawn(this);

        GUIComponent.PrepareGUI(getServer());

        Bukkit.getLogger().info("okawaffles OITQ has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("okawaffles OITQ disabled. Thanks for using trying it out!");
    }
}
