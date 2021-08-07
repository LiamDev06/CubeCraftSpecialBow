package me.liamhbest.cubecraftspecialbow.utility;

import me.liamhbest.cubecraftspecialbow.SpecialBow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SpecialBowGUI implements Listener {

    public Inventory getSpecialBowGUI(){
        Inventory specialBowGUI = Bukkit.createInventory(null, 54, "CubeCraft Special Bow");

        ItemStack close = new ItemStack(Material.BOOK);
        ItemMeta meta = close.getItemMeta();
        meta.setDisplayName(ChatColor.YELLOW + "Close menu");
        close.setItemMeta(meta);

        specialBowGUI.setItem(49, close);
        specialBowGUI.setItem(22, SpecialBow.getInstance().getSpecialBowUtility().getSpecialBow());
        return specialBowGUI;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){

        if (event.getWhoClicked() instanceof Player){
            if (event.getView().getTitle().equals("CubeCraft Special Bow")) {
                if (event.getCurrentItem() == null) return;
                if (!event.getCurrentItem().hasItemMeta()) return;
                Player player = (Player) event.getWhoClicked();
                event.setCancelled(true);

                if (event.getSlot() == 22){
                    // Click to get bow

                    if (player.getInventory().firstEmpty() != -1){
                        player.closeInventory();
                        player.updateInventory();

                        player.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("Bow Given Message")));
                        player.getInventory().addItem(SpecialBow.getInstance().getSpecialBowUtility().getSpecialBow());
                    } else {
                        player.sendMessage(SpecialBow.getInstance().translate("&cYou cannot receive the bow as your inventory is full!"));
                    }

                } else if (event.getSlot() == 49){
                    // Close inventory
                    player.closeInventory();
                    player.updateInventory();
                }

            }
        }

    }

}












