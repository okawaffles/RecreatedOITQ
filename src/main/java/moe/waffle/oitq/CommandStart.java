package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command, String label, String[] args) {
        GameVarStorage.GameActive = true;
        Bukkit.getOnlinePlayers().forEach(player -> {
            GameVarStorage.kills.put(player, 0);
        });

        return true;
    }
}
