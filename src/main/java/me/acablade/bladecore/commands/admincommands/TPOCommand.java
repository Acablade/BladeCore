package me.acablade.bladecore.commands.admincommands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TPOCommand implements CommandExecutor, ICommand {

    @Override
    public String getDescription() {
        return "Teleport command";
    }

    @Override
    public String getSyntax(){
        return "/tpo <player> [player]";
    }

    @Override
    public String getPermission() {
        return "bladecore.tpo";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 2){
            //Target player
            if(!sender.hasPermission(getPermission())){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.INSUFFICIENT_PERMISSIONS);
                return false;
            }
            Player teleportTo = Bukkit.getPlayer(args[1]);
            //Player that gets teleported
            Player toTeleport = Bukkit.getPlayer(args[0]);

            if(teleportTo != null && toTeleport != null){
                teleport(sender,toTeleport,teleportTo);
            }else{
                if(teleportTo == null && toTeleport == null){
                    MessageSender.sendMessageAtPath(sender, ConfigMessage.PLAYER_NOT_ACTIVE,"PLAYER","Both players");
                }else if(teleportTo == null){
                    MessageSender.sendMessageAtPath(sender, ConfigMessage.PLAYER_NOT_ACTIVE,"PLAYER",teleportTo.getName());
                }else{
                    MessageSender.sendMessageAtPath(sender, ConfigMessage.PLAYER_NOT_ACTIVE,"PLAYER",toTeleport.getName());
                }
            }

        }else if(args.length == 1){
            if(!(sender instanceof Player)){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.ONLY_PLAYER_USAGE);
                return false;
            }
            Player player = (Player) sender;
            Player dest = Bukkit.getPlayer(args[0]);
            if(dest == null){
                MessageSender.sendMessageAtPath(sender, ConfigMessage.PLAYER_NOT_ACTIVE,"PLAYER",dest.getName());
                return false;
            }
            teleport(sender,player,dest);
        }else{
            MessageSender.sendMessageAtPath(sender, ConfigMessage.WRONG_SYNTAX,
                    "SYNTAX", getSyntax());
        }

        return false;
    }

    public void teleport(@Nullable CommandSender sender, Player player, Player dest){
        player.teleport(dest);
        MessageSender.sendMessageAtPath(sender == null ? player:sender,ConfigMessage.SUCCESSFUL_TELEPORT,
                    "PLAYER", player.getName(),
                    "DESTINATION",dest.getName());
    }

}
