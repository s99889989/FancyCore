package com.daxton.fancycore.manager;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.other.playerdata.PlayerDataFancy;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManagerCore {
    //玩家資訊
    public static Map<UUID, PlayerDataFancy> player_Data_Map = new HashMap<>();
    //玩家Gui
    public static Map<UUID, GUI> player_Gui_Map = new HashMap<>();

}
