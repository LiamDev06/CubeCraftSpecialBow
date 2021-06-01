package me.liamhbest.cubecraftspecialbow;

import me.liamhbest.cubecraftspecialbow.commands.BowCommand;
import me.liamhbest.cubecraftspecialbow.listeners.BowHitListener;
import me.liamhbest.cubecraftspecialbow.utility.SpecialBowGUI;
import me.liamhbest.cubecraftspecialbow.utility.SpecialBowUtility;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SpecialBow extends JavaPlugin {

    private static SpecialBow instance;
    public static SpecialBow getInstance(){ return instance; }

    private SpecialBowGUI specialBowGUIManager;
    public SpecialBowGUI getSpecialBowGUIManager() { return specialBowGUIManager; }

    private SpecialBowUtility specialBowUtility;
    public SpecialBowUtility getSpecialBowUtility() { return specialBowUtility; }

    @Override
    public void onEnable() {
        instance = this;
        specialBowGUIManager = new SpecialBowGUI();
        specialBowUtility = new SpecialBowUtility();

        Bukkit.getLogger().info("[SPECIAL BOW] Loading special bow plugin...");

        getServer().getPluginManager().registerEvents(new BowHitListener(), this);
        getServer().getPluginManager().registerEvents(new SpecialBowGUI(), this);
        getCommand("specialbow").setExecutor(new BowCommand());

        File cFile = new File(getDataFolder(), "config.yml");
        if (!cFile.exists()){
            try {
                cFile.createNewFile();
            } catch (IOException exception){
                exception.printStackTrace();
            }

            getConfig().options().copyDefaults(true);
            saveConfig();
        }
    }

    @Override
    public void onDisable() {
        instance = null;
        Bukkit.getLogger().info("[SPECIAL BOW] Disabling special bow plugin...");
    }

    public String translate(String value){
        return ChatColor.translateAlternateColorCodes('&', value);
    }

}











