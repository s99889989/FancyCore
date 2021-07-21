package com.daxton.fancycore.api.judgment;

public class NumberJudgment {

    //判斷是否是數字
    public static boolean isNumber(String inputString){
        boolean output = true;
        try {
            Double.valueOf(inputString);
        }catch (NumberFormatException exception){
            output = false;
        }
        return output;
    }

}
