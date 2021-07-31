package com.daxton.fancycore.manager;

import com.daxton.fancycore.api.entity.mob.MobData;

import java.util.HashMap;
import java.util.Map;

public class MobManager {

    //魔物資料
    public static Map<String, MobData> mob_Data_Map = new HashMap<>();
    //儲存Mobs文件位置
    public static Map<String, String> mythicMobs_mobFile_Map = new HashMap<>();
    //用UUID字串儲存MM等級
    public static Map<String,String> mythicMobs_Level_Map = new HashMap<>();
    //用UUID字串儲存MMID
    public static Map<String,String> mythicMobs_mobID_Map = new HashMap<>();
    //ModelEngine資料
    public static Map<String,String> modelengine_Map = new HashMap<>();

}
