package me.acablade.bladecore;

import me.acablade.bladecore.commands.MainCommand;
import me.acablade.bladecore.commands.MessageCommand;
import me.acablade.bladecore.commands.ReplyCommand;
import me.acablade.bladecore.commands.admincommands.*;
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
        MainCommand.initCommandMap();
        registerCommands();
    }

    private void registerCommands() {

        getCommand("broadcast").setExecutor(new BroadcastCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("inventory").setExecutor(new InventoryCommand());
        getCommand("tpo").setExecutor(new TPOCommand());
        getCommand("message").setExecutor(new MessageCommand());
        getCommand("reply").setExecutor(new ReplyCommand());
        getCommand("bladecore").setExecutor(new MainCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
