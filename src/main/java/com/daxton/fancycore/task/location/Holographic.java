package com.daxton.fancycore.task.location;

import com.daxton.fancycore.api.taskaction.MapGetKey;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public class Holographic {

    public static void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, String taskID, Location inputLocation){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //標記名稱
        String mark = actionMapHandle.getString(new String[]{"mark","mk"},"0");
        //顯示訊息
        String message = actionMapHandle.getString(new String[]{"message","m"}, null);
        //移除顯示訊息
        int removeMessage = actionMapHandle.getInt(new String[]{"removemessage","rm"}, 0);
        //是否要移動
        boolean teleport = actionMapHandle.getBoolean(new String[]{"teleport","tp"}, false);
        //顯示物品的ID
        String itemID = actionMapHandle.getString(new String[]{"itemid","iid"}, null);
        //是否刪除
        boolean delete = actionMapHandle.getBoolean(new String[]{"delete","deleteall","d"}, false);
        //改變指定行數內容
        int changeMessage = actionMapHandle.getInt(new String[]{"cm","changeMessage"}, 0);







    }

}
