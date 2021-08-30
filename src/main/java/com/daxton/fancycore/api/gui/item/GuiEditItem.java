package com.daxton.fancycore.api.gui.item;

import com.daxton.fancycore.nms.NMSItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GuiEditItem {

	//獲取損壞值
	public static int getData(ItemStack itemStack){
		ItemMeta itemMeta = itemStack.getItemMeta();
		Damageable damageable = (Damageable) itemMeta;
		return damageable.getDamage();
	}
	//獲取屬性清單
	public static List<String> attrGet(ItemStack itemStack){
		List<String> stringList = new ArrayList<>();
		ItemMeta itemMeta = itemStack.getItemMeta();
		if(itemMeta.getAttributeModifiers() != null){
			itemMeta.getAttributeModifiers().forEach((attribute, attributeModifier) -> {
				String inherit = attribute.name();
				String operation = attributeModifier.getOperation().name();
				double addNumber = attributeModifier.getAmount();
				stringList.add("§f"+inherit+":"+operation+":"+addNumber);
			});
		}
		return stringList;
	}
	//把List插入Lore後面
	public static void loreTail(ItemStack itemStack, List<String> list){
		List<String> oldLore = itemStack.getLore();
		if(oldLore != null){
			oldLore.addAll(list);
			itemStack.setLore(oldLore);
		}else {
			itemStack.setLore(list);
		}
	}
	//把List插入標記位置
	public static void loreInsert(ItemStack itemStack, String key, List<String> list){
		List<String> newLoreList = new ArrayList<>();
		itemStack.getLore().forEach(s -> {
			if(s.contains(key)){
				newLoreList.addAll(list);
			}else {
				newLoreList.add(s);
			}
		});
		itemStack.setLore(newLoreList);
	}

	public static List<String> getPersistentData(ItemStack itemStack){
		List<String> arrayList = new ArrayList<>();
		PersistentDataContainer pData = itemStack.getItemMeta().getPersistentDataContainer();
		Set<NamespacedKey> k = pData.getKeys();
		k.forEach(nameSpacedKey -> {
			String ss = itemStack.getItemMeta().getPersistentDataContainer().get(nameSpacedKey, PersistentDataType.STRING);
			arrayList.add(nameSpacedKey.getKey()+" : "+ss);
			//FancyCore.fancyCore.getLogger().info(nameSpacedKey.getKey()+" : "+ss);
		});

		return arrayList;
	}

	public static String mmoItemType(ItemStack itemStack){
		return NMSItem.getNBTTagString(itemStack, "MMOITEMS_ITEM_TYPE");
	}

}
