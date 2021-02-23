package me.acablade.bladecore.utils;

import org.bukkit.GameMode;

public enum GameModeEnum {

    SURVIVAL(GameMode.SURVIVAL,0),
    CREATIVE(GameMode.CREATIVE,1),
    ADVENTURE(GameMode.ADVENTURE,2),
    SPECTATOR(GameMode.SPECTATOR,3);

    private final GameMode gameMode;
    private final int id;
    GameModeEnum(GameMode gameMode, int id){
        this.id = id;
        this.gameMode = gameMode;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public int getId() {
        return id;
    }

    public static GameModeEnum getById(int id){
        for(GameModeEnum gameModeEnum: GameModeEnum.values()){
            if(gameModeEnum.id == id){
                return gameModeEnum;
            }
        }
        return null;
    }

    public static GameModeEnum getByName(String name){
        for(GameModeEnum gameModeEnum: GameModeEnum.values()){
            if(gameModeEnum.toString().equalsIgnoreCase(name)){
                return gameModeEnum;
            }
        }
        return null;
    }


}
