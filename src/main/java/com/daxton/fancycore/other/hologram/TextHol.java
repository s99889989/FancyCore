package com.daxton.fancycore.other.hologram;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancycore.other.task.PackEntity;
import com.daxton.fancycore.manager.ProtocolMap;
import com.daxton.fancycore.nms.v1_16_R3.ItemDropMetadata;
import net.minecraft.server.v1_16_R3.EntityItem;
import net.minecraft.server.v1_16_R3.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_16_R3.PacketPlayOutSpawnEntity;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftServer;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftItem;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TextHol {

	public static void entitySpawn(Player player){
		FancyCore.fancyCore.getLogger().info("測試項目");
		Location inputLocation = player.getLocation().add(0,2,0);

		PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
		packet.getModifier().writeDefaults();
		int entityID = (int)(Math.random() * Integer.MAX_VALUE);
		packet.getIntegers().write(0, entityID);
		packet.getUUIDs().write(0, UUID.randomUUID());

		//packet.getEntityTypeModifier().write(0, EntityType.FALLING_BLOCK);
		packet.getEntityTypeModifier().write(0, EntityType.DROPPED_ITEM);

		packet.getDoubles().write(0, inputLocation.getX());
		packet.getDoubles().write(1, inputLocation.getY());
		packet.getDoubles().write(2, inputLocation.getZ());

		packet.getIntegers().write(4, (int) (inputLocation.getPitch() * 256.0F / 360.0F));
		packet.getIntegers().write(5, (int) (inputLocation.getYaw() * 256.0F / 360.0F));
		//packet.getIntegers().write(6, 12);

		PackEntity.sendPack(packet);

		CItem cItem = new CItem("PLAYER_HEAD");

		cItem.setHeadValue(player.getName());
		ItemStack itemStack = cItem.getItemStack();


		ItemDropMetadata.send(entityID, itemStack, inputLocation);

		PackEntity.velocity(entityID, new Vector());

	}
	public static void item1(Player player, int eID, Location inputLocation){
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

		EntityItem item = new EntityItem(((CraftWorld)inputLocation.getWorld()).getHandle(), 0,0,0);
		item.setLocation(inputLocation.getX(), inputLocation.getY(), inputLocation.getZ(), 0, 0);
		item.setItemStack(CraftItemStack.asNMSCopy(itemStack));

		PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(eID, item.getDataWatcher(), true);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(data);
	}
	public static void item(int eID, Location inputLocation){

		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
		EntityItem item = new EntityItem(((CraftWorld)inputLocation.getWorld()).getHandle(), 0,0,0);
		item.setLocation(inputLocation.getX(), inputLocation.getY(), inputLocation.getZ(), 0, 0);
		item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
		CraftServer server = ((CraftServer)Bukkit.getServer());

		Item item2 = new CraftItem(server, item);

		PacketContainer packet = ProtocolMap.protocolManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
		packet.getIntegers().write(0, eID);

		List<WrappedWatchableObject> list = new ArrayList<>();




		WrappedDataWatcher watcher = new WrappedDataWatcher();
		//WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(ItemStack.class);
		WrappedDataWatcher.Serializer serializer = WrappedDataWatcher.Registry.get(Object.class);
		watcher.setObject(8, serializer, itemStack);



		packet.getWatchableCollectionModifier().write(0, watcher.getWatchableObjects());

		//packet.getDataWatcherModifier().write(0, watcher);

		PackEntity.sendPack(packet);
	}

	public static void tt(Player p){
		ItemStack skull = new ItemStack(Material.SKELETON_SKULL, 1, (short) 3);

//		SkullMeta skm = (SkullMeta) skull.getItemMeta();
//		skm.setOwner(p.getName());
//		skm.setDisplayName(p.getName());
//		skull.setItemMeta(skm);
//		skull.setAmount(1);

		Location loc = p.getLocation().add(0,1,0);
		ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);


		EntityItem item = new EntityItem(((CraftWorld) loc.getWorld()).getHandle(), 0,0,0);
		item.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
		item.setItemStack(CraftItemStack.asNMSCopy(itemStack));
		item.setNoGravity(true);


		PacketPlayOutSpawnEntity spawnItem = new PacketPlayOutSpawnEntity(item);
		PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(item.getId(), item.getDataWatcher(), true);

		((CraftPlayer) p).getHandle().playerConnection.sendPacket(spawnItem);
		((CraftPlayer) p).getHandle().playerConnection.sendPacket(data);
	}

}
