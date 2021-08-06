package com.daxton.fancycore.api.judgment;

import java.math.BigDecimal;

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

    //判斷是否為16進位數字
    public static boolean isHexNumber(String inputString){
        boolean output = true;
        try {
            if(inputString.lastIndexOf(".")==-1) {
                Integer.parseInt( inputString, 16 );
            } else {
                int f = inputString.length() - inputString.lastIndexOf(".") - 1;
                // 移除 . [效過同  (x * 16^f)], 將所得整數轉 10 進位
                BigDecimal i = new BigDecimal(Integer.parseInt( inputString.replace(".", ""), 16 ));
                // 所得結果除以 16^f; 因為每加一位 16 進制小數對應 10 進位有四位; 故精準度取 f*4
                i.divide(BigDecimal.valueOf(Math.pow(16,f)), f*4, BigDecimal.ROUND_CEILING );
            }
        }catch (Exception exception){
            output = false;
        }
        return output;
    }

}
