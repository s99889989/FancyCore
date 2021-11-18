package com.daxton.fancycore.api.fancyclient.build.gui;

import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class ModText implements ModComponent{

	//物件名稱
	private String object_name;
	//顯示文字大小
	private float scale;
	//基礎位置
	private int position;
	//X偏移
	private int x;
	//Y偏移
	private int y;
	//垂直顯示
	private boolean vertical;
	//行高
	private int row_height;
	//行距
	private int line_spacing;
	//對齊(1=靠左,2=置中,3=靠右)
	private int align;
	//文字反向排序
	private boolean reverse_sort;
	//內容列表
	private List<String> text_list;

	private ModText(String object_name, float scale, int position, int x, int y, boolean vertical, int row_height, int line_spacing, int align, boolean reverse_sort, List<String> text_list) {
		this.object_name = object_name;
		this.scale = scale;
		this.position = position;
		this.x = x;
		this.y = y;
		this.vertical = vertical;
		this.row_height = row_height;
		this.line_spacing = line_spacing;
		this.align = align;
		this.reverse_sort = reverse_sort;
		this.text_list = text_list;
	}

	public void addText(String text){
		text_list.add(text);
	}

	public void setText(int index, String text){
		if(text_list.size() == index+1){
			text_list.set(index, text);
		}
		if(text_list.size() == index){
			text_list.add(text);
		}
	}

	public TextLabelJson toObject(){
		return new TextLabelJson(object_name, scale, position, x, y, vertical, row_height, line_spacing, align, reverse_sort, text_list);
	}

	public TextLabelJson toObject(LivingEntity self, LivingEntity target){
		return new TextLabelJson(self, target, object_name, scale, position, x, y, vertical, row_height, line_spacing, align, reverse_sort, text_list);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModTextBuilder{

		//物件名稱
		private String object_name = "";
		//顯示文字大小
		private float scale = 1;
		//基礎位置
		private int position;
		//X偏移
		private int x;
		//Y偏移
		private int y;
		//垂直顯示
		private boolean vertical;
		//行高
		private int row_height = 7;
		//行距
		private int line_spacing = 1;
		//對齊(1=靠左,2=置中,3=靠右)
		private int align = 1;
		//文字反向排序
		private boolean reverse_sort;
		//內容列表
		private List<String> text_list = new ArrayList<>();

		public static ModTextBuilder getInstance(){
			return new ModTextBuilder();
		}

		public ModTextBuilder addTextList(String text){
			this.text_list.add(text);
			return this;
		}

		public ModTextBuilder setTextList(List<String> text_list) {
			this.text_list = text_list;
			return this;
		}

		public ModTextBuilder setObjectName(String object_name) {
			this.object_name = object_name;
			return this;
		}

		public ModTextBuilder setScale(float font_size) {
			this.scale = font_size;
			return this;
		}

		public ModTextBuilder setPosition(int position) {
			this.position = position;
			return this;
		}

		public ModTextBuilder setX(int x) {
			this.x = x;
			return this;
		}

		public ModTextBuilder setY(int y) {
			this.y = y;
			return this;
		}

		public ModTextBuilder setVertical(boolean vertical) {
			this.vertical = vertical;
			return this;
		}

		public ModTextBuilder setRow_height(int row_height) {
			this.row_height = row_height;
			return this;
		}

		public ModTextBuilder setLineSpacing(int line_spacing) {
			this.line_spacing = line_spacing;
			return this;
		}

		public ModTextBuilder setAlign(int align) {
			this.align = align;
			return this;
		}

		public ModTextBuilder setReverseSort(boolean reverse_sort) {
			this.reverse_sort = reverse_sort;
			return this;
		}

		public ModText build(){
			return new ModText(object_name, scale, position, x, y, vertical, row_height, line_spacing, align, reverse_sort, text_list);
		}

	}

}
