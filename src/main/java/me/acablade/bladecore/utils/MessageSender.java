package me.acablade.bladecore.utils;

import me.acablade.bladecore.objects.CustomConfig;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageSender {

    public static String placeholders(String msg, String... placeholders) {
        int i;
        for (i = 0; i < placeholders.length - 1; i += 2) {
            String placeholder = placeholders[i];
            if (placeholder.charAt(0) != '{') {
                placeholder = "{" + placeholder;
            }
            if (placeholder.charAt(placeholder.length() - 1) != '}') {
                placeholder = placeholder + "}";
            }
            placeholders[i] = placeholder;
            }
        for (i = 0; i < placeholders.length - 1; i += 2) {
            msg = msg.replace(placeholders[i], placeholders[i + 1]);
        }
        return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', msg.replace("{PREFIX}", ConfigMessage.PREFIX.getMessage())));
    }

    public static void sendMessageAtPath(CommandSender to,String path,String... placeholders){
        to.sendMessage(placeholders(CustomConfig.getCustomConfig().getString(path),placeholders));
    }
    public static void sendMessageAtPath(CommandSender to, ConfigMessage path, String... placeholders){
        to.sendMessage(placeholders(path.getMessage(),placeholders));
    }


}
