package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.other.DigitConversion;
import com.daxton.fancycore.api.other.StringSplit;
import org.bukkit.entity.LivingEntity;

public class ConversionChange {

    public ConversionChange(){

    }

    public static String valueOf(LivingEntity self, LivingEntity target, String inputString, String changeString){
        String outputString = "";
        String function = null;
        String message = null;
        for(String string : StringSplit.toList(changeString,";")){

            if(string.toLowerCase().contains("function=") || string.toLowerCase().contains("fc=")){
                String[] strings = string.split("=");
                if(strings.length == 2){
                    function= strings[1];
                }
            }

            if(string.toLowerCase().contains("message=") || string.toLowerCase().contains("m=")){
                String[] strings = string.split("=");
                if(strings.length == 2){
                    message= strings[1];
                }
            }

        }

        if(function != null){
            if(message != null){
                outputString = ChangeConversion(self, target, inputString, function, message);
            }else {
                outputString = ChangeConversion(self, target, inputString, function);
            }
        }else {
            outputString = inputString;
        }

        return outputString;
    }

    //沒有轉換字
    public static String ChangeConversion(LivingEntity self, LivingEntity target, String inputString,String function){
        if(function.equalsIgnoreCase("StringColor")){
            return inputString.replace("§h", "§0").replace("§i", "§1").replace("§j", "§2").replace("§k", "§3").replace("§l", "§4").replace("§m", "§5").replace("§n", "§6").replace("§o", "§7").replace("§p", "§8").replace("§q", "§9");
        }
        return inputString;
    }


    //有轉換字
    public static String ChangeConversion(LivingEntity self, LivingEntity target, String inputString,String function,String changeMessage){
        String outputString = "";

        switch (function.toLowerCase()){
            case "contain":
                String[] containKeyList = changeMessage.split(",");
                for(String containKey : containKeyList){
                    String[] containKeyList2 = containKey.split(">");
                    if(containKeyList2.length == 2){
                        inputString = inputString.replace(containKeyList2[0],containKeyList2[1]);
                    }
                }
                outputString = inputString;
                break;
            case "containcolor":
                String[] containColorList = changeMessage.split(",");
                for(String containKey : containColorList){
                    String[] containKeyList2 = containKey.split(">");
                    if(containKeyList2.length == 2){
                        inputString = inputString.replace("§0", "§h").replace("§1", "§i").replace("§2", "§j").replace("§3", "§k").replace("§4", "§l").replace("§5", "§m").replace("§6", "§n").replace("§7", "§o").replace("§8", "§p").replace("§9", "§q");
                        inputString = inputString.replace(containKeyList2[0],containKeyList2[1]);
                    }
                }
                outputString = inputString;
                break;
            case "containall":
                String[] containallKeyList = changeMessage.split(",");
                for(String containallKey : containallKeyList){
                    String[] containallKeyList2 = containallKey.split(">");
                    if(containallKeyList2.length == 2 && inputString.contains(containallKeyList2[0])){

                        if(containallKeyList2[1].contains("&")){

                            outputString = ConversionMain.valueOf(self,target,containallKeyList2[1], true);

                        }else {
                            outputString = containallKeyList2[1];
                        }

                        break;
                    }else {
                        outputString = inputString;
                    }
                }
                break;
            case "exsame":
                String[] exsameKeyList = changeMessage.split(",");
                for(String exsameKey : exsameKeyList){
                    String[] exsameKeyList2 = exsameKey.split(">");
                    if(exsameKeyList2.length == 2){
                        if(inputString.equals(exsameKeyList2[0])){
                            inputString = exsameKeyList2[1];
                            break;
                        }
                    }
                }
                outputString = inputString;
                break;
            case "converhead":
                outputString = DigitConversion.NumberHead2(inputString,changeMessage);
                break;
            case "converunits":
                outputString = DigitConversion.NumberUnits2(inputString,changeMessage);
                break;
            case "converdouble":
                outputString = DigitConversion.NumberDouble2(inputString,changeMessage);
                break;
            case "converaddrl":
                outputString = DigitConversion.stringAddRight2(inputString,changeMessage);
                break;
            default:
                outputString = inputString;
        }

        return outputString;
    }
}
