package me.acablade.bladecore.utils;

import me.acablade.bladecore.objects.CustomConfig;

public enum ConfigMessage {


    WRONG_SYNTAX("wrong-syntax"),
    PM_FORMAT("pm-format"),
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
