package com.daxton.fancycore.hook.Vault;

import com.daxton.fancycore.manager.OtherManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Currency {
    //給予目標錢
    public static void giveMoney(Player player, double amount){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || OtherManager.econ == null || amount <= 0){
            return;
        }
        OtherManager.econ.depositPlayer(player, amount);
    }
    //收取目標錢
    public static void receiveMoney(Player player, double amount){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || OtherManager.econ == null || amount <= 0){
            return;
        }
        OtherManager.econ.withdrawPlayer(player, amount);
    }
    //獲取該目標金錢總量
    public static double getMoneyAmount(Player player){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || OtherManager.econ == null){
            return 0;
        }
        return OtherManager.econ.getBalance(player);
    }


}
