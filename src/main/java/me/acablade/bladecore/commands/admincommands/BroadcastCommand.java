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

public class BroadcastCommand implements CommandExecutor, ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!sender.hasPermission(getPermission())){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
            return false;
        }
        if(args.length < 1){
            MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                    "SYNTAX", getSyntax());
        }else{
            StringBuilder messageBuilder = new StringBuilder();
            for (String arg : args) {
                messageBuilder.append(arg).append(" ");
            }
            String message = messageBuilder.toString().substring(0,messageBuilder.toString().length()-1);
            for(Player player: Bukkit.getOnlinePlayers()){
                MessageSender.sendMessageAtPath(player, ConfigMessage.BROADCAST_FORMAT,
                        "MESSAGE",message);
            }
        }


        return false;
    }

    @Override
    public String getDescription() {
        return "Broadcasts a command";
    }

    @Override
    public String getSyntax() {
        return "/broadcast <message>";
    }

    @Override
    public String getPermission() {
        return "bladecore.broadcast";
    }
}
