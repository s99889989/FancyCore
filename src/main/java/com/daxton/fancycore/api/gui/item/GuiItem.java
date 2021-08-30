package com.daxton.fancycore.api.gui.item;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GuiItem {
    //基礎按鈕
    public static ItemStack valueOf(FileConfiguration itemConfig, String itemID){
        ItemStack newItemStack = new ItemStack(Material.STONE);
        if(itemConfig.contains(itemID+".Material")){
            //設定物品材質
            newItemStack = GuiItemSet.setMaterial(itemConfig, itemID);
        }
        //設定物品顯示名稱
        if(itemConfig.contains(itemID+".DisplayName")){
            GuiItemSet.setDisplayName(null, null, itemConfig, itemID, newItemStack);
        }
        //設定物品Lore
        if(itemConfig.contains(itemID+".Lore")){
            GuiItemSet.setLore(null, null, itemConfig, itemID, newItemStack);
        }
        //設定物品損壞Data
        if(itemConfig.contains(itemID+".Data")){
            GuiItemSet.setData(itemConfig, itemID, newItemStack);
        }
        //設定物品CustomModelData
        if(itemConfig.contains(itemID+".CustomModelData")){
            GuiItemSet.setCustomModelData(itemConfig, itemID, newItemStack);
        }
        //設定物品的頭值
        if(itemConfig.contains(itemID+".HeadValue")){
            GuiItemSet.setHeadValue(null, itemConfig, itemID, newItemStack);
        }
        return newItemStack;
    }

    public static ItemStack valueOf(Player player, FileConfiguration itemConfig, String itemID, String key, String reKey){
        ItemStack newItemStack = new ItemStack(Material.STONE);
        if(itemConfig.contains(itemID+".Material")){
            //設定物品材質
            newItemStack = GuiItemSet.setMaterial(itemConfig, itemID);
        }
        //設定物品顯示名稱
        if(itemConfig.contains(itemID+".DisplayName")){
            GuiItemSet.setDisplayName(player, null, itemConfig, itemID, newItemStack, key, reKey);
        }
        //設定物品Lore
        if(itemConfig.contains(itemID+".Lore")){
            GuiItemSet.setLore(player, null, itemConfig, itemID, newItemStack, key, reKey);

        }
        //設定物品損壞Data
        if(itemConfig.contains(itemID+".Data")){
            GuiItemSet.setData(itemConfig, itemID, newItemStack);
        }
        //設定物品CustomModelData
        if(itemConfig.contains(itemID+".CustomModelData")){
            GuiItemSet.setCustomModelData(itemConfig, itemID, newItemStack);
        }
        //設定物品的頭值
        if(itemConfig.contains(itemID+".HeadValue")){
            GuiItemSet.setHeadValue(null, itemConfig, itemID, newItemStack);
        }
        return newItemStack;
    }
    //有玩家的按鈕
    public static ItemStack valueOf(Player player, FileConfiguration itemConfig, String itemID){
        ItemStack newItemStack = new ItemStack(Material.STONE);
        if(itemConfig.contains(itemID+".Material")){
            //設定物品材質
            newItemStack = GuiItemSet.setMaterial(itemConfig, itemID);
        }
        //設定物品顯示名稱
        if(itemConfig.contains(itemID+".DisplayName")){
            GuiItemSet.setDisplayName(player, null, itemConfig, itemID, newItemStack);
        }
        //設定物品Lore
        if(itemConfig.contains(itemID+".Lore")){
            GuiItemSet.setLore(player, null, itemConfig, itemID, newItemStack);
        }
        //設定物品損壞Data
        if(itemConfig.contains(itemID+".Data")){
            GuiItemSet.setData(itemConfig, itemID, newItemStack);
        }
        //設定物品CustomModelData
        if(itemConfig.contains(itemID+".CustomModelData")){
            GuiItemSet.setCustomModelData(itemConfig, itemID, newItemStack);
        }
        //設定物品的頭值
        if(itemConfig.contains(itemID+".HeadValue")){
            GuiItemSet.setHeadValue(player, itemConfig, itemID, newItemStack);
        }
        return newItemStack;
    }


}
