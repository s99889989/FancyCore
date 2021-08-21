package com.daxton.fancycore.task;

import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.manager.TaskActionManager;
import com.daxton.fancycore.task.entity.*;
import com.daxton.fancycore.task.location.*;
import com.daxton.fancycore.task.meta.Action;
import com.daxton.fancycore.task.meta.run.FixedPoint;
import com.daxton.fancycore.task.meta.run.Loop;
import com.daxton.fancycore.task.meta.run.OrbitalAction;
import com.daxton.fancycore.task.player.*;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.Map;

public class TaskAction {

    public static void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        MapGetKey mapGetKey = new MapGetKey(action_Map, self, target);

        String judgeString = mapGetKey.getString(new String[]{"ActionType"}, "");

        //FancyCore.fancyCore.getLogger().info(judgeString);
        //目標為實體
        //-------------------------------------------------------------------------//

        if(judgeString.equalsIgnoreCase("Attribute")){
            AttributeSet attributeSet = new AttributeSet();
            attributeSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Damage")){
            Damage damage = new Damage();
            damage.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("DCMessage")){
            DiscordMessage discordMessage = new DiscordMessage();
            discordMessage.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Glow")){
            GlowSet glowSet = new GlowSet();
            glowSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Heal")){
            Heal heal = new Heal();
            heal.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Invisible")){
            InvisibleSet invisibleSet = new InvisibleSet();
            invisibleSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("LoggerInfo")){
            LoggerMessage loggerMessage = new LoggerMessage();
            loggerMessage.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Message")){
            Message message = new Message();
            message.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Move")){
            Move move = new Move();
            move.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("MythicSkill")){
            MythicAction mythicAction = new MythicAction();
            mythicAction.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Name")){
            NameSet nameSet = new NameSet();
            nameSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("PotionEffect")){
            PotionEffect potionEffect = new PotionEffect();
            potionEffect.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Teleport")){
            Teleport teleport = new Teleport();
            teleport.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        //目標為玩家
        //-------------------------------------------------------------------------//
        if(judgeString.equalsIgnoreCase("ActionBar")){
            ActionBar actionBar = new ActionBar();
            actionBar.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("BossBar")){
            BossBarSend bossBarSend = new BossBarSend();
            bossBarSend.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Command")){
            Command command = new Command();
            command.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Exp")){
            Experience experience = new Experience();
            experience.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Item")){

            return;
        }
        if(judgeString.equalsIgnoreCase("Inventory")){
            CustomInventory customInventory = new CustomInventory();
            customInventory.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Level")){
            Level level = new Level();
            level.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Mana")){

            return;
        }
        if(judgeString.equalsIgnoreCase("Money")){
            MoneySet moneySet = new MoneySet();
            moneySet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Title")){
            Title title = new Title();
            title.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        //目標為座標
        //-------------------------------------------------------------------------//
        if(judgeString.equalsIgnoreCase("Block")){
            BlockSet blockSet = new BlockSet();
            blockSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("FloatMessage")){
            MessageFloat messageFloat = new MessageFloat();
            messageFloat.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Guise")){
            return;
        }
        if(judgeString.equalsIgnoreCase("Light")){
            LightSet lightSet = new LightSet();
            lightSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Model")){
            ModelEngineSet modelEngineSet = new ModelEngineSet();
            modelEngineSet.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Particle")){
            ParticlesSend particlesSend = new ParticlesSend();
            particlesSend.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("Sound")){
            Sound sound = new Sound();
            sound.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        //元動作
        //-------------------------------------------------------------------------//
        if(judgeString.equalsIgnoreCase("Action")){
            Action action = new Action();
            action.execute(self, target, action_Map, inputLocation, taskID);
            return;
        }
        if(judgeString.equalsIgnoreCase("FixedPoint")){
            if(TaskActionManager.task_FixedPoint_Map.get(taskID) == null){
                FixedPoint fixedPoint = new FixedPoint();
                fixedPoint.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_FixedPoint_Map.put(taskID, fixedPoint);
            }else {
                FixedPoint fixedPoint = TaskActionManager.task_FixedPoint_Map.get(taskID);
                fixedPoint.cancel();
                FixedPoint fixedPoint2 = new FixedPoint();
                fixedPoint2.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_FixedPoint_Map.put(taskID, fixedPoint2);
            }

        }
        if(judgeString.equalsIgnoreCase("Loop")){
            if(TaskActionManager.task_Loop_Map.get(taskID) == null){
                Loop loop = new Loop();
                loop.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_Loop_Map.put(taskID, loop);
            }else {
                Loop loop = TaskActionManager.task_Loop_Map.get(taskID);
                loop.cancel();
                Loop loop2 = new Loop();
                loop2.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_Loop_Map.put(taskID, loop2);
            }
            return;
        }
        if(judgeString.equalsIgnoreCase("Orbital")){
            if(TaskActionManager.task_OrbitalAction_Map.get(taskID) == null){
                OrbitalAction orbitalAction = new OrbitalAction();
                orbitalAction.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_OrbitalAction_Map.put(taskID, orbitalAction);
            }else {
                OrbitalAction orbitalAction = TaskActionManager.task_OrbitalAction_Map.get(taskID);
                orbitalAction.cancel();

                OrbitalAction orbitalAction2 = new OrbitalAction();
                orbitalAction2.execute(self, target, action_Map, inputLocation, taskID);
                TaskActionManager.task_OrbitalAction_Map.put(taskID, orbitalAction2);
            }
            return;
        }
        //Class專用
        //-------------------------------------------------------------------------//




        //外加的動作
        //-------------------------------------------------------------------------//
        if(TaskActionManager.task_Action_Map.get(judgeString.toLowerCase()) != null){
            TaskActionManager.task_Action_Map.get(judgeString.toLowerCase()).execute(self, target, action_Map, inputLocation, taskID);
        }

    }

}
