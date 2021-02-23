package me.acablade.bladecore;

import me.acablade.bladecore.objects.CustomConfig;
import org.bukkit.plugin.java.JavaPlugin;

public final class BladeCore extends JavaPlugin {

    private static BladeCore instance;

    public static BladeCore getInstance(){
        return instance;
    }


    @Override
    public void onEnable() {
        // Plugin startup logic
        if(instance == null) instance = this;

        //Create messages.yml
        CustomConfig.createCustomConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
