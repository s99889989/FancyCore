package com.daxton.fancycore.nms.v1_13_R2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.lang.reflect.Method;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.server.v1_13_R2.MojangsonParser;
import net.minecraft.server.v1_13_R2.NBTTagCompound;

import net.minecraft.server.v1_13_R2.NBTBase;
import net.minecraft.server.v1_13_R2.NBTCompressedStreamTools;
import net.minecraft.server.v1_13_R2.NBTReadLimiter;
import net.minecraft.server.v1_13_R2.NBTTagList;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

public class NMSItem {

	public static Method Write_NBT;
	public static Method Read_NBT;

	//把Json字串轉成NBT再轉成物品
	public static ItemStack jsonStringToItemStack(@NotNull String itemString){
		try {
			net.minecraft.server.v1_13_R2.NBTTagCompound compound = MojangsonParser.parse(itemString);
			net.minecraft.server.v1_13_R2.ItemStack nmsItemStack = net.minecraft.server.v1_13_R2.ItemStack.a(compound);
			return org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack.asBukkitCopy(nmsItemStack);
		} catch (CommandSyntaxException exception) {
			exception.printStackTrace();
		}
		return new ItemStack(Material.STONE);
	}

	//把物品NBT轉成String
	public static String itemNBTtoString(@NotNull ItemStack itemStack){
		net.minecraft.server.v1_13_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = new NBTTagCompound();
		nmsItemStack.save(compound);
		return compound.toString();
	}

	//從物品獲得NBT並轉成String
	public static String getNBTTagString(ItemStack itemStack, String key){
		net.minecraft.server.v1_13_R2.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound compound = new NBTTagCompound();
		nmsItemStack.save(compound);
		NBTTagCompound compound2 = compound.getCompound("tag");
		return compound2.getString(key);
	}

	//把物品轉成Base64字串
	public static String itemStackToBase64(ItemStack[] itemStacks) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
		NBTTagList nbtTagList = new NBTTagList();

		for (ItemStack itemStack : itemStacks) {
			CraftItemStack craftItemStack = getCraftVersion(itemStack);
			NBTTagCompound nbtTagCompound = new NBTTagCompound();
			if (craftItemStack != null) {
				try {
					CraftItemStack.asNMSCopy(craftItemStack).save(nbtTagCompound);
				} catch (NullPointerException exception) {
					//
				}
			}

			nbtTagList.add(nbtTagCompound);
		}

		if (Write_NBT == null) {
			try {
				Write_NBT = NBTCompressedStreamTools.class.getDeclaredMethod("a", NBTBase.class, DataOutput.class);
				Write_NBT.setAccessible(true);
			} catch (Exception exception) {
				throw new IllegalStateException("無法找到私有寫入方法。", exception);
			}
		}

		try {
			Write_NBT.invoke(null, nbtTagList, dataOutputStream);
		} catch (Exception exception) {
			throw new IllegalArgumentException("無法寫 " + nbtTagList + " 到 " + dataOutputStream, exception);
		}

		return Base64Coder.encodeLines(byteArrayOutputStream.toByteArray());
	}

	private static CraftItemStack getCraftVersion(ItemStack itemStack) {
		if (itemStack instanceof CraftItemStack) {
			return (CraftItemStack)itemStack;
		} else if (itemStack != null) {
			return CraftItemStack.asCraftCopy(itemStack);
		} else {
			return null;
		}
	}
	//把Base64字串轉成物品
	public static ItemStack[] base64toItemStack(String itemString) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(itemString));
		NBTTagList nbtTagList = (NBTTagList)readNbt(new DataInputStream(byteArrayInputStream));
		ItemStack[] itemStacks = new ItemStack[nbtTagList.size()];

		for(int i = 0; i < nbtTagList.size(); ++i) {
			NBTTagCompound nbtTagCompound = (NBTTagCompound)nbtTagList.get(i);
			if (!nbtTagCompound.isEmpty()) {
				itemStacks[i] = CraftItemStack.asCraftMirror(net.minecraft.server.v1_13_R2.ItemStack.a(nbtTagCompound));
			}
		}

		return itemStacks;
	}

	private static NBTBase readNbt(DataInput dataInput) {
		if (Read_NBT == null) {
			try {
				Read_NBT = NBTCompressedStreamTools.class.getDeclaredMethod("a", DataInput.class, Integer.TYPE, NBTReadLimiter.class);
				Read_NBT.setAccessible(true);
			} catch (Exception exception) {
				throw new IllegalStateException("無法找到私有讀取方法。.", exception);
			}
		}

		try {
			return (NBTBase) Read_NBT.invoke(null, dataInput, 0, new NBTReadLimiter(9223372036854775807L));
		} catch (Exception exception) {
			throw new IllegalArgumentException("無法讀取 " + dataInput, exception);
		}
	}

}
