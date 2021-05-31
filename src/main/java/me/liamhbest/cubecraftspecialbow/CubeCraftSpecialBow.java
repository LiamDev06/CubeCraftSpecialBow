package me.liamhbest.cubecraftspecialbow;

import org.bukkit.plugin.java.JavaPlugin;

public final class CubeCraftSpecialBow extends JavaPlugin {

    private static CubeCraftSpecialBow instance;
    public static CubeCraftSpecialBow getInstance(){
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

    }

    @Override
    public void onDisable() {
        instance = null;
    }
}











