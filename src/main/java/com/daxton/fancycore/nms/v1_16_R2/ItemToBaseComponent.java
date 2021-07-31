package com.daxton.fancycore.nms.v1_16_R2;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemToBaseComponent {

    public static BaseComponent[] to(ItemStack itemStack){
        net.minecraft.server.v1_16_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        nmsItemStack.save(compound);
        String json = compound.toString();
        BaseComponent[] hoverEventComponents = new BaseComponent[]{new TextComponent(json)};
        return hoverEventComponents;
    }

}
