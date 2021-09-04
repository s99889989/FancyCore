package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.api.other.StringFind;
import org.bukkit.entity.LivingEntity;

import java.util.List;

public class ConversionCustom {


    public ConversionCustom(){

    }

    public static String valueOf(LivingEntity self, LivingEntity target, String inputString){

        String outputString = "";
        inputString = inputString.replace(" ","").replace("&","");
        if(KeyMap.conversion_Key.get(inputString) == null){
            return inputString;
        }
        List<String> stringList = KeyMap.conversion_Key.get(inputString);
        for(int i = 0 ; i < stringList.size() ; i++){
            String stringMessage = stringList.get(i);
            String headKey = StringFind.getAction(stringMessage);
            String content = StringFind.getContent(stringMessage);
            String targetKey = StringFind.getTarget(stringMessage);
            if(i == 0){
                if(headKey.toLowerCase().contains("content")){
                    outputString = CustomConversion(self, target, headKey, content, targetKey);
                }else {
                    outputString = " X"+inputString+"X ";
                    break;
                }
            }else {
                outputString = CustomChange(self,target,headKey, outputString, content);
            }

        }

        return outputString;
    }

    public static String CustomConversion(LivingEntity self, LivingEntity target, String headKey, String content, String targetKey){
        String outputString = "";
        if(headKey.toLowerCase().contains("content")){
            if(content.contains("&")){
                content = ConversionMain.valueOf(self,target,content, true);
            }
            if(content.contains("<") && content.contains(">")){
                //FancyCore.fancyCore.getLogger().info(content);
                content = ConversionPlaceholder.valueOf(self,target,content);
            }
            if(content.contains("%")){
                content = ConversionPlaceholderAPI.valueOf(self,target,content,targetKey);
            }
            outputString = content;
        }


        return outputString;
    }

    public static String CustomChange(LivingEntity self, LivingEntity target, String headKey, String content,String changeContent){
        String outputString = "";

        if(headKey.toLowerCase().contains("conver")){
            outputString = ConversionChange.valueOf(self,target,content,changeContent);
        }


        if(headKey.toLowerCase().contains("math")){

            outputString = ConversionMath.valueOf(content,changeContent);
        }


        return outputString;
    }


}
