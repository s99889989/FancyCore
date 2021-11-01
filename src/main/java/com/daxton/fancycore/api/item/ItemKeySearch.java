package com.daxton.fancycore.api.item;

import com.daxton.fancycore.FancyCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ItemKeySearch {

    //獲取自訂屬性
    public static String getCustomAttributes(ItemStack itemStack, String key){
        if(itemStack == null || itemStack.getType() == Material.AIR || key == null || key.isEmpty()){
            return "";
        }
        String output = "";
        FancyCore fancyCore = FancyCore.fancyCore;

        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemMeta != null){
            String outputString = itemMeta.getPersistentDataContainer().get(new NamespacedKey(fancyCore, key), PersistentDataType.STRING);
            if(outputString != null){
                output = outputString;
            }
        }
        return output;
    }
    //獲取Tag類型值列表
    public static List<String> getTagList(ItemStack itemStack, String key){
        List<String> tagList = new ArrayList<>();
        if(itemStack.getType() != Material.AIR){
            ItemMeta itemMeta = itemStack.getItemMeta();
            NamespacedKey classes_Data = new NamespacedKey(FancyCore.fancyCore, key);

            if(itemMeta.getPersistentDataContainer().has(classes_Data, PersistentDataType.TAG_CONTAINER)){

                PersistentDataContainer newDataContainer = itemMeta.getPersistentDataContainer().get(classes_Data, PersistentDataType.TAG_CONTAINER);

                newDataContainer.getKeys().forEach(namespacedKey -> {
                    if(newDataContainer.has(namespacedKey, PersistentDataType.STRING)){
                        String ss =  newDataContainer.get(namespacedKey, PersistentDataType.STRING);
                        tagList.add(ss);
                    }
                });

            }
        }
        return tagList;
    }
    //獲取Tag類型Map
    public static Map<String, String> getTagMap(ItemStack itemStack, String searchKey){
        Map<String, String> tagMap = new HashMap<>();
        if(itemStack.getType() != Material.AIR){
            ItemMeta itemMeta = itemStack.getItemMeta();
            NamespacedKey classes_Data = new NamespacedKey(FancyCore.fancyCore, searchKey);

            if(itemMeta.getPersistentDataContainer().has(classes_Data, PersistentDataType.TAG_CONTAINER)){

                PersistentDataContainer newDataContainer = itemMeta.getPersistentDataContainer().get(classes_Data, PersistentDataType.TAG_CONTAINER);

                newDataContainer.getKeys().forEach(namespacedKey -> {
                    if(newDataContainer.has(namespacedKey, PersistentDataType.STRING)){
                        String key = namespacedKey.getKey();
                        String ss =  newDataContainer.get(namespacedKey, PersistentDataType.STRING);
                        tagMap.put(key, ss);
                    }
                });

            }
        }
        return tagMap;
    }
    //獲取自訂屬性Map
    public static Map<String, String> getCustomAttributesMap(ItemStack itemStack){
        Map<String, String> customAttr = new HashMap<>();
        if(itemStack.getType() != Material.AIR){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(!itemMeta.getPersistentDataContainer().isEmpty()){
                Set<NamespacedKey> k = itemStack.getItemMeta().getPersistentDataContainer().getKeys();
                k.forEach(namespacedKey -> {
                    if(itemMeta.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING)){
                        String ss = itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
                        customAttr.put(namespacedKey.getKey(), ss);
                        //FancyItems.fancyItems.getLogger().info(namespacedKey.getKey()+" : "+ss);
                    }
                });
            }

        }
        return customAttr;
    }

}
