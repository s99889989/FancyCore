package com.daxton.fancycore.api.other;

import java.text.DecimalFormat;

public class DigitConversion {

    private static String[] suffix = new String[]{"","k","m","b","t"};
    private static int MAX_LENGTH = 4;

    //位數轉換，去掉一些位數
    public static String NumberUtil(double number, String decimal){
        DecimalFormat decimalFormat = new DecimalFormat(decimal);
        String string = decimalFormat.format(number);
        if(string.contains(",")){
            string = string.replace(",",".");
        }
        if(string.endsWith(".0")){
            string = string.replace(".0","");
        }

         return string;
    }

    //轉換單位
    public static String format(double number) {
        String r = new DecimalFormat("##0E0").format(number);
        r = r.replaceAll("E[0-9]", suffix[Character.getNumericValue(r.charAt(r.length() - 1)) / 3]);
        while(r.length() > MAX_LENGTH || r.matches("[0-9]+\\.[a-z]")){
            r = r.substring(0, r.length()-2) + r.substring(r.length() - 1);
        }
        return r;
    }

    /**位數字的轉換2**/
    public static String NumberChange2(String string,String content){

        String[] stl2 = content.split(",");
        for(String stringList2 : stl2){
            String[] stl3 = stringList2.split(">");
            if(string.replace(stl3[0],"").length() == 0){
                string = string.replace(stl3[0],stl3[1]);

            }
        }
        return string;
    }

    /**對首位字做處理2**/
    public static String NumberHead2(String string, String content){
        String lastString = "";
        char[] c = string.toCharArray();
        String[] strings = new String[c.length];
        for(int i = 0 ; i < c.length; i++){
            if(i == 0){
                strings[i] = NumberChange2(String.valueOf(c[i]),content);
            }else {
                strings[i] = String.valueOf(c[i]);
            }
        }
        for(String value : strings){
            lastString = lastString + value;
        }
        return lastString;
    }
    /**對單位字做處理，不包括首位字2**/
    public static String NumberUnits2(String string, String content){
        String lastString = "";
        char[] c = string.toCharArray();
        String[] strings = new String[c.length];
        for(int i = 0 ; i < c.length; i++){
            if(i != 0 & (i+1)%2 != 0){
                strings[i] = NumberChange2(String.valueOf(c[i]),content);

            }else {
                strings[i] = String.valueOf(c[i]);
            }
        }
        for(String value : strings){
            lastString = lastString + value;
        }

        return lastString;
    }

    /**對雙位字做處理，不包括首位字2**/
    public static String NumberDouble2(String string, String content){

        String lastString = "";
        char[] c = string.toCharArray();
        String[] strings = new String[c.length];
        for(int i = 0 ; i < c.length; i++){
            if( i != 0 & (i+1)%2 == 0 ){

                strings[i] = NumberChange2(String.valueOf(c[i]),content);

            }else {
                strings[i] = String.valueOf(c[i]);
            }
        }
        for(String value : strings){
            lastString = lastString + value;
        }
        return lastString;
    }

    /**單字左右邊加上字**/
    public static String stringAddRight2(String string, String content){
        String[] contests = content.split(",");
        /**英文**/
        String[] cA = contests[0].split(">");
//        /**泰文**/
//        String[] cB = contests[1].split(",");
        /**其他**/
        String[] cC = contests[1].split(">");

        String lastString = "";
        char[] c = string.toCharArray();
        String[] strings = new String[c.length];
        for(int i = 0 ; i < c.length; i++){
            int amount = c[i];
            if(amount < 4000){
                strings[i] = cA[0]+c[i]+cA[1];
            }else {
                strings[i] = cC[0]+c[i]+cC[1];
            }

        }
        for(String value : strings){
            lastString = lastString + value;
        }
        return lastString;
    }

}
