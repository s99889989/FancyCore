package com.daxton.fancycore.api.other;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountWords {

    //計算指定單字出現次數
    public static int count(String srcText, String findText) {
        int count = 0;
        Pattern p = Pattern.compile(findText);
        Matcher m = p.matcher(srcText);
        while (m.find()) {
            count++;
        }
        return count;
    }

}
