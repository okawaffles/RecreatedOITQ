package moe.waffle.oitq;

import moe.waffle.oitq.commands.CommandEndGame;
import moe.waffle.oitq.commands.CommandStart;
import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.components.CfgComponent;
import moe.waffle.oitq.components.TimerComponent;
import moe.waffle.oitq.events.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    public static Plugin Instance;
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("Recreated OneInTheQuiver");
        Bukkit.getLogger().info("by dill schmarfius industries (subsid. of CATGIRLS SPACE)");
        Bukkit.getLogger().info("Gamemode inspiration from Mineplex.");
        Bukkit.getLogger().info("Starting OITQ plugin...");

        // Plugin startup logic
        Instance = this;

        // load commands
        getCommand("start").setExecutor(new CommandStart());
        getCommand("endgame").setExecutor(new CommandEndGame());

        // load events
        new EventPlayerKilled(this);
        new EventPlayerBowHit(this);
        new EventPlayerRespawn(this);
        new EventPlayerDamage(this);
        new EventPickup(this);

        GUIComponent.PrepareGUI(getServer());
        CfgComponent.LoadConfig(this);
        TimerComponent.SetupTimer(this);

        Bukkit.getLogger().info("OneInTheQuiver has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("OneInTheQuiver disabled. Thanks for using trying it out!");
    }
}
