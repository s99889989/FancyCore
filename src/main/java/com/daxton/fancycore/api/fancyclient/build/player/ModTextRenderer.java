package com.daxton.fancycore.api.fancyclient.build.player;

import com.daxton.fancycore.api.fancyclient.json.player.text.TextEntityJson;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModTextRenderer implements RendererComponent{

	//物件名稱
	private String objectName;
	//顯示文字大小
	private float scale;
	//位置高度
	private double locationHeight;
	//位置選轉角度
	private double locationAngle;
	//位置選轉角度距離
	private double locationDistance;
	//每行距離
	private int rowHeight;
	//對齊(1=靠左,2=置中,3=靠右)
	private int align;
	//內容列表
	private List<String> textList;
	//旋轉角度
	private float rotateX;
	private float rotateY;
	private float rotateZ;
	//自動旋轉角度
	private int autoRotationX;
	private int autoRotationY;
	private int autoRotationZ;

	private ModTextRenderer(String objectName, float scale, double locationHeight, double locationAngle, double locationDistance, int rowHeight, int align, List<String> textList, float rotateX, float rotateY, float rotateZ, int autoRotationX, int autoRotationY, int autoRotationZ) {
		this.objectName = objectName;
		this.scale = scale;
		this.locationHeight = locationHeight;
		this.locationAngle = locationAngle;
		this.locationDistance = locationDistance;
		this.rowHeight = rowHeight;
		this.align = align;
		this.textList = textList;
		this.rotateX = rotateX;
		this.rotateY = rotateY;
		this.rotateZ = rotateZ;
		this.autoRotationX = autoRotationX;
		this.autoRotationY = autoRotationY;
		this.autoRotationZ = autoRotationZ;
	}

	public TextEntityJson toObject(LivingEntity self, LivingEntity target){
		return new TextEntityJson(self, target, objectName, scale, locationHeight, locationAngle, locationDistance, rowHeight, align, textList, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
	}

	//---------------------------------------------------------------------------------------------------//

	public static class ModTextRendererBuilder{

		//物件名稱
		private String objectName = String.valueOf((int)(Math.random()*100000));
		//顯示文字大小
		private float scale = 0.025F;
		//位置高度
		private double locationHeight;
		//位置選轉角度
		private double locationAngle;
		//位置選轉角度距離
		private double locationDistance;
		//每行距離
		private int rowHeight = 10;
		//對齊(1=靠左,2=置中,3=靠右)
		private int align = 1;
		//內容列表
		private List<String> textList = new ArrayList<>();
		//旋轉角度
		private float rotateX;
		private float rotateY;
		private float rotateZ;
		//自動旋轉角度
		private int autoRotationX;
		private int autoRotationY;
		private int autoRotationZ;

		public static ModTextRendererBuilder getInstance(){
			return new ModTextRendererBuilder();
		}

		public ModTextRendererBuilder setObjectName(String objectName) {
			this.objectName = objectName;
			return this;
		}

		public ModTextRendererBuilder setScale(float scale) {
			this.scale = scale;
			return this;
		}

		public ModTextRendererBuilder setLocationHeight(double locationHeight) {
			this.locationHeight = locationHeight;
			return this;
		}

		public ModTextRendererBuilder setLocationAngle(double locationAngle) {
			this.locationAngle = locationAngle;
			return this;
		}

		public ModTextRendererBuilder setLocationDistance(double locationDistance) {
			this.locationDistance = locationDistance;
			return this;
		}

		public ModTextRendererBuilder setRowHeight(int rowHeight) {
			this.rowHeight = rowHeight;
			return this;
		}

		public ModTextRendererBuilder setAlign(int align) {
			this.align = align;
			return this;
		}

		public ModTextRendererBuilder setTextList(String[] textList) {
			this.textList = Arrays.asList(textList);
			return this;
		}

		public ModTextRendererBuilder addText(String text){
			this.textList.add(text);
			return this;
		}

		public ModTextRendererBuilder setTextList(List<String> textList) {
			this.textList = textList;
			return this;
		}

		public ModTextRendererBuilder setRotateX(float rotateX) {
			this.rotateX = rotateX;
			return this;
		}

		public ModTextRendererBuilder setRotateY(float rotateY) {
			this.rotateY = rotateY;
			return this;
		}

		public ModTextRendererBuilder setRotateZ(float rotateZ) {
			this.rotateZ = rotateZ;
			return this;
		}

		public ModTextRendererBuilder setAutoRotationX(int autoRotationX) {
			this.autoRotationX = autoRotationX;
			return this;
		}

		public ModTextRendererBuilder setAutoRotationY(int autoRotationY) {
			this.autoRotationY = autoRotationY;
			return this;
		}

		public ModTextRendererBuilder setAutoRotationZ(int autoRotationZ) {
			this.autoRotationZ = autoRotationZ;
			return this;
		}

		public ModTextRenderer build(){
			return new ModTextRenderer(objectName, scale, locationHeight, locationAngle, locationDistance, rowHeight, align, textList, rotateX, rotateY, rotateZ, autoRotationX, autoRotationY, autoRotationZ);
		}

	}

}
