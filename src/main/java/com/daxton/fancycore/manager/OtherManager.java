package com.daxton.fancycore.manager;

import discord4j.core.GatewayDiscordClient;
import net.milkbowl.vault.economy.Economy;

import java.util.HashMap;
import java.util.Map;

public class OtherManager {
    //經濟
    public static Economy econ;
    //Discord連線
    public static GatewayDiscordClient client;
    //自訂值轉譯
    public static Map<String, String> custom_Value = new HashMap<>();
    //自訂值預設值
    public static Map<String, String> custom_Value_Default = new HashMap<>();

}
