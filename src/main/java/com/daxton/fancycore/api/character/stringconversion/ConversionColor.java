package com.daxton.fancycore.api.character.stringconversion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.md_5.bungee.api.ChatColor.COLOR_CHAR;

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
    //顏色轉換§
    public static String translateHexColorCodes(String startTag, String endTag, String message) {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find()) {
            String group = matcher.group(1);
            //FancyChat.fancyChat.getLogger().info(group);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

}
