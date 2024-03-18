package moe.waffle.oitq.components;

import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.scoreboard.*;

public class GUIComponent {
    private static Scoreboard board;
    private static Objective objective;
    public static void PrepareGUI(Server srv) {
        board = srv.getScoreboardManager().getMainScoreboard();

        if (board.getObjective("oitq-sidebar") == null) {
            objective = board.registerNewObjective("oitq-sidebar", Criteria.DUMMY, ChatColor.DARK_AQUA +""+ChatColor.BOLD + "+ [ONE IN THE QUIVER] +");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        } else {
            objective = board.getObjective("oitq-sidebar");
            objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        }
    }
    public static void UpdateGUI() {
        GameVarStorage.kills.forEach((player, killCount) -> {
            Score plrScore = objective.getScore(player.getName());
            plrScore.setScore(killCount);
        });
    }
}
