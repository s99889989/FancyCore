package com.daxton.fancycore.task.meta.run;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.aims.entity.many.Radius;
import com.daxton.fancycore.api.aims.location.GetLocation;
import com.daxton.fancycore.api.aims.other.ThreeDLocation;
import com.daxton.fancycore.api.aims.vector.LocationVector;
import com.daxton.fancycore.api.aims.vector.PathDeviation;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import com.daxton.fancycore.task.meta.StartAction;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class LocPng extends BukkitRunnable {

	private Function<Location,Location> fLocation;
	private Location targetLocation;
	private Location startLocation;
	private Vector vec;
	private int speed;
	private int j;
	private int k;


	private int period = 1;
	private int duration = 100;

	private List<Map<String, String>> onTimeHitList = new ArrayList<>();
	private List<Map<String, String>> onTimeList = new ArrayList<>();
	private List<Map<String, String>> onEndHitList = new ArrayList<>();
	private List<Map<String, String>> onEndList = new ArrayList<>();

	private double hitRange = 0.8;
	private boolean selfDead = true;
	private boolean targetDead = true;
	private int setHitCount;
	private int hitCount;
	private boolean hitStop;

	private LivingEntity self;

	private LivingEntity target;

	private String taskID = "";

	Map<String, String> action_Map;

	public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String inTaskID){
		this.self = self;
		this.target = target;
		this.taskID = inTaskID;
		this.action_Map = action_Map;
		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
		//初始動作
		List<Map<String, String>> onStartList = actionMapHandle.getActionMapList(new String[]{"onstart","os"},null);

		//過程中動作
		onTimeList = actionMapHandle.getActionMapList(new String[]{"ontime","ot"},null);

		//過程中命中動作
		onTimeHitList = actionMapHandle.getActionMapList(new String[]{"ontimehit","oth"},null);

		//最後動作
		onEndList = actionMapHandle.getActionMapList(new String[]{"onend","oe"},null);

		//最後命中動作
		onEndHitList = actionMapHandle.getActionMapList(new String[]{"onendhit","oeh"},null);

		//執行間隔時間
		period = actionMapHandle.getInt(new String[]{"period","p"},0);

		//此動作要持續多久
		duration = actionMapHandle.getInt(new String[]{"duration","d"},20);

		//軌道偏移
		String[] orbitalDeviation = actionMapHandle.getStringList(new String[]{"orbitaldeviation","od"},new String[]{"true","true","false","0","0"},"\\|",5);

		boolean odpt = true;
		boolean odyw = true;
		boolean odsign = false;
		double odX = 0;
		double odY = 0;

		if(orbitalDeviation.length == 5){
			odpt = Boolean.parseBoolean(orbitalDeviation[0]);
			odyw = Boolean.parseBoolean(orbitalDeviation[1]);
			odsign = Boolean.parseBoolean(orbitalDeviation[2]);
			try {
				odX = Double.parseDouble(orbitalDeviation[3]);
				odY = Double.parseDouble(orbitalDeviation[4]);

			}catch (NumberFormatException exception){
				odX = 0;
				odY = 0;

			}
		}

		//技能運行速度
		speed = actionMapHandle.getInt(new String[]{"speed","s"},20);

		//命中判定範圍
		hitRange = actionMapHandle.getDouble(new String[]{"hitrange","hr"},0.8);

		//命中次數
		setHitCount = actionMapHandle.getInt(new String[]{"hitcount","hc"},0);
		//命中次數過後是否結束任務
		hitStop = actionMapHandle.getBoolean(new String[]{"hitstop","hs"},false);

		//起始座標偏移
		String[] startlocadds = actionMapHandle.getStringList(new String[]{"startlocadd","sla"},new String[]{"true","true","0","0","0","0"},"\\|",6);
		boolean startTargetPitch = Boolean.parseBoolean(startlocadds[0]);
		boolean startTargetYaw = Boolean.parseBoolean(startlocadds[1]);
		double startPitch;
		double startYaw;
		double startDistance;
		double startHight;
		try {
			startPitch = Double.parseDouble(startlocadds[2]);
			startYaw = Double.parseDouble(startlocadds[3]);
			startDistance = Double.parseDouble(startlocadds[4]);
			startHight = Double.parseDouble(startlocadds[5]);
		}catch (NumberFormatException exception){
			startPitch = 0;
			startYaw = 0;
			startDistance = 0;
			startHight = 0;
		}
		//起始位置
		startLocation = LocationVector.getDirectionLoction(self.getLocation(), self.getLocation(), startTargetPitch, startTargetYaw, startPitch, startYaw, startDistance).add(0, startHight, 0);

		//如果自身死亡任務是否取消
		selfDead = actionMapHandle.getBoolean(new String[]{"selfdead","sd"},true);

		//如果目標死亡任務是否取消
		targetDead = actionMapHandle.getBoolean(new String[]{"targetdead","td"},true);

		//目標
		String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
		Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
		Location location = GetLocation.getOne(self, target, targetMap, inputLocation);
		targetLocation = location;

		if(targetLocation != null){

			fLocation = (floc) -> floc;

			vec = PathDeviation.getDirection2(self.getLocation(), odpt, odyw, odsign, odX, odY, 1);

			List<Location> runLocation = getLocationList(this.self, this.target, startLocation, action_Map);
			for(int i = 0 ; i < runLocation.size() ; i++){
				Location goLocation = runLocation.get(i);
				StartAction.execute(self, target, onStartList, goLocation.clone() , taskID+i);
			}

			runTaskTimer(FancyCore.fancyCore, 0L, period);


		}




	}

	public void run(){
		//自身死亡
		if(selfDead && self.isDead()){
			List<Location> runLocation = getLocationList(this.self, this.target, targetLocation, action_Map);
			for(int i = 0 ; i < runLocation.size() ; i++){
				Location location = runLocation.get(i);
				StartAction.execute(self, target, onEndList, location.clone() , taskID+i);
			}

			cancel();
			TaskActionManager.task_OrbitalAction_Map.remove(taskID);
			return;
		}

		j += period;
		for(int k = 0; k < 1; ++k) {
			double c = Math.min(1.0D, (double) j / speed);
			Location location = fLocation.apply(startLocation.add(targetLocation.clone().subtract(startLocation).toVector().normalize().multiply(c).add(vec.multiply(1.0D - c))));
			List<Location> runLocation = getLocationList(this.self, this.target, location, action_Map);
			for(int i = 0 ; i < runLocation.size() ; i++){
				Location goLocation = runLocation.get(i);
				StartAction.execute(self, target, onTimeList, goLocation.clone() , taskID+i);
			}
		}


		if(j > duration || startLocation.distanceSquared(targetLocation) < hitRange){
			List<Location> runLocation = getLocationList(this.self, this.target, targetLocation, action_Map);
			for(int i = 0 ; i < runLocation.size() ; i++){
				Location goLocation = runLocation.get(i);
				StartAction.execute(self, target, onEndList, goLocation.clone() , taskID+i);
			}
			List<LivingEntity> livingEntityList = Radius.getRadiusLivingEntities3(self, targetLocation, hitRange);
			if(livingEntityList.size() > 0){
				for(LivingEntity livingEntity : livingEntityList){
					StartAction.execute(this.self, livingEntity, onEndHitList, targetLocation, taskID);
				}
			}
			cancel();
			TaskActionManager.task_OrbitalAction_Map.remove(taskID);
		}

	}

	//圖片位置設定
	public List<Location> getLocationList(LivingEntity self, LivingEntity livingEntity, Location location, Map<String, String> action_Map){
		FancyCore cd = FancyCore.fancyCore;
		List<Location> runLocation = new ArrayList<>();

		MapGetKey actionMapHandle = new MapGetKey(action_Map, self, livingEntity);

		//要使用的圖片名稱
		String img = actionMapHandle.getString(new String[]{"img"},"");

		//圖片的縮放
		double imgSize = actionMapHandle.getDouble(new String[]{"is","imgsize"},1);

		//要使用的圖片角度
		String[] pngRotAngles = actionMapHandle.getStringList(new String[]{"ira","imgrotangle"},new String[]{"Self","true","true","0","0","0"},"\\|",6);
		String imgTarget = pngRotAngles[0].toLowerCase();
		boolean imgTargetPitch = Boolean.parseBoolean(pngRotAngles[1]);
		boolean imgTargetYaw = Boolean.parseBoolean(pngRotAngles[2]);
		double imgAddX;
		double imgAddY;
		double imgAddZ;
		try {
			imgAddX = Double.parseDouble(pngRotAngles[3]);
			imgAddY = Double.parseDouble(pngRotAngles[4]);
			imgAddZ = Double.parseDouble(pngRotAngles[5]);
		}catch (NumberFormatException exception){
			imgAddX = 0;
			imgAddY = 0;
			imgAddZ = 0;
		}


		try{
			BufferedImage bufferedImage = ImageIO.read(new File(cd.getDataFolder(),"Png/"+ img+".png"));


			double[] inputDouble;
			if(imgTarget.equals("self")){
				inputDouble = ThreeDLocation.getCosSin(self, imgTargetPitch, imgTargetYaw, imgAddX, imgAddY, imgAddZ);
			}else {
				inputDouble = ThreeDLocation.getCosSin(livingEntity, imgTargetPitch, imgTargetYaw, imgAddX, imgAddY, imgAddZ);
			}

			int width = bufferedImage.getWidth();

			double widthHalf = (double)width/2;

			int height = bufferedImage.getHeight();

			double heightHalf = (double)height/2;

			for(int i=0 ; i < height ; i++) {

				for (int j = 0; j < width ; j++) {

					int color = bufferedImage.getRGB(j, i);
					//int blue = color & 0xff;
					//int green = (color & 0xff00) >> 8;
					//int red = (color & 0xff0000) >> 16;
					int alpha = (color & 0xff000000) >>> 24;
					//int rgb = ( (red*65536) + (green*256) +blue );

					if(alpha != 0){
						//把高度置中
						double addHeight;
						if(i == (heightHalf-0.5)){
							addHeight = -0.5;
						}else if(i >= heightHalf){
							addHeight = (i-heightHalf)*-1;
						}else {
							addHeight = (heightHalf-(i));
						}
						addHeight = addHeight * imgSize;
						//把寬度置中
						double addWidth;
						if(j == (widthHalf-0.5)){
							addWidth = -0.5;
						}else if(j >= widthHalf){
							addWidth = (j-widthHalf);
						}else {
							addWidth = (widthHalf-(j))*-1;
						}
						addWidth = addWidth * imgSize;

						Location useLocation = location.clone().add(addWidth, addHeight, 0);

						useLocation = ThreeDLocation.getPngLocationX(useLocation.clone(), location.clone(), inputDouble);
						useLocation = ThreeDLocation.getPngLocationY(useLocation.clone(), location.clone(), inputDouble);
						useLocation = ThreeDLocation.getPngLocationZ(useLocation.clone(), location.clone(), inputDouble);

						runLocation.add(useLocation);

					}

				}
			}


		}catch (IOException exception){
			//
		}

		return runLocation;
	}

}
