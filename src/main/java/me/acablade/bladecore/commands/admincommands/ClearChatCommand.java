package me.acablade.bladecore.commands.admincommands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCommand implements CommandExecutor, ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!sender.hasPermission(getPermission())){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
            return false;
        }
        if(args.length > 0){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.WRONG_SYNTAX,
                    "SYNTAX",getSyntax());
            return false;
        }
        for (int i = 0; i < 150; i++) {
            for(Player player: Bukkit.getOnlinePlayers()){
                player.sendMessage(" ");
            }
        }
        for(Player player: Bukkit.getOnlinePlayers()){
            MessageSender.sendMessageAtPath(player,ConfigMessage.CHAT_CLEARED,
                    "PLAYER", sender.getName());
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Clears chat";
    }

    @Override
    public String getSyntax() {
        return "/clearchat";
    }

    @Override
    public String getPermission() {
        return "bladecore.clearchat";
    }
}
