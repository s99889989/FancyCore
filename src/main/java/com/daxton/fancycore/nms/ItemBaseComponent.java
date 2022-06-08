package com.daxton.fancycore.nms;

import com.daxton.fancycore.FancyCore;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.ItemStack;

public class ItemBaseComponent {

    public static BaseComponent[] to(ItemStack itemStack){
        String nmsVersion = NMSVersion.getNMSVersion();
        switch (nmsVersion){
            case "v1_13_R1":
                return com.daxton.fancycore.nms.v1_13_R1.ItemToBaseComponent.to(itemStack);
            case "v1_13_R2":
                return com.daxton.fancycore.nms.v1_13_R2.ItemToBaseComponent.to(itemStack);
            case "v1_14_R1":
                return com.daxton.fancycore.nms.v1_14_R1.ItemToBaseComponent.to(itemStack);
            case "v1_15_R1":
                return com.daxton.fancycore.nms.v1_15_R1.ItemToBaseComponent.to(itemStack);
            case "v1_16_R1":
                return com.daxton.fancycore.nms.v1_16_R1.ItemToBaseComponent.to(itemStack);
            case "v1_16_R2":
                return com.daxton.fancycore.nms.v1_16_R2.ItemToBaseComponent.to(itemStack);
            case "v1_16_R3":
                return com.daxton.fancycore.nms.v1_16_R3.ItemToBaseComponent.to(itemStack);
            case "v1_17_R1":
                return com.daxton.fancycore.nms.v1_17_R1.ItemToBaseComponent.to(itemStack);
            case "v1_18_R1":
                return com.daxton.fancycore.nms.v1_18_R1.ItemToBaseComponent.to(itemStack);
            case "v1_18_R2":
                return com.daxton.fancycore.nms.v1_18_R2.ItemToBaseComponent.to(itemStack);
        }
        return null;
    }

}
