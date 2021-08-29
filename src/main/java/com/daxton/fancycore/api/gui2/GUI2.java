package com.daxton.fancycore.api.gui2;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public final class GUI2 {

	Player player;
	int size;
	String title;
	Inventory inventory;

	private GUI2(Player player, int size, String title){
		this.player = player;
		this.size = size;
		this.title = title;
		this.inventory = Bukkit.createInventory(null, size , title);
	}

	public Player getPlayer() {
		return player;
	}

	public int getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	public Inventory getInventory() {
		return inventory;
	}

	@Override
	public String toString() {
		return player.getName()+" : "+size+" : "+title;
	}

	public static class GUIBuilder{
		Player player;
		int size;
		String title;

		private GUIBuilder(){

		}

		public static GUIBuilder getInstance(){
			return new GUIBuilder();
		}

		public GUIBuilder setPlayer(Player player) {
			this.player = player;
			return this;
		}

		public GUIBuilder setSize(int size) {
			this.size = size;
			return this;
		}

		public GUIBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		public GUI2 build(){
			return new  GUI2(player, size, title);
		}

	}

}

