package com.daxton.fancycore.api.gui;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GuiButtom {

    public static ItemStack valueOf(FileConfiguration itemConfig, String itemID){
        ItemStack newItemStack = new ItemStack(Material.STONE);
        if(itemConfig.contains(itemID+".Material")){
            //設定物品材質
            newItemStack = GuiButtomSet.setMaterial(itemConfig, itemID);
        }
        //設定物品顯示名稱
        if(itemConfig.contains(itemID+".DisplayName")){
            GuiButtomSet.setDisplayName(null, null, itemConfig, itemID, newItemStack);
        }
        //設定物品Lore
        if(itemConfig.contains(itemID+".Lore")){
            GuiButtomSet.setLore(null, null, itemConfig, itemID, newItemStack);
        }
        //設定物品損壞Data
        if(itemConfig.contains(itemID+".Data")){
            GuiButtomSet.setData(itemConfig, itemID, newItemStack);
        }
        //設定物品CustomModelData
        if(itemConfig.contains(itemID+".CustomModelData")){
            GuiButtomSet.setCustomModelData(itemConfig, itemID, newItemStack);
        }
        //設定物品的頭值
        if(itemConfig.contains(itemID+".HeadValue")){
            GuiButtomSet.setHeadValue(null, itemConfig, itemID, newItemStack);
        }
        return newItemStack;
    }
    public static ItemStack valueOf(Player player, FileConfiguration itemConfig, String itemID){
        ItemStack newItemStack = new ItemStack(Material.STONE);
        if(itemConfig.contains(itemID+".Material")){
            //設定物品材質
            newItemStack = GuiButtomSet.setMaterial(itemConfig, itemID);
        }
        //設定物品顯示名稱
        if(itemConfig.contains(itemID+".DisplayName")){
            GuiButtomSet.setDisplayName(player, null, itemConfig, itemID, newItemStack);
        }
        //設定物品Lore
        if(itemConfig.contains(itemID+".Lore")){
            GuiButtomSet.setLore(player, null, itemConfig, itemID, newItemStack);
        }
        //設定物品損壞Data
        if(itemConfig.contains(itemID+".Data")){
            GuiButtomSet.setData(itemConfig, itemID, newItemStack);
        }
        //設定物品CustomModelData
        if(itemConfig.contains(itemID+".CustomModelData")){
            GuiButtomSet.setCustomModelData(itemConfig, itemID, newItemStack);
        }
        //設定物品的頭值
        if(itemConfig.contains(itemID+".HeadValue")){
            GuiButtomSet.setHeadValue(player, itemConfig, itemID, newItemStack);
        }
        return newItemStack;
    }


}
