package com.daxton.fancycore.manager;

import com.daxton.fancycore.api.hook.MythicMob.MobData;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MobManager {

    //魔物資料
    public static Map<String, MobData> mob_Data_Map = new HashMap<>();
    //儲存Mobs文件位置
    public static Map<String, String> mythicMobs_mobFile_Map = new HashMap<>();
    //ModelEngine資料
    public static Map<String,String> model_Engine_Map = new HashMap<>();
    //獲得MythicMob的活動實體
    public static Map<UUID, ActiveMob> mythicMobs_ActiveMob_Map = new HashMap<>();


}
