package moe.waffle.oitq;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandEndGame implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameEndEvent.EndGame(GameEndReason.PLAYER_MANUALLY_ENDED_GAME);

        return true;
    }
}
