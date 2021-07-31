package com.daxton.fancycore.nms.v1_16_R3;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.config.FileConfig;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemToBaseComponent {

    public static BaseComponent[] to(ItemStack itemStack){

        net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound compound = new NBTTagCompound();
        nmsItemStack.save(compound);
        String json = compound.toString();
        //json = "{tag:{CustomModelData:1,PublicBukkitValues:{\"fancycore:itemid\":\"FishingRod.HandmadeFishingRod\",\"fancycore:fishingrodstypes\":\"手工\",\"fancycore:tooltype\":\"釣竿\"},display:{Name:'{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"yellow\",\"text\":\"Handmade fishing rod\"}],\"text\":\"\"}',Lore:['{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"工具類型 : 釣竿\"}],\"text\":\"\"}','{\"extra\":[{\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"color\":\"gray\",\"text\":\"釣竿種類 : 手工\"}],\"text\":\"\"}']}}}";
        //FancyCore.fancyCore.getLogger().info(json);
        BaseComponent[] hoverEventComponents = new BaseComponent[]{new TextComponent(json)};
        return hoverEventComponents;
    }

}
