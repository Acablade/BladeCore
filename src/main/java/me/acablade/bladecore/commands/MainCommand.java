package me.acablade.bladecore.commands;

import me.acablade.bladecore.BladeCore;
import me.acablade.bladecore.commands.admincommands.*;
import me.acablade.bladecore.objects.CustomConfig;
import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import sun.applet.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainCommand implements CommandExecutor,ICommand {
    private static List<ICommand> commandList = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            sender.sendMessage("§m§8-------- §r§bBladeCore §m§8--------");
            for(ICommand cmd: commandList){
                if(sender.hasPermission(cmd.getPermission()) || cmd.getPermission().length() <= 3){
                    sender.sendMessage("§b"+cmd.getSyntax()+ " §7: "  +cmd.getDescription());
                }
            }
            sender.sendMessage("§m§8-----------------------------------");
        }else if(args.length == 1){
            if(args[0].equalsIgnoreCase("reload")){
                if(!sender.hasPermission("bladecore.reload")){
                    MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
                    return false;
                }
                CustomConfig.createCustomConfig();
            }
        }else{
            MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                    "PREFIX",ConfigMessage.PREFIX.getMessage(),
                    "SYNTAX",getSyntax());
        }

        return false;
    }

    public static void initCommandMap(){
        commandList.add(new BroadcastCommand());
        commandList.add(new ClearChatCommand());
        commandList.add(new FlyCommand());
        commandList.add(new InventoryCommand());
        commandList.add(new TPOCommand());
        commandList.add(new MessageCommand());
        commandList.add(new ReplyCommand());
        commandList.add(new TPOCommand());
        commandList.add(new MainCommand());
    }

    @Override
    public String getDescription() {
        return "Sends this message";
    }

    @Override
    public String getSyntax() {
        return "/bladecore [reload]";
    }

    @Override
    public String getPermission() {
        return "";
    }
}
