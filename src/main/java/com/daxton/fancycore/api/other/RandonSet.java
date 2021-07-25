package com.daxton.fancycore.api.other;

public class RandonSet {

    public static int getInt(int inputMin, int inputMax){
        int min = inputMin;
        int max = inputMax;
        if(inputMin > inputMax){
            min = inputMax;
            max = inputMin;
        }
        return (int) (Math.random() * (max - min + 1) + min);
    }


}
