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

public class InventoryCommand implements CommandExecutor, ICommand {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            MessageSender.sendMessageAtPath(sender, ConfigMessage.ONLY_PLAYER_USAGE);
            return false;
        }
        Player p = (Player) sender;
        if(p.hasPermission(getPermission())){
            if(args.length == 1){
                Player target = Bukkit.getPlayer(args[0]);
                if(target == null){
                    MessageSender.sendMessageAtPath(p,ConfigMessage.PLAYER_NOT_ACTIVE,
                            "PREFIX", ConfigMessage.PREFIX.getMessage(),
                            "PLAYER", args[0]);
                    return false;
                }
                p.openInventory(target.getInventory());
            }else{
                MessageSender.sendMessageAtPath(p,ConfigMessage.WRONG_SYNTAX,
                        "PREFIX", ConfigMessage.PREFIX.getMessage(),
                        "SYNTAX", getSyntax());
            }
        }


        return false;
    }

    @Override
    public String getDescription() {
        return "Opens inventory of specified player";
    }

    @Override
    public String getSyntax() {
        return "/inventory <playerName>";
    }

    @Override
    public String getPermission() {
        return "bladecore.inventory";
    }
}
