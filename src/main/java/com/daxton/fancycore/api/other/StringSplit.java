package com.daxton.fancycore.api.other;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplit {

    //丟入字串和分割關鍵字，把字串分割成List
    public static List<String> toList(String string, String key){
        List<String> stringList = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(string,key);
        while(stringTokenizer.hasMoreElements()){
            stringList.add(stringTokenizer.nextToken());
        }
        return stringList;
    }

}
