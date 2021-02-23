package me.acablade.bladecore.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PrivateMessaging {

    public static HashMap<UUID,UUID> messageHashMap = new HashMap<>();

    public static void sendMessage(Player to, CommandSender from, String message) {
        MessageSender.sendMessageAtPath(to,ConfigMessage.PM_FORMAT,"PREFIX", ConfigMessage.PREFIX.getMessage(), "TO",to.getName(), "FROM", "Sen", "MESSAGE",message);
        MessageSender.sendMessageAtPath(to,ConfigMessage.PM_FORMAT,"PREFIX", ConfigMessage.PREFIX.getMessage(), "TO","Sen", "FROM", from.getName(), "MESSAGE",message);
        if(from instanceof Player){
            messageHashMap.put(to.getUniqueId(),((Player) from).getUniqueId());
        }
    }

}
