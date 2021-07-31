package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.api.other.CountWords;
import org.bukkit.entity.LivingEntity;

public class ConversionMessage {

    public static String valueOf(String inputString){
        if(inputString.contains("{#") && inputString.contains("}")){

            int num1 = CountWords.count(inputString, "\\{#");
            int num2 = CountWords.count(inputString, "\\}");
            if(num1 == num2){
                for(int i = 0; i < num1 ; i++){
                    int head = inputString.indexOf("{#");
                    int tail = inputString.indexOf("}");

                    String change = ConversionColor.valueOf(inputString.substring(head,tail+1));

                    inputString = inputString.replace(inputString.substring(head,tail+1),change);
                }
            }

        }
        return inputString;
    }

}
