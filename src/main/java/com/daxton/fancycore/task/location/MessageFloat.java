package com.daxton.fancycore.task.location;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.other.hologram.FloatMessage;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.manager.TaskActionManager;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Map;

public class MessageFloat implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //標記名稱
        String mark = actionMapHandle.getString(new String[]{"mark","mk"},"");
        //改變指定行數內容
        int changeMessage = actionMapHandle.getInt(new String[]{"cm","changeMessage"}, 0);
        //顯示訊息
        String message = actionMapHandle.getString(new String[]{"message","m"}, null);
        //移除顯示訊息
        int removeMessage = actionMapHandle.getInt(new String[]{"removemessage","rm"}, 0);
        //是否要移動
        boolean teleport = actionMapHandle.getBoolean(new String[]{"teleport","tp"}, false);
        //是否刪除
        boolean delete = actionMapHandle.getBoolean(new String[]{"delete","d"}, false);
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);

        if(inputLocation == null && TaskActionManager.task_FloatMessage_Map.get(taskID+mark) != null){
            inputLocation = TaskActionManager.task_FloatMessage_Map.get(taskID+mark).getLocation();
        }

        Location location = GetLocation.getOne(self, target, targetMap, inputLocation);
        //

        if(TaskActionManager.task_FloatMessage_Map.get(taskID+mark) == null){
            if(location != null){
                FloatMessage floatMessage = FloatMessage.createFloatMessage(location);
                //增加訊息
                if(message != null){
                    floatMessage.addLine(message);
                }
                TaskActionManager.task_FloatMessage_Map.put(taskID+mark, floatMessage);
            }
        }else {
            FloatMessage floatMessage = TaskActionManager.task_FloatMessage_Map.get(taskID+mark);
            //移動
            if(teleport && location != null){
                floatMessage.teleport(location);
            }
            //增加修改訊息
            if(message != null){
                if(changeMessage > 0){
                    floatMessage.editLine(changeMessage-1, message);
                }else {
                    floatMessage.addLine(message);
                }
            }

            //移除指定行數
            if(removeMessage > 0){
                floatMessage.removeLine(removeMessage-1);
            }
            //刪除
            if(delete){
                floatMessage.delete();
                TaskActionManager.task_FloatMessage_Map.remove(taskID);
            }

        }




    }

}
