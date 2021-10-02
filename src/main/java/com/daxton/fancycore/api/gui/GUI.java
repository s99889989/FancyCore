package com.daxton.fancycore.api.gui;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.button.GuiChatAction;
import com.daxton.fancycore.api.gui.button.GuiCloseAction;
import com.daxton.fancycore.api.gui.button.GuiOpenAction;
import com.daxton.fancycore.manager.PlayerManagerCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;


public final class GUI {

	Player player;
	int size;
	String title;
	Inventory inventory;
	public GuiButton[] buttons;
	public GuiOpenAction guiOpenAction;
	public GuiCloseAction guiCloseAction;
	public GuiChatAction guiChatAction;
	boolean move;
	boolean open;
	boolean chat;

	private GUI(Player player, int size, String title, boolean move){
		this.player = player;
		this.size = size;
		this.title = title;
		this.move = move;
		this.inventory = Bukkit.createInventory(player, size , title);
		buttons = new GuiButton[size+36];
		//PlayerManagerCore.player_Gui_Map.put(player.getUniqueId(), this);
	}
	//設置按鈕
	public void setButton(GuiButton guiButton, int vertical, int horizontal){
		int actual = ((vertical-1)*9)+(horizontal-1);
		buttons[actual] = guiButton;
		inventory.setItem(actual, guiButton.itemStack);
	}
	//設置按鈕
	public void setButton(GuiButton guiButton, int place){
		int actual = place - 1;
		buttons[actual] = guiButton;
		inventory.setItem(actual, guiButton.itemStack);
	}

	//指定範圍的空位置增加按鈕，用ignore進行過濾不需要位置
	public void addButton(GuiButton guiButton, int head, int tail, Integer[] integers){
		List<Integer> ignore = Arrays.asList(integers);
		if(tail > head){
			for(int i = head; i < tail+1 ; i++){
				int actual = i - 1;
				if(buttons[actual] == null && !ignore.contains(i)){
					//FancyCore.fancyCore.getLogger().info(""+actual);
					buttons[actual] = guiButton;
					if(actual < size){
						inventory.setItem(actual, guiButton.itemStack);
					}
					break;
				}
			}
		}
	}
	//移除按鈕
	public void removeButton(int vertical, int horizontal){
		int actual = ((vertical-1)*9)+(horizontal-1);
		buttons[actual] = null;
		inventory.clear(actual);
	}
	//移除按鈕
	public void removeButton(int place){
		int actual = place - 1;
		buttons[actual] = null;
		inventory.clear(actual);
	}

	//從指定位置開始往後清除物品，位置為1~54。
	public void clearButtonFrom(int head, int tail){
		if(tail > head){
			for(int i = head; i < tail+1 ; i++){
				int actual = i - 1;
				buttons[actual] = null;
				if(actual < size){
					inventory.clear(actual);
				}
			}
		}
	}


	//打開
	public void open(GUI gui){

		GUI oGui = PlayerManagerCore.player_Gui_Map.get(player.getUniqueId());


		if(oGui != null && oGui.isOpen()){
			oGui.close();
			new BukkitRunnable() {
				@Override
				public void run() {
					gui.setOpen(true);
					PlayerManagerCore.player_Gui_Map.put(player.getUniqueId(), gui);
					if(gui.guiOpenAction != null){
						gui.guiOpenAction.execute();
					}
					player.openInventory(gui.inventory);
				}
			}.runTask(FancyCore.fancyCore);
		}else {
			gui.setOpen(true);
			PlayerManagerCore.player_Gui_Map.put(player.getUniqueId(), gui);
			if(gui.guiOpenAction != null){
				gui.guiOpenAction.execute();
			}
			player.openInventory(gui.inventory);
		}



	}

	public void close(){
		inventory.close();
	}

	//設置關閉動作
	public void setGuiCloseAction(GuiCloseAction guiCloseAction) {
		this.guiCloseAction = guiCloseAction;
	}
	//設置聊天動作
	public void setGuiChatAction(GuiChatAction guiChatAction) {
		this.guiChatAction = guiChatAction;
	}
	//設置打開動作
	public void setGuiOpenAction(GuiOpenAction guiOpenAction) {
		this.guiOpenAction = guiOpenAction;
	}

	//設置全部按鈕移動
	public void setMove(boolean move) {
		this.move = move;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public void setChat(boolean chat) {
		this.chat = chat;
	}

	//

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

	public boolean isMove() {
		return move;
	}

	public boolean isOpen() {
		return open;
	}

	public GuiButton getButtons(int vertical, int horizontal) {
		int actual = ((vertical-1)*9)+(horizontal-1);
		return buttons[actual];
	}

	public GuiButton getButtons(int place) {
		int actual = place - 1;
		return buttons[actual];
	}

	public GuiCloseAction getGuiCloseAction() {
		return guiCloseAction;
	}

	public GuiChatAction getGuiChatAction() {
		return guiChatAction;
	}

	public boolean isChat() {
		return chat;
	}

	//---------------------------------------------------------------------------------------------------//

	@Override
	public String toString() {
		return player.getName()+" : "+size+" : "+title;
	}

	public static class GUIBuilder{
		Player player;
		int size = 54;
		String title = "";
		boolean move = false;

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

		public GUIBuilder setMove(boolean move){
			this.move = move;
			return this;
		}

		public GUI build(){
			return new GUI(player, size, title, move);
		}

	}

}

