package me.acablade.bladecore.objects;

import me.acablade.bladecore.BladeCore;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomConfig {

    private static FileConfiguration customConfig;

    public static FileConfiguration getCustomConfig() {
        return customConfig;
    }

    public static void createCustomConfig() {
        File customConfigFile = new File(BladeCore.getInstance().getDataFolder(), "messages.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            BladeCore.getInstance().saveResource("messages.yml", false);
        }

        customConfig= new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
