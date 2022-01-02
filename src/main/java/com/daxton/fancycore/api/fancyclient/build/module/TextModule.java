package com.daxton.fancycore.api.fancyclient.build.module;

import com.daxton.fancycore.api.fancyclient.json.menu_object.text.TextLabelJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;

public class TextModule implements ModComponent {

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

	private TextModule(String object_name, float scale, int position, int x, int y, boolean vertical, int row_height, int line_spacing, int align, boolean reverse_sort, List<String> text_list) {
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

	public static class Builder {

		//物件名稱
		private String objectID = String.valueOf((int)(Math.random()*100000));
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
		private int rowHeight = 7;
		//行距
		private int lineSpacing = 1;
		//對齊(1=靠左,2=置中,3=靠右)
		private int align = 1;
		//文字反向排序
		private boolean reverseSort;
		//內容列表
		private List<String> textList = new ArrayList<>();

		public static Builder getInstance(){
			return new Builder();
		}

		public Builder addTextList(String text){
			this.textList.add(text);
			return this;
		}

		public Builder setTextList(List<String> text_list) {
			this.textList = text_list;
			return this;
		}

		public Builder setObjectName(String object_name) {
			this.objectID = object_name;
			return this;
		}

		public Builder setScale(float font_size) {
			this.scale = font_size;
			return this;
		}

		public Builder setPosition(int position) {
			this.position = position;
			return this;
		}

		public Builder setX(int x) {
			this.x = x;
			return this;
		}

		public Builder setY(int y) {
			this.y = y;
			return this;
		}

		public Builder setVertical(boolean vertical) {
			this.vertical = vertical;
			return this;
		}

		public Builder setRowHeight(int rowHeight) {
			this.rowHeight = rowHeight;
			return this;
		}

		public Builder setLineSpacing(int line_spacing) {
			this.lineSpacing = line_spacing;
			return this;
		}

		public Builder setAlign(int align) {
			this.align = align;
			return this;
		}

		public Builder setReverseSort(boolean reverse_sort) {
			this.reverseSort = reverse_sort;
			return this;
		}

		public TextModule build(){
			return new TextModule(objectID, scale, position, x, y, vertical, rowHeight, lineSpacing, align, reverseSort, textList);
		}

	}

}
