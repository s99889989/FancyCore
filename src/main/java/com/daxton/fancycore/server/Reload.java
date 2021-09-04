package com.daxton.fancycore.server;

import com.daxton.fancycore.api.character.stringconversion.KeyMap;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.other.customvalue.SetCustomValue;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.task.meta.ClearAction;

public class Reload {

    //重新讀取的一些任務
    public static void execute(){
        //清除全部在執行的動作
        ClearAction.all();
        //重新取設定檔
        FileConfig.reload();
//        //設定DC連接
//        FileConfiguration fileConfig = FileConfig.config_Map.get("config.yml");
//        boolean discordEnable = fileConfig.getBoolean("Discord.enable");
//        if(discordEnable){
//            String token = fileConfig.getString("Discord.token");
//            if(token != null){
//                OtherManager.client = DiscordClientBuilder.create(token)
//                        .build()
//                        .login()
//                        .block();
//            }
//        }
        //重新設置轉換字串
        KeyMap.reload();
        //設置動作Map
        StringToMap.setActionMap();
        //自訂值
        SetCustomValue.execute();
    }

}
