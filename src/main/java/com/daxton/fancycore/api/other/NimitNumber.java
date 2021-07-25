package com.daxton.fancycore.api.other;

public class NimitNumber {

    //丟入數字，把數字修改至設定範圍內
    public static int get(int input, int inputMin, int inputMax){
        int output = input;
        int min = inputMin;
        int max = inputMax;
        if(min > max){
            min = inputMax;
            max = inputMin;
        }
        if(output < min){
            output = min;
        }
        if(output > max){
            output = max;
        }
        return output;
    }

}
