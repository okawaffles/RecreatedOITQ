package moe.waffle.oitq;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // load commands
        getCommand("start").setExecutor(new CommandStart());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
