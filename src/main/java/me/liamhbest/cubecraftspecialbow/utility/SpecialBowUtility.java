package me.liamhbest.cubecraftspecialbow.utility;

import me.liamhbest.cubecraftspecialbow.SpecialBow;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpecialBowUtility {

    public ItemStack getSpecialBow(){
        ItemStack item = new ItemStack(Material.BOW);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(SpecialBow.getInstance().translate(SpecialBow.getInstance().getConfig().getString("bow.name")));

        ArrayList<String> lore = new ArrayList<>();
        for (String s : SpecialBow.getInstance().getConfig().getStringList("bow.lore")){
            lore.add(SpecialBow.getInstance().translate(s));
        }
        meta.setLore(lore);

        meta.addEnchant(Enchantment.DURABILITY, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        return item;
    }

    public ItemStack getRandomInventoryItem(ItemStack[] inventoryContent){
        List<ItemStack> items = new ArrayList<>();

        for (ItemStack item : inventoryContent){
            if (item != null && item.getType() != Material.AIR){
                items.add(item);
            }
        }

        try {
            return items.get(new Random().nextInt(items.size()));
        } catch (IllegalArgumentException exception){
            return null;
        }
    }

}















