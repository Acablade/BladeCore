package me.acablade.bladecore.commands;

import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message implements CommandExecutor {

    //msg <player> <message>

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 2){
            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                messageBuilder.append(args[i]).append(" ");
            }
            String message = messageBuilder.substring(0,messageBuilder.length()-1);
            String pmMessage = MessageSender.placeholders(ConfigMessage.PM_FORMAT.getMessage(),new String[]{"PREFIX", ConfigMessage.PREFIX.getMessage(), "TO",args[0], "FROM", sender.getName(), "MESSAGE",message});
            Player to = Bukkit.getPlayer(args[0]);
            if(to!=null){
                to.sendMessage(pmMessage);
                sender.sendMessage(pmMessage);
            }
        }else{
            MessageSender.sendMessageAtPath(sender, ConfigMessage.WRONG_SYNTAX,new String[0]);
        }
        return false;
    }
}
