package com.daxton.fancycore.nms.v1_16_R3;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlockState;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemDropMetadata {

	public static void send(int entityID, ItemStack itemStack, Location inputLocation){
		try {
			//ItemStack item = new ItemStack(Material.LAVA);

			net.minecraft.server.v1_16_R3.ItemStack nmsItemStack = CraftItemStack.asNMSCopy(itemStack);
			World world = ((CraftWorld)inputLocation.getWorld()).getHandle();
			EntityItem entityItem = new EntityItem(world, 0,0,0);

//			net.minecraft.server.v1_16_R3.ItemBlock nmsBlock = CraftBlockState.getBlockState();
//
//			org.bukkit.entity.Item item = (Item) inputLocation.getWorld().spawnEntity(inputLocation, EntityType.DROPPED_ITEM);
//			net.minecraft.server.v1_16_R3.Block block = Block.asBlock(item);
//			net.minecraft.server.v1_16_R3.Block.dropItems();


			entityItem.setLocation(inputLocation.getX(), inputLocation.getY(), inputLocation.getZ(), 0, 0);
			entityItem.setItemStack(nmsItemStack);
			entityItem.setNoGravity(true);

			//world.addEntity(entityItem);

			Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
			List<Player> playerList = new ArrayList<>(onlinePlayers);
			playerList.forEach(player -> {
				PacketPlayOutEntityMetadata data = new PacketPlayOutEntityMetadata(entityID, entityItem.getDataWatcher(), true);
				((CraftPlayer) player).getHandle().playerConnection.sendPacket(data);
			});

			//PackEntity.velocity(entityID, new Vector());
			//nmsItemStack.a(net.minecraft.server.v1_16_R3.Blocks.LAVA.getBlockData());
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}


}
