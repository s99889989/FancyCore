package com.daxton.fancycore.api.conversion;

public class StringConversion {

    //轉成int
    public static int getInt(int i, String inputString){
        int output;
        if(inputString.contains(".")){
            inputString = inputString.substring(0, inputString.indexOf("."));
        }
        try {
            output = Integer.parseInt(inputString);
        }catch (NumberFormatException exception){
            output = i;
        }
        return output;
    }
    //轉成double
    public static double getDoubel(double i, String inputString){
        double output;
        try {
            output = Double.parseDouble(inputString);
        }catch (NumberFormatException exception){
            output = i;
        }
        return output;
    }

    //轉成boolean
    public static boolean getBoolean(String inputString){
        return Boolean.parseBoolean(inputString);
    }

}
