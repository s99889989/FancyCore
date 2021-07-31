package com.daxton.fancycore.api.character.stringconversion;

import com.daxton.fancycore.api.character.placeholder.PlaceholderOther;
import com.daxton.fancycore.api.character.placeholder.PlaceholderSelf;
import com.daxton.fancycore.api.character.placeholder.PlaceholderTarget;
import com.daxton.fancycore.api.other.CountWords;
import org.bukkit.entity.LivingEntity;

public class ConversionPlaceholder {


    public ConversionPlaceholder(){

    }

    public static String valueOf(LivingEntity self, LivingEntity target, String inputString){
        String outputString = "";
        int numHead = CountWords.count(inputString, "<");
        int numTail = CountWords.count(inputString, ">");
        if(numHead == numTail){
            for(int i = 0; i < numHead ; i++){
                int head = inputString.indexOf("<");
                int tail = inputString.indexOf(">");
                if(inputString.contains("<") && inputString.contains(">")){
                    if(inputString.substring(head,tail+1).toLowerCase().contains("<cd_other_")){
                        inputString = inputString.replace(inputString.substring(head,tail+1), PlaceholderOther.getOther(inputString.substring(head,tail+1)));
                        continue;
                    }
                    if(inputString.substring(head,tail+1).toLowerCase().contains("<cd_self_")){
                        inputString = inputString.replace(inputString.substring(head,tail+1), PlaceholderSelf.valueOf(self,target,inputString.substring(head,tail+1)));
                        continue;
                    }
                    if(target != null && inputString.substring(head,tail+1).toLowerCase().contains("<cd_target_")){
                        inputString = inputString.replace(inputString.substring(head,tail+1), PlaceholderTarget.valueOf(target,inputString.substring(head,tail+1)));
                        continue;
                    }
                }else {
                    break;
                }
            }
        }
        outputString = inputString;

        return outputString;
    }


}
