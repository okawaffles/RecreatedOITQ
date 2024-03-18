package moe.waffle.oitq.commands;

import moe.waffle.oitq.components.GUIComponent;
import moe.waffle.oitq.core.GameVarStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandStart implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender,Command command, String label, String[] args) {
        GameVarStorage.GameActive = true;
        Bukkit.getOnlinePlayers().forEach(player -> {
            GameVarStorage.kills.put(player, 0);

            ItemStack BOW = new ItemStack(Material.BOW, 1);
            ItemStack IRONSWORD = new ItemStack(Material.IRON_SWORD, 1);

            ItemMeta BowItemMeta = BOW.getItemMeta();
            BowItemMeta.setUnbreakable(true);
            BOW.setItemMeta(BowItemMeta);

            ItemMeta SwordMeta = IRONSWORD.getItemMeta();
            SwordMeta.setUnbreakable(true);
            IRONSWORD.setItemMeta(SwordMeta);

            player.getInventory().clear();
            player.getInventory().addItem(
                    IRONSWORD,
                    BOW,
                    new ItemStack(Material.ARROW, 1)
            );

            GUIComponent.UpdateGUI();
        });

        return true;
    }
}
