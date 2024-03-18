package moe.waffle.oitq.core;

import org.bukkit.ChatColor;

public class GameEndEvent {
    public static void EndGame(GameEndReason reason) {
        if (reason == GameEndReason.PLAYER_REACHED_WIN_SCORE) {
            GameVarStorage.kills.forEach((player, kills) -> {
                BroadcastHelper.BroadcastMessage(
                        ChatColor.DARK_AQUA + player.getName() +
                                ChatColor.DARK_GRAY + " - " +
                                ChatColor.DARK_AQUA + kills
                );
            });

            // do it twice cuz i'm weird lol
            GameVarStorage.kills.forEach((player, kills) -> {
                if (kills == 21) {
                    // we need a blank string or else java gets MAD
                    BroadcastHelper.BroadcastMessage(
                            ChatColor.DARK_AQUA + "" + ChatColor.BOLD + player.getName() +
                                    ChatColor.RESET + ChatColor.DARK_GRAY + " has won the game!"
                    );
                }
            });
        }

        if (reason == GameEndReason.PLAYER_MANUALLY_ENDED_GAME) {
            BroadcastHelper.BroadcastMessage(ChatColor.RED +""+ ChatColor.ITALIC + "A player has manually ended the game.");
        }

        // clear variables
        GameVarStorage.GameActive = false;
        GameVarStorage.kills.clear();
    }
}
