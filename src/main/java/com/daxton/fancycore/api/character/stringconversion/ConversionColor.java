package com.daxton.fancycore.api.character.stringconversion;

public class ConversionColor {


    public ConversionColor(){

    }

    public static String valueOf(String inputString){
        if(inputString.length() == 9){
            String key1 = inputString.substring(2,3);
            String key2 = inputString.substring(3,4);
            String key3 = inputString.substring(4,5);
            String key4 = inputString.substring(5,6);
            String key5 = inputString.substring(6,7);
            String key6 = inputString.substring(7,8);
            inputString = "§x§"+key1+"§"+key2+"§"+key3+"§"+key4+"§"+key5+"§"+key6;
        }
        return inputString;
    }


}
