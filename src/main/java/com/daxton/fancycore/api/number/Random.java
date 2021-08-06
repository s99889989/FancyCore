package com.daxton.fancycore.api.number;

public class Random {

    //隨機單個16進位字串
    public static String randomHex(){
        int k = (int) (Math.random() *15);
        return Integer.toHexString(k);
    }

}
