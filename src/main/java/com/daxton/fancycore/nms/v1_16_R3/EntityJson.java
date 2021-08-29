package com.daxton.fancycore.nms.v1_16_R3;

import com.daxton.fancycore.FancyCore;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.EntityTypes;
import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class EntityJson {

    public static BaseComponent[] to(Entity entity){
        net.minecraft.server.v1_16_R3.Entity nmsEntity = ((CraftEntity) entity).getHandle();
        NBTTagCompound nbt = new NBTTagCompound();
        nmsEntity.save(nbt);
        nmsEntity.getEntityType();

        String json = nbt.toString();
        FancyCore.fancyCore.getLogger().info(json);
        BaseComponent[] hoverEventComponents = new BaseComponent[]{new TextComponent(json)};
        return hoverEventComponents;
    }

}
