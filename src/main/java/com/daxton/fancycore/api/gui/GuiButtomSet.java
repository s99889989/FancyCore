package com.daxton.fancycore.api.gui;

import com.daxton.fancycore.FancyCore;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.UUID;

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
    //設定物品的頭值
    public static void setHeadValue(FileConfiguration itemConfig, String itemID, ItemStack itemStack){
        String headValue = itemConfig.getString(itemID+".HeadValue");
        FancyCore fancyCore = FancyCore.fancyCore;
        Material material = itemStack.getType();
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        if(material == Material.PLAYER_HEAD){
            if(headValue.length() < 50){
                OfflinePlayer targetPlayer = fancyCore.getServer().getOfflinePlayer(headValue);
                assert skullMeta != null;
                skullMeta.setOwningPlayer(targetPlayer);
                itemStack.setItemMeta(skullMeta);
            }else {
                try {
                    PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID(), null);
                    playerProfile.getProperties().add(new ProfileProperty("textures", headValue));
                    skullMeta.setPlayerProfile(playerProfile);
                    itemStack.setItemMeta(skullMeta);
                } catch (Exception exception) {
                    fancyCore.getLogger().info("頭的值只能在paper伺服器使用。");
                    fancyCore.getLogger().info("The value of the header can only be used on the paper server.");
                }
            }

        }
        itemStack.setItemMeta(skullMeta);
    }
}
