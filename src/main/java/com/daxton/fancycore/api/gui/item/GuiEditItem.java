package com.daxton.fancycore.api.gui.item;

import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.daxton.fancycore.nms.NMSItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GuiEditItem {
	//設置材質
	public static ItemStack setMaterial(ItemStack itemStack, String stringMaterial){
		try {
			ItemMeta itemMeta = itemStack.getItemMeta();
			Material itemMaterial = Enum.valueOf(Material.class, stringMaterial.replace(" ","").toUpperCase());
			ItemStack newItemStack = new ItemStack(itemMaterial);
			newItemStack.setItemMeta(itemMeta);

			return newItemStack;

		}catch (Exception exception){
			//
		}
		return itemStack;
	}
	//設置CMD值
	public static void setCustomModelData(ItemStack itemStack, int cmd){
		ItemMeta itemMeta = itemStack.getItemMeta();
		itemMeta.setCustomModelData(cmd);
		itemStack.setItemMeta(itemMeta);
	}

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
		if(list != null){
			if(oldLore != null){
				oldLore.addAll(list);
				itemStack.setLore(oldLore);
			}else {
				itemStack.setLore(list);
			}
		}

	}
	//替換掉lore裡面的指定值
	public static void loreReplace(ItemStack itemStack, String key, String replace){
		List<String> oldLore = itemStack.getLore();
		List<String> newLore = new ArrayList<>();
		if(oldLore != null){
			oldLore.forEach(s -> {
				String rs = s.replace(key, replace);
				newLore.add(rs);
			});
			itemStack.setLore(newLore);
		}
	}

	//把List插入標記位置
	public static void loreInsert(ItemStack itemStack, String key, List<String> list){
		List<String> newLoreList = new ArrayList<>();
		if(itemStack.getLore() != null && list != null){
			itemStack.getLore().forEach(s -> {
				if(s.contains(key)){
					newLoreList.addAll(list);
				}else {
					newLoreList.add(s);
				}
			});
		}
		itemStack.setLore(newLoreList);
	}

	//把List插入標記位置
	public static void loreInsert(LivingEntity self, ItemStack itemStack, String key, List<String> list){
		List<String> newLoreList = new ArrayList<>();
		if(itemStack.getLore() != null && list != null){
			itemStack.getLore().forEach(s -> {
				if(s.contains(key)){
					for(String addString : list){
						addString = ConversionMain.valueOf(self, null, addString, true);
						newLoreList.add(addString);
					}
				}else {
					newLoreList.add(s);
				}
			});
		}
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
	//轉換Name內容
	public static void replaceName(ItemStack itemStack, Map<String, String> nameMap){
		ItemMeta itemMeta =  itemStack.getItemMeta();
		String itemDisplayName = itemMeta.getDisplayName();

		for(String key : nameMap.keySet()){
			String value = nameMap.get(key);
			if(itemDisplayName.contains(key)){
				itemDisplayName = itemDisplayName.replace(key, value);
			}
		}

		itemMeta.setDisplayName(itemDisplayName);

		itemStack.setItemMeta(itemMeta);
	}
	//轉換Lore內容
	public static void replaceLore(ItemStack itemStack, Map<String, String> loreMap){
		List<String> oldLore = itemStack.getLore();
		if(oldLore == null){
			return;
		}
		List<String> newLore = new ArrayList<>();

		oldLore.forEach(s -> {
			int i = 1;
			for(String key : loreMap.keySet()){
				String value = loreMap.get(key);
				if(s.contains(key)){
					s = s.replace(key, value);
				}
				if(i == loreMap.keySet().size()){
					newLore.add(s);
					break;
				}
				i++;
			}
		});
		itemStack.setLore(newLore);
	}

	public static String mmoItemType(ItemStack itemStack){
		return NMSItem.getNBTTagString(itemStack, "MMOITEMS_ITEM_TYPE");
	}

}
