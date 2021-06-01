package me.liamhbest.cubecraftspecialbow.commands;

import me.liamhbest.cubecraftspecialbow.SpecialBow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BowCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(SpecialBow.getInstance().translate("&cThis command can only be executed by a player!"));
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 0){
            player.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("Bow Inventory Open Message")));
            player.openInventory(SpecialBow.getInstance().getSpecialBowGUIManager().getSpecialBowGUI());
            return true;
        }

        try {
            Player targetPlayer = Bukkit.getPlayer(args[0]);

            targetPlayer.getInventory().addItem(SpecialBow.getInstance().getSpecialBowUtility().getSpecialBow());
            targetPlayer.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("You Received Bow From Player Message").replace("%player_received_from%", player.getName())));
            player.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("You Gave The Bow Message").replace("%player_gave_to%", targetPlayer.getName())));

        } catch (NullPointerException exception){
            player.sendMessage(ChatColor.RED + "This player is not online!");
        }

        return false;
    }
}















