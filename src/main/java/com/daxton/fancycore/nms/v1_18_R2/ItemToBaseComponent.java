package com.daxton.fancycore.nms.v1_18_R2;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.inventory.ItemStack;

public class ItemToBaseComponent {

    //把物品轉成聊天格式
    public static BaseComponent[] to(ItemStack itemStack){
        return new BaseComponent[]{new TextComponent("")};
    }

}
