package me.acablade.bladecore.utils;

import me.acablade.bladecore.objects.CustomConfig;

public enum ConfigMessage {


    WRONG_SYNTAX("wrong-syntax"),
    PM_FORMAT("pm-format"),
    ONLY_PLAYER_USAGE("only-player-command"),
    NO_REPLIES("no-reply"),
    PLAYER_NOT_ACTIVE("player-not-active"),
    SUCCESSFUL_TELEPORT("successful-teleport"),
    CHAT_CLEARED("chat-cleared"),
    FLY_ENABLED("fly-enabled"),
    FLY_DISABLED("fly-disabled"),
    BROADCAST_FORMAT("broadcast-format"),
    INSUFFICIENT_PERMISSIONS("insufficient-permissions"),
    PREFIX("prefix");


    private String path;
    ConfigMessage(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getMessage(){
        return CustomConfig.getCustomConfig().getString(this.path);
    }
}
