package com.daxton.fancycore.api.task;

import com.daxton.fancycore.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Currency {

    public static void giveMoney(Player player, int amount){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || Manager.econ == null || amount < 0){
            return;
        }
        Manager.econ.depositPlayer(player, amount);
    }

    public static void receiveMoney(Player player, int amount){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || Manager.econ == null || amount < 0){
            return;
        }
        Manager.econ.withdrawPlayer(player, amount);
    }

    public static double getMoney(Player player){
        if(Bukkit.getServer().getPluginManager().getPlugin("Vault") == null || Manager.econ == null){
            return 0;
        }
        return Manager.econ.getBalance(player);
    }


}
