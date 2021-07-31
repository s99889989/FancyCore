package com.daxton.fancycore.api.other;

import java.util.List;

public class StringFind {

    /**丟入整個動作 返回動作第一個關鍵字**/
    public static String getAction(String string){
        String lastString = "";
        List<String> stringList = StringSplit.toList(string,"[;] ");
        if(stringList.size() > 0){
            String[] strings = stringList.toArray(new String[stringList.size()]);
            lastString = strings[0].replace(" ","");
        }
        return lastString;
    }

    /**丟入整個自訂字 返回自訂字內容**/
    public static String getContent(String string){
        String lastString = "";
        List<String> stringList = StringSplit.toList(string,"[]");
        if(stringList.size() > 1){
            String[] strings = stringList.toArray(new String[stringList.size()]);
            lastString = strings[1];
        }
        return lastString;
    }

    /**丟入整個自訂字 返回自訂字目標**/
    public static String getTarget(String string){
        String lastString = "";
        List<String> stringList = StringSplit.toList(string,"[]");
        if(stringList.size() == 3){
            String[] strings = stringList.toArray(new String[stringList.size()]);
            lastString = strings[2].replace(" ","");
        }
        return lastString;
    }

}
