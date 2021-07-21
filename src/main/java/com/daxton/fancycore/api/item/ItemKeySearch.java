package com.daxton.fancycore.api.item;

import com.daxton.fancycore.FancyCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
    //獲取自訂屬性Map
    public static Map<String, String> getCustomAttributesMap(ItemStack itemStack){
        Map<String, String> customAttr = new HashMap<>();
        if(itemStack.getType() != Material.AIR){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(itemMeta != null){
                if(!itemStack.getItemMeta().getPersistentDataContainer().isEmpty()){
                    Set<NamespacedKey> k = itemStack.getItemMeta().getPersistentDataContainer().getKeys();
                    k.forEach(namespacedKey -> {
                        String ss = itemStack.getItemMeta().getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
                        if(ss != null){
                            customAttr.put(namespacedKey.getKey(), ss);
                            //FancyItems.fancyItems.getLogger().info(namespacedKey.getKey()+" : "+ss);
                        }
                    });
                }
            }
        }
        return customAttr;
    }

}
