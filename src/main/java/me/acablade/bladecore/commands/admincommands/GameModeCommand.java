package me.acablade.bladecore.commands.admincommands;

import me.acablade.bladecore.objects.ICommand;
import me.acablade.bladecore.utils.ConfigMessage;
import me.acablade.bladecore.utils.GameModeEnum;
import me.acablade.bladecore.utils.MessageSender;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameModeCommand implements CommandExecutor, ICommand {
    @Override
    public String getDescription() {
        return "short version of vanilla gamemode command";
    }

    @Override
    public String getSyntax() {
        return "/gamemode <gamemode> [player]";
    }

    @Override
    public String getPermission() {
        return "bladecore.gamemode";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!sender.hasPermission(getPermission())){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
            return false;
        }
        if(args.length == 1){
            if(!(sender instanceof Player)){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.ONLY_PLAYER_USAGE);
                return false;
            }
            Player p = (Player) sender;
            GameModeEnum gameModeEnum;
            try {
                int i = Integer.parseInt(args[0]);
                gameModeEnum = GameModeEnum.getById(i);
            } catch (NumberFormatException nfe) {
                gameModeEnum = GameModeEnum.getByName(args[0]);
            }
            if(gameModeEnum != null){
                p.setGameMode(gameModeEnum.getGameMode());
                MessageSender.sendMessageAtPath(sender,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PREFIX", ConfigMessage.PREFIX.getMessage(),
                        "PLAYER", p.getName(),
                        "GAMEMODE", gameModeEnum.getGameMode().name());
            }else{
                MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                        "PREFIX",ConfigMessage.PREFIX.getMessage(),
                        "SYNTAX",getSyntax());
            }
        }else if(args.length == 2){

            Player p = Bukkit.getPlayer(args[1]);
            if(p==null){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.PLAYER_NOT_ACTIVE);
            }

            GameModeEnum gameModeEnum;
            try {
                int i = Integer.parseInt(args[0]);
                gameModeEnum = GameModeEnum.getById(i);
            } catch (NumberFormatException nfe) {
                gameModeEnum = GameModeEnum.getByName(args[0]);
            }
            if(gameModeEnum != null){
                p.setGameMode(gameModeEnum.getGameMode());
                MessageSender.sendMessageAtPath(sender,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PREFIX", ConfigMessage.PREFIX.getMessage(),
                        "PLAYER", p.getName(),
                        "GAMEMODE", gameModeEnum.getGameMode().name());
                MessageSender.sendMessageAtPath(p,ConfigMessage.SUCCESSFUL_GAMEMODE,
                        "PREFIX", ConfigMessage.PREFIX.getMessage(),
                        "PLAYER", p.getName(),
                        "GAMEMODE", gameModeEnum.getGameMode().name());
            }else{
                MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                        "PREFIX",ConfigMessage.PREFIX.getMessage(),
                        "SYNTAX",getSyntax());
            }
        }else{
            MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                    "PREFIX",ConfigMessage.PREFIX.getMessage(),
                    "SYNTAX",getSyntax());
        }

        return false;
    }
}
