package com.daxton.fancycore.api.conversion;

import com.daxton.fancycore.api.judgment.NumberJudgment;
import net.md_5.bungee.api.ChatColor;

public class ColorConversion {

    //把16進位代碼轉成顏色
    public static ChatColor hexToColor(String in) {
        if (in != null) {
            switch (in.toUpperCase()) {
                case "0":
                    return ChatColor.BLACK;
                case "1":
                    return ChatColor.DARK_BLUE;
                case "2":
                    return ChatColor.DARK_GREEN;
                case "3":
                    return ChatColor.DARK_AQUA;
                case "4":
                    return ChatColor.DARK_RED;
                case "5":
                    return ChatColor.DARK_PURPLE;
                case "6":
                    return ChatColor.GOLD;
                case "7":
                    return ChatColor.GRAY;
                case "8":
                    return ChatColor.DARK_GRAY;
                case "9":
                    return ChatColor.BLUE;
                case "A":
                    return ChatColor.GREEN;
                case "B":
                    return ChatColor.AQUA;
                case "C":
                    return ChatColor.RED;
                case "D":
                    return ChatColor.LIGHT_PURPLE;
                case "E":
                    return ChatColor.YELLOW;
                default:
                    return ChatColor.WHITE;
            }
        }
        return ChatColor.WHITE;
    }


}
