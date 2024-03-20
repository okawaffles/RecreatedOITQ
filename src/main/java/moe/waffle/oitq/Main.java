package moe.waffle.oitq;

import moe.waffle.oitq.commands.CommandEndGame;
import moe.waffle.oitq.commands.CommandStart;
import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.components.CfgComponent;
import moe.waffle.oitq.components.TimerComponent;
import moe.waffle.oitq.events.EventPlayerBowHit;
import moe.waffle.oitq.events.EventPlayerDamage;
import moe.waffle.oitq.events.EventPlayerKilled;
import moe.waffle.oitq.events.EventPlayerRespawn;
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
        new EventPlayerDamage(this);

        GUIComponent.PrepareGUI(getServer());
        CfgComponent.LoadConfig(this);
        TimerComponent.SetupTimer(this);

        Bukkit.getLogger().info("okawaffles OITQ has been enabled.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("okawaffles OITQ disabled. Thanks for using trying it out!");
    }
}
