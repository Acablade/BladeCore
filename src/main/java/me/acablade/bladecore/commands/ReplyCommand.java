package me.acablade.bladecore.commands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import me.acablade.bladecore.utils.PrivateMessaging;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReplyCommand implements CommandExecutor, ICommand {

    //r <message>

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.ONLY_PLAYER_USAGE);
            return false;
        }

        if(args.length == 0){
            MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                    "PREFIX", ConfigMessage.PREFIX.getMessage(),
                    "SYNTAX",getSyntax());
        }

        Player from = (Player) sender;


        if(!PrivateMessaging.messageHashMap.containsKey(from.getUniqueId())){
            MessageSender.sendMessageAtPath(from,ConfigMessage.NO_REPLIES.getPath());
            return false;
        }

        Player to = Bukkit.getPlayer(PrivateMessaging.messageHashMap.get(from.getUniqueId()));

        if(to==null) {
            MessageSender.sendMessageAtPath(sender,ConfigMessage.PLAYER_NOT_ACTIVE.getPath(),"PREFIX",ConfigMessage.PREFIX.getMessage(),"PLAYER",to.getName());
            return false;
        }
        StringBuilder messageBuilder = new StringBuilder();
        for (String arg : args) {
            messageBuilder.append(arg).append(" ");
        }
        PrivateMessaging.sendMessage(to,from, messageBuilder.substring(0,messageBuilder.toString().length()-1));



        return false;
    }

    @Override
    public String getDescription() {
        return "Replies to the message";
    }

    @Override
    public String getSyntax() {
        return "/reply <message>";
    }

    @Override
    public String getPermission() {
        return "";
    }
}
