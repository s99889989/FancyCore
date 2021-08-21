package com.daxton.fancycore.api.character.conversion;

import com.daxton.fancycore.api.judgment.NumberJudgment;

import java.math.BigDecimal;

public class NumberConversion {

    //16進制轉10進制，如果錯誤，就回傳空值
    public static String hexToTen(String in){
        if(!NumberJudgment.isHexNumber(in)){
            return "";
        }
        String out;
        if(in.lastIndexOf(".")==-1) {
            out = String.valueOf(Integer.parseInt( in, 16 ));
        } else {
            int f = in.length() - in.lastIndexOf(".") - 1;
            // 移除 . [效過同  (x * 16^f)], 將所得整數轉 10 進位
            BigDecimal i = new BigDecimal(Integer.parseInt( in.replace(".", ""), 16 ));
            // 所得結果除以 16^f; 因為每加一位 16 進制小數對應 10 進位有四位; 故精準度取 f*4
            out = i.divide(BigDecimal.valueOf(Math.pow(16,f)), f*4, BigDecimal.ROUND_CEILING )+"";
        }

        return out;
    }

    public static String tenToHex(String in){
        if(!NumberJudgment.isHexNumber(in)){
            return "0";
        }
        return Integer.toHexString(Integer.parseInt(in));
    }


}
