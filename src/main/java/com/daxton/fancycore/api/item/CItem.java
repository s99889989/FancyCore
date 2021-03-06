package com.daxton.fancycore.api.item;

import com.daxton.fancycore.FancyCore;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CItem {

    private ItemStack itemStack;
    //給出材質創造物品
    public CItem(String material){
        try {
            Material itemMaterial = Enum.valueOf(Material.class, material.replace(" ","").toUpperCase());
            itemStack = new ItemStack(itemMaterial);
        }catch (Exception exception){
            itemStack = new ItemStack(Material.STONE);
        }
    }
    //設置物品堆疊數量
    public void setAmount(int amount){
        itemStack.setAmount(amount);
    }
    //設定物品顯示名稱
    public void setDisplayName(String displayName){
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品Lore
    public void setLore(List<String> loreList){
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> newLoreList = new ArrayList<>();

        if(itemMeta != null && itemMeta.getLore() != null){
            newLoreList = itemMeta.getLore();
        }
        if(loreList != null){
            newLoreList.addAll(loreList);
        }
        if(itemMeta != null){
            itemMeta.setLore(newLoreList);
            itemStack.setItemMeta(itemMeta);
        }
    }
    //設定職業需求
    public void setNeedClasses(List<String> classList){
        if(classList.isEmpty()){
            return;
        }
        FancyCore fancyCore = FancyCore.fancyCore;

        NamespacedKey MAIN_DATA = new NamespacedKey(fancyCore, "needclasses");

        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();

        PersistentDataContainer newDataContainer = dataContainer.getAdapterContext().newPersistentDataContainer();

        for(int i = 0; i < classList.size() ; i++){
            String value = classList.get(i);
            newDataContainer.set(new NamespacedKey(fancyCore, String.valueOf(i)), PersistentDataType.STRING, value);
        }

        dataContainer.set(MAIN_DATA, PersistentDataType.TAG_CONTAINER, newDataContainer);

        itemStack.setItemMeta(itemMeta);

    }
    //設定職業等級需求
    public void setNeedLevel(Map<String, String> levelMap){
        if(levelMap.isEmpty()){
            return;
        }
        FancyCore fancyCore = FancyCore.fancyCore;

        NamespacedKey MAIN_DATA = new NamespacedKey(fancyCore, "needlevel");

        ItemMeta itemMeta = itemStack.getItemMeta();

        PersistentDataContainer dataContainer = itemMeta.getPersistentDataContainer();

        PersistentDataContainer newDataContainer = dataContainer.getAdapterContext().newPersistentDataContainer();

        levelMap.forEach((s, s2) -> {
            newDataContainer.set(new NamespacedKey(fancyCore, s), PersistentDataType.STRING, s2);
        });

        dataContainer.set(MAIN_DATA, PersistentDataType.TAG_CONTAINER, newDataContainer);

        itemStack.setItemMeta(itemMeta);

    }
    //設置Lore
    public void setLore(List<String> loreList, boolean head){
        List<String> oldLore = itemStack.getLore();
        List<String> newLoreList = new ArrayList<>();
        if(oldLore != null){
            if(head){
                newLoreList.addAll(loreList);
                newLoreList.addAll(oldLore);
            }else {
                newLoreList.addAll(oldLore);
                newLoreList.addAll(loreList);
            }
        }else {
            newLoreList.addAll(loreList);
        }
        itemStack.setLore(newLoreList);
    }
    //設定物品損壞Data
    public void setData(int data){
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        ((Damageable) itemMeta).setDamage(data);
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品CustomModelData
    public void setCustomModelData(int customModelData){
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setCustomModelData(customModelData);
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品附魔
    public void setEnchantments(String enchantments, int level){
        ItemMeta itemMeta = itemStack.getItemMeta();
        //FancyCore.fancyCore.getLogger().info("附魔V ");
        try {
            //NamespacedKey key = NamespacedKey.minecraft(enchantments);
            //Enchantment enchant = Enchantment.getByKey(key);
            Enchantment enchant = Enchantment.getByName(enchantments);
            //FancyCore.fancyCore.getLogger().info("附魔: "+enchant.getName());
            if(enchant != null){
                assert itemMeta != null;
                itemMeta.addEnchant(enchant, level,false);
            }
        }catch (Exception exception){
            //
        }
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品原生屬性
    public void setAttributes(String equipmentSlot, String inherit, String operation, double attrAmount){
        ItemMeta itemMeta = itemStack.getItemMeta();

        try {
            if(inherit != null && operation != null && attrAmount != 0 && equipmentSlot != null){
                Attribute attribute = Enum.valueOf(Attribute.class,inherit.toUpperCase());
                AttributeModifier.Operation operation1 = Enum.valueOf(AttributeModifier.Operation.class,operation);
                EquipmentSlot equipmentSlot1 = Enum.valueOf(EquipmentSlot.class,equipmentSlot.toUpperCase());
                assert itemMeta != null;
                if(equipmentSlot.toLowerCase().contains("all")) {
                    itemMeta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), String.valueOf(UUID.randomUUID()), attrAmount, operation1));
                }else {
                    itemMeta.addAttributeModifier(attribute, new AttributeModifier(UUID.randomUUID(), String.valueOf(UUID.randomUUID()), attrAmount, operation1, equipmentSlot1));
                }
            }
        }catch (Exception exception){
            //
        }
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品不會損壞
    public void setUnbreakable(boolean unbreakable){
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setUnbreakable(unbreakable);
        itemStack.setItemMeta(itemMeta);
    }
    //設置物品標籤
    public void setItemFlags(String flags, boolean enable){

        if(enable){
            try {
                ItemMeta itemMeta = itemStack.getItemMeta();
                ItemFlag itemFlag = Enum.valueOf(ItemFlag.class, flags);
                assert itemMeta != null;
                itemMeta.addItemFlags(itemFlag);
                itemStack.setItemMeta(itemMeta);
            }catch (Exception exception){
                //
            }
        }

    }
    //設定物品的動作
    public void setAction(List<String> actionList){
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;

        List<String> itemLore = new ArrayList<>();
        assert itemMeta != null;
        if(itemMeta.getLore() != null){
            itemLore = itemMeta.getLore();
        }

        if(!actionList.isEmpty()){
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();
            int i = 0;
            for(String action : actionList){
                i++;
                NamespacedKey xd = new NamespacedKey(fancyCore, "Action"+i);
                String[] actionArray = action.split(":");
                if(actionArray.length == 2){
                    data.set(xd , PersistentDataType.STRING, actionArray[1]);
                    if(!actionArray[0].equals("null")){
                        itemLore.add(actionArray[0]);
                        itemMeta.setLore(itemLore);
                    }
                }
            }
        }
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品的頭值
    public void setHeadValue(String headValue){
        if(headValue == null){
            return;
        }
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
    //設定物品的右鍵CD
    public void setCoolDownRightClick(String coolDown){
        if(coolDown == null){
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;
        assert itemMeta != null;
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        NamespacedKey xd = new NamespacedKey(fancyCore, "CoolDownRightClick");
        data.set(xd , PersistentDataType.STRING, coolDown);
        itemStack.setItemMeta(itemMeta);
    }
    //設定物品的左鍵CD
    public void setCoolDownLeftClick(String coolDown){
        if(coolDown == null){
            return;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;
        assert itemMeta != null;
        PersistentDataContainer data = itemMeta.getPersistentDataContainer();
        NamespacedKey xd = new NamespacedKey(fancyCore, "CoolDownLeftClick");
        data.set(xd , PersistentDataType.STRING, coolDown);
        itemStack.setItemMeta(itemMeta);
    }
    public void setDisableAttack(boolean enable){
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;
        assert itemMeta != null;
        if(enable){
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();
            NamespacedKey xd = new NamespacedKey(fancyCore, "DisableAttack");
            data.set(xd , PersistentDataType.STRING, "true");
        }
        itemStack.setItemMeta(itemMeta);
    }
    public void setCustomAttrs(String attributes, String value){
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;
        assert itemMeta != null;
        try {
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();
            NamespacedKey xd = new NamespacedKey(fancyCore, attributes);
            data.set(xd , PersistentDataType.STRING, value);
        }catch (Exception exception){
            //exception.printStackTrace();
        }
        itemStack.setItemMeta(itemMeta);
    }

    //設定物品的ID
    public void setID(String id){
        ItemMeta itemMeta = itemStack.getItemMeta();
        FancyCore fancyCore = FancyCore.fancyCore;
        assert itemMeta != null;
        try {
            PersistentDataContainer data = itemMeta.getPersistentDataContainer();
            NamespacedKey xd = new NamespacedKey(fancyCore, "itemID");
            data.set(xd , PersistentDataType.STRING, id);
        }catch (Exception exception){
            //exception.printStackTrace();
        }
        itemStack.setItemMeta(itemMeta);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
