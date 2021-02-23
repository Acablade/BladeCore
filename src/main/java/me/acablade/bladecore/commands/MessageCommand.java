package me.acablade.bladecore.commands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import me.acablade.bladecore.utils.PrivateMessaging;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MessageCommand implements CommandExecutor, ICommand {

    //msg <player> <message>


    @Override
    public String getDescription() {
        return "Private messages player";
    }

    public String getSyntax(){
        return "/msg <player> <message>";
    }

    @Override
    public String getPermission() {
        return "";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length >= 2){
            StringBuilder messageBuilder = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                messageBuilder.append(args[i]).append(" ");
            }
            Player to = Bukkit.getPlayer(args[0]);
            if(to!=null){
                PrivateMessaging.sendMessage(to,sender, messageBuilder.substring(0,messageBuilder.toString().length()-1));
            }else{
                MessageSender.sendMessageAtPath(sender,ConfigMessage.PLAYER_NOT_ACTIVE.getPath(),"PLAYER",to.getName());
            }


        }else{
            MessageSender.sendMessageAtPath(sender, ConfigMessage.WRONG_SYNTAX,"SYNTAX", getSyntax());
        }
        return false;
    }
}
