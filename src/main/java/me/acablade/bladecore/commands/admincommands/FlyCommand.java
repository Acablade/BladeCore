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
import org.jetbrains.annotations.Nullable;

public class FlyCommand implements CommandExecutor, ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender.hasPermission(getPermission()))){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.INSUFFICIENT_PERMISSIONS);
            return false;
        }

        if(args.length == 0){
            if(!(sender instanceof Player)){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.ONLY_PLAYER_USAGE);
                return false;
            }

            Player p = (Player) sender;
            allowFlight(null,p,!p.getAllowFlight());
        }else if(args.length == 1){
            Player target = Bukkit.getPlayer(args[0]);
            if(target == null){
                MessageSender.sendMessageAtPath(sender,ConfigMessage.PLAYER_NOT_ACTIVE);
                return false;
            }
            allowFlight(sender,target,!target.getAllowFlight());
        }else{
            MessageSender.sendMessageAtPath(sender,ConfigMessage.WRONG_SYNTAX,
                    "SYNTAX",getSyntax());
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Toggles flight of a player";
    }

    @Override
    public String getSyntax() {
        return "/fly [player]";
    }

    @Override
    public String getPermission() {
        return "bladecore.fly";
    }

    private void allowFlight(@Nullable CommandSender sender, Player p, boolean allowFlight){
        p.setAllowFlight(allowFlight);
        MessageSender.sendMessageAtPath(p, p.getAllowFlight()?ConfigMessage.FLY_ENABLED:ConfigMessage.FLY_DISABLED,
                "PLAYER", p.getName());
        if(sender!= null){
            MessageSender.sendMessageAtPath(sender, p.getAllowFlight()?ConfigMessage.FLY_ENABLED:ConfigMessage.FLY_DISABLED,
                    "PLAYER", p.getName());
        }
    }

}
