package moe.waffle.oitq.core;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class GameVarStorage {
    public static boolean GameActive = false;
    public static HashMap<Player, Integer> kills = new HashMap<Player, Integer>();
    public static boolean FreezePlayers = false;
}
