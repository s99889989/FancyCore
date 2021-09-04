package com.daxton.fancycore.api.gui.item;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class GuiItemSet {

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
        itemDisplayName = ConversionMain.valueOf(self, null, itemDisplayName, true);
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setDisplayName(itemDisplayName);
        newItemStack.setItemMeta(itemMeta);
    }

    //設定物品顯示名稱
    public static void setDisplayName(LivingEntity self, LivingEntity target, FileConfiguration itemConfig, String itemID, ItemStack newItemStack, String key, String reKey){
        String itemDisplayName = itemConfig.getString(itemID+".DisplayName");
        itemDisplayName = ConversionMain.valueOf(self, null, itemDisplayName, true);
        if(itemDisplayName.contains(key)){
            itemDisplayName = itemDisplayName.replace(key, reKey);
        }
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setDisplayName(itemDisplayName);
        newItemStack.setItemMeta(itemMeta);
    }

    //設定物品Lore
    public static void setLore(LivingEntity self, LivingEntity target, FileConfiguration itemConfig, String itemID, ItemStack newItemStack){
        List<String> itemLore = itemConfig.getStringList(itemID+".Lore");
        for(int i = 0; i < itemLore.size() ; i++){
            String m = ConversionMain.valueOf(self, null, itemLore.get(i), true);
            itemLore.set(i, m);
        }
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setLore(itemLore);
        newItemStack.setItemMeta(itemMeta);
    }

    //設定物品Lore
    public static void setLore(LivingEntity self, LivingEntity target, FileConfiguration itemConfig, String itemID, ItemStack newItemStack, String key, String reKey){
        List<String> itemLore = itemConfig.getStringList(itemID+".Lore");
        for(int i = 0; i < itemLore.size() ; i++){
            String m = ConversionMain.valueOf(self, null, itemLore.get(i), true);
            if(m.contains(key)){
                m = m.replace(key, reKey);
            }
            itemLore.set(i, m);
        }
        ItemMeta itemMeta = newItemStack.getItemMeta();
        itemMeta.setLore(itemLore);
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
    public static void setHeadValue(LivingEntity self, FileConfiguration itemConfig, String itemID, ItemStack itemStack){
        String headValue = itemConfig.getString(itemID+".HeadValue");
        headValue = ConversionMain.valueOf(self, null, headValue, true);
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
                GameProfile profile = new GameProfile(UUID.randomUUID(), "");
                profile.getProperties().put("textures", new Property("textures", headValue));
                Field profileField;
                try {
                    profileField = skullMeta.getClass().getDeclaredField("profile");
                    profileField.setAccessible(true);
                    profileField.set(skullMeta, profile);
                } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
                    try {
                        PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID(), null);
                        playerProfile.getProperties().add(new ProfileProperty("textures", headValue));
                        skullMeta.setPlayerProfile(playerProfile);
                        itemStack.setItemMeta(skullMeta);
                    } catch (NoSuchMethodError exception) {
                        //
                    }
                }
                itemStack.setItemMeta(skullMeta);

            }

        }
    }

    //設置物品標籤
    public static void setItemFlags(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        ItemFlag itemFlag1 = ItemFlag.HIDE_ATTRIBUTES;
        ItemFlag itemFlag2 = ItemFlag.HIDE_DESTROYS;
        ItemFlag itemFlag3 = ItemFlag.HIDE_DYE;
        ItemFlag itemFlag4 = ItemFlag.HIDE_ENCHANTS;
        ItemFlag itemFlag5 = ItemFlag.HIDE_PLACED_ON;
        ItemFlag itemFlag6 = ItemFlag.HIDE_POTION_EFFECTS;
        ItemFlag itemFlag7 = ItemFlag.HIDE_UNBREAKABLE;
        itemMeta.addItemFlags(itemFlag1, itemFlag2, itemFlag3, itemFlag4, itemFlag5, itemFlag6, itemFlag7);
        itemStack.setItemMeta(itemMeta);

    }

}
