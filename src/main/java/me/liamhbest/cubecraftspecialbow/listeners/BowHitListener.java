package me.liamhbest.cubecraftspecialbow.listeners;

import me.liamhbest.cubecraftspecialbow.SpecialBow;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class BowHitListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if (event.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
            Arrow arrow = (Arrow) event.getDamager();

            if (arrow.getShooter() instanceof Player && event.getEntity() instanceof Player){
                Player shootingPlayer = (Player) arrow.getShooter();
                Player playerHit = (Player) event.getEntity();
                if (playerHit.hasPermission("tt.bow")) return;

                shootingPlayer.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("Shooting Player Hit Message").replace("%player_hit%", playerHit.getName())));
                playerHit.sendMessage(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("Player Hit Hit Message").replace("%shooting_player%", shootingPlayer.getName())));

                try {
                    ItemStack itemToKeep = SpecialBow.getInstance().getSpecialBowUtility().getRandomInventoryItem(playerHit.getInventory().getContents());
                    ItemStack itemToDrop = itemToKeep.clone();

                    itemToKeep.setAmount(itemToKeep.getAmount() - 1);
                    itemToDrop.setAmount(1);

                    Item dropItem = playerHit.getWorld().dropItem(playerHit.getLocation(), itemToDrop);
                    dropItem.setPickupDelay(40);

                    final String itemName = itemToDrop.getType().name();
                    if (itemName.endsWith("_HELMET") && playerHit.getInventory().getHelmet() != null && playerHit.getInventory().getHelmet().getType() != Material.AIR) {
                        playerHit.getInventory().setHelmet(null);
                    } else

                    if (itemName.endsWith("_CHESTPLATE") && playerHit.getInventory().getChestplate() != null && playerHit.getInventory().getChestplate().getType() != Material.AIR) {
                        playerHit.getInventory().setChestplate(null);
                    } else

                    if (itemName.endsWith("_LEGGINGS") && playerHit.getInventory().getLeggings() != null && playerHit.getInventory().getLeggings().getType() != Material.AIR) {
                        playerHit.getInventory().setLeggings(null);
                    } else

                    if (itemName.endsWith("_BOOTS") && playerHit.getInventory().getBoots() != null && playerHit.getInventory().getBoots().getType() != Material.AIR) {
                        playerHit.getInventory().setBoots(null);
                    }

                } catch (NullPointerException | IllegalArgumentException ignored){ }
            }

        }

    }

}












