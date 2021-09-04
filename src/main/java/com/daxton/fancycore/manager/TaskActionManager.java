package com.daxton.fancycore.manager;

import com.daxton.fancycore.other.hologram.FloatMessage;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.task.guise.GuiseEntity;
import com.daxton.fancycore.other.task.modelentity.ModelEntity;
import com.daxton.fancycore.task.meta.run.FixedPoint;
import com.daxton.fancycore.task.meta.run.Loop;
import com.daxton.fancycore.task.meta.run.OrbitalAction;
import com.ticxo.modelengine.api.model.ModeledEntity;
import org.bukkit.Location;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskActionManager {
    //動作Key 動作Map
    public static Map<String, List<Map<String, String>>> action_SubAction_Map = new HashMap<>();
    //動作(沒有持續執行的動作)
    public static Map<String, FancyAction> task_Action_Map = new HashMap<>();
    //Loop
    public static Map<String, Loop> task_Loop_Map = new HashMap<>();
    //OrbitalAction
    public static Map<String, OrbitalAction> task_OrbitalAction_Map = new HashMap<>();
    //FixedPoint
    public static Map<String, FixedPoint> task_FixedPoint_Map = new HashMap<>();
    //FloatMessage
    public static Map<String, FloatMessage> task_FloatMessage_Map = new HashMap<>();
    //BossBar
    public static Map<String, BossBar> task_BossBar_Map = new HashMap<>();
    //GuiseEntity
    public static Map<String, GuiseEntity> task_GuiseEntity_Map = new HashMap<>();
    //ModelEntity
    public static Map<String, ModelEntity> task_ModelEntity_Map = new HashMap<>();


}
