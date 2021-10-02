package com.daxton.fancycore.api.character.conversion;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

public class StringTransform {
	//BossBar顏色
	public static BarColor getBarColor(String inputString, BarColor defaultKey){
		if(inputString == null){
			return defaultKey;
		}
		try {
			return Enum.valueOf(BarColor.class, inputString.toUpperCase());
		}catch (IllegalArgumentException exception){
			return defaultKey;
		}
	}
	//BossBar樣式
	public static BarStyle getBarStyle(String inputString, BarStyle defaultKey){
		if(inputString == null){
			return defaultKey;
		}
		try {
			return Enum.valueOf(BarStyle.class, inputString.toUpperCase());
		}catch (IllegalArgumentException exception){
			return defaultKey;
		}
	}

}
