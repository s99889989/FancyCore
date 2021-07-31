package com.daxton.fancycore.api.gui;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class GuiButtomSet {

    //設定物品材質
    public static ItemStack setMaterial(FileConfiguration itemConfig, String itemID){
        ItemStack newItemStack;

        String itemMaterial = itemConfig.getString(itemID+".Material");
        try {
            Material material = Enum.valueOf(Material.class,itemMaterial.replace(" ","").toUpperCase());
            newItemStack = new ItemStack(material);
        }catch (Exception exception){
            newItemStack = new ItemStack(Material.STONE);
        }
        return newItemStack;
    }

    //設定物品顯示名稱
    public static void setDisplayName(LivingEntity self, LivingEntity target, FileConfiguration itemConfig, String itemID, ItemStack newItemStack){
        String itemDisplayName = itemConfig.getString(itemID+".DisplayName");
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setDisplayName(itemDisplayName);
        newItemStack.setItemMeta(itemMeta);
    }

    //設定物品Lore
    public static void setLore(LivingEntity self, LivingEntity target, FileConfiguration itemConfig, String itemID, ItemStack newItemStack){
        List<String> itemDisplayName = itemConfig.getStringList(itemID+".Lore");
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setLore(itemDisplayName);
        newItemStack.setItemMeta(itemMeta);
    }

    //設定物品CustomModelData
    public static void setCustomModelData(FileConfiguration itemConfig, String itemID, ItemStack newItemStack){
        int itemCustomModelData = itemConfig.getInt(itemID+".CustomModelData");
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setCustomModelData(itemCustomModelData);
        newItemStack.setItemMeta(itemMeta);
    }
    //設定物品損壞Data
    public static void setData(FileConfiguration itemConfig, String itemID, ItemStack newItemStack){
        int itemData = itemConfig.getInt(itemID+".Data");
        ItemMeta itemMeta = newItemStack.getItemMeta();
        ((Damageable) itemMeta).setDamage(itemData);
        newItemStack.setItemMeta(itemMeta);
    }

}
