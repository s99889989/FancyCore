package com.daxton.fancycore.api.taskaction;

import com.daxton.fancycore.api.other.CountWords;
import com.daxton.fancycore.api.other.StringSplit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringToMap {

    //把動作字串轉成，動作Map
    public static Map<String, String> toActionMap(String inputString){
        Map<String, String> actionMap = new HashMap<>();
        if (inputString.contains("[") && inputString.contains("]")) {

            int num1 = CountWords.count(inputString, "\\[");
            int num2 = CountWords.count(inputString, "\\]");
            if (num1 == 1 && num2 == 1) {
                //設定動作類型
                String actionType = inputString.substring(0, inputString.indexOf("[")).trim();
                actionMap.put("actiontype",actionType.toLowerCase().trim());
                //cd.getLogger().info("ActionType"+" : "+actionType);

                //從]往後的字串
                String lastSet = inputString.substring(inputString.indexOf("]")+1)+" ";
                //設定目標
                if(lastSet.contains("@")){
                    String targetKey = lastSet.substring(lastSet.indexOf("@"), lastSet.indexOf(" ",lastSet.indexOf("@")));

                    actionMap.put("targetkey",targetKey);
                    //cd.getLogger().info("TargetKey"+" : "+targetKey);
                }
                //設定觸發
                if(lastSet.contains("~")){
                    String triggerKey = lastSet.substring(lastSet.indexOf("~"), lastSet.indexOf(" ",lastSet.indexOf("~")));
                    actionMap.put("triggerkey",triggerKey);
                    //cd.getLogger().info("TriggerKey"+" : "+triggerKey);
                }

                String midSet = inputString.substring(inputString.indexOf("[")+1, inputString.indexOf("]"));

                List<String> midSetList = StringSplit.toList(midSet, ";");
                midSetList.forEach(midKey -> {
                    String[] midArray = midKey.split("=");
                    if(midArray.length == 2){
                        //cd.getLogger().info(midArray[0]+" : "+midArray[1]);
                        actionMap.put(midArray[0].toLowerCase(),midArray[1]);
                    }
                });
            }
        }

        return actionMap;
    }

    //把目標字串轉成，目標Map
    public static Map<String, String> toTargetMap(String inputString){
        Map<String, String> targetMap = new HashMap<>();
        if (inputString.contains("{") && inputString.contains("}")) {

            int num1 = CountWords.count(inputString, "\\{");
            int num2 = CountWords.count(inputString, "\\}");
            if (num1 == 1 && num2 == 1) {

                String targetType = inputString.substring(inputString.indexOf("@")+1, inputString.indexOf("{")).trim();

                targetMap.put("targettype", targetType.toLowerCase());

                String midSet = inputString.substring(inputString.indexOf("{")+1, inputString.indexOf("}"));

                List<String> midSetList = StringSplit.toList(midSet,";");
                midSetList.forEach(midKey -> {
                    String[] midArray = midKey.split("=");
                    if(midArray.length == 2){
                        targetMap.put(midArray[0].toLowerCase(), midArray[1]);
                    }
                });
            }
        }else if(!inputString.contains("{") && !inputString.contains("}")){
            targetMap.put("targettype", inputString.replace("@","").trim().toLowerCase());
        }else {
            targetMap.put("targettype", "null");
        }
//        targetMap.forEach((s, s2) -> {
//            CustomDisplay.getCustomDisplay().getLogger().info(s+" : "+s2);
//        });

        return targetMap;
    }

}
