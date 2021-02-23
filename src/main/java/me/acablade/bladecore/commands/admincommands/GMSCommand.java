package me.acablade.bladecore.commands.admincommands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GMSCommand implements CommandExecutor, ICommand {
    @Override
    public String getDescription() {
        return "quick gamemode survival command";
    }

    @Override
    public String getSyntax() {
        return "/gms";
    }

    @Override
    public String getPermission() {
        return "bladecore.gamemode";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender.hasPermission(getPermission()))){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
            return false;
        }

        if(args.length > 1){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.WRONG_SYNTAX,
                    "SYNTAX",getSyntax());
        }else{
            if(args.length == 1){
                Player p = Bukkit.getPlayer(args[0]);
                if(p==null){
                    MessageSender.sendMessageAtPath(sender,ConfigMessage.PLAYER_NOT_ACTIVE);
                    return false;
                }
                p.setGameMode(GameMode.SURVIVAL);
                MessageSender.sendMessageAtPath(sender,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PLAYER", p.getName(),
                        "GAMEMODE", GameMode.SURVIVAL.name());
                MessageSender.sendMessageAtPath(p,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PLAYER", p.getName(),
                        "GAMEMODE", GameMode.SURVIVAL.name());

            }else{
                if(!(sender instanceof Player)){
                    MessageSender.sendMessageAtPath(sender,ConfigMessage.ONLY_PLAYER_USAGE);
                    return false;
                }
                Player p = (Player) sender;
                p.setGameMode(GameMode.SURVIVAL);
                MessageSender.sendMessageAtPath(sender,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PLAYER", p.getName(),
                        "GAMEMODE", GameMode.SURVIVAL.name());
            }
        }

        return false;
    }
}
