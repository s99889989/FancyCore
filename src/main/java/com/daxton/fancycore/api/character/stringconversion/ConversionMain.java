package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.api.other.CountWords;
import org.bukkit.entity.LivingEntity;

public class ConversionMain {



    public ConversionMain(){

    }

    public static String valueOf(LivingEntity self, LivingEntity target, String inputString){

        String outputString = "";
        if(inputString != null){
            //顏色代碼轉換
            //inputString = ConversionMessage.valueOf(inputString);
            inputString = ConversionColor.translateHexColorCodes("\\{#", "\\}", inputString);

            if(inputString.contains("&")){
                int num = CountWords.count(inputString, "&");
                if(num%2 == 0){
                    for(int i = 0; i < num/2 ; i++){
                        int head = inputString.indexOf("&");
                        int tail = inputString.indexOf("&",head+1);
                        if(inputString.contains("&")){
                            String change = ConversionCustom.valueOf(self,target,inputString.substring(head,tail+1));
                            inputString = inputString.replace(inputString.substring(head,tail+1),change);
                        }
                    }
                }
            }
            outputString = inputString;
        }

        return outputString;
    }


}
