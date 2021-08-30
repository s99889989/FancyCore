package com.daxton.fancycore.api.gui.button;

import org.bukkit.inventory.ItemStack;

public class GuiButton {

	public GuiAction guiAction;
	public ItemStack itemStack;
	public boolean move;
	int actual;
	public int place;
	public int vertical;
	public int horizontal;

	public GuiButton(GuiAction guiAction, ItemStack itemStack, boolean move){
		this.guiAction = guiAction;
		this.itemStack = itemStack;
		this.move = move;
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ButtonBuilder{

		GuiAction guiAction;
		ItemStack itemStack;
		boolean move = false;
		int actual = 1;
		int place = 1;
		int vertical = 1;
		int horizontal = 1;

		private ButtonBuilder(){

		}

		public static ButtonBuilder getInstance(){
			return new ButtonBuilder();
		}

		public ButtonBuilder setGuiAction(GuiAction guiAction){
			this.guiAction = guiAction;
			return this;
		}

		public ButtonBuilder setItemStack(ItemStack itemStack){
			this.itemStack = itemStack;
			return this;
		}

		public ButtonBuilder setMove(boolean move){
			this.move = move;
			return this;
		}

//		public ButtonBuilder setPlace(int vertical, int horizontal){
//			this.vertical = vertical;
//			this.horizontal = horizontal;
//			this.place = ((vertical-1)*9)+(horizontal);
//			this.actual = ((vertical-1)*9)+(horizontal-1);
//			return this;
//		}
//
//		public ButtonBuilder setPlace(int place){
//			this.place = place;
//			this.actual = place - 1;
//			this.vertical = ((place-1)/9)+1;
//			this.horizontal = 1;
//			return this;
//		}

		public GuiButton build(){
			return new GuiButton(guiAction, itemStack, move);
		}

	}

}
