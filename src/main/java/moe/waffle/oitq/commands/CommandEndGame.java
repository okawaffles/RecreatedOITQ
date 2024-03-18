package moe.waffle.oitq.commands;

import moe.waffle.oitq.core.GameEndEvent;
import moe.waffle.oitq.core.GameEndReason;
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
