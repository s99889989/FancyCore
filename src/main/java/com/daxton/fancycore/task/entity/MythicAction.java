package com.daxton.fancycore.task.entity;

import com.daxton.fancycore.api.aims.entity.GetEntity;
import com.daxton.fancycore.other.task.FancyAction;
import com.daxton.fancycore.other.taskaction.MapGetKey;
import com.daxton.fancycore.other.taskaction.StringToMap;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.skills.Skill;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MythicAction implements FancyAction {

    public void execute(LivingEntity self, LivingEntity target, Map<String, String> action_Map, Location inputLocation, String taskID){
        if (Bukkit.getServer().getPluginManager().getPlugin("MythicMobs") == null){
            return;
        }
        MapGetKey actionMapHandle = new MapGetKey(action_Map, self, target);
        //MythicMobs技能名稱
        String skillName = actionMapHandle.getString(new String[]{"skill","s"},"SmashAttack");
        //目標
        String targetString = actionMapHandle.getString(new String[]{"targetkey"}, "");
        Map<String, String> targetMap = StringToMap.toTargetMap(targetString);
        List<LivingEntity> livingEntityList = GetEntity.get(self, target, targetMap);
        //
        Optional<Skill> opt = MythicMobs.inst().getSkillManager().getSkill(skillName);
        if(!(opt.isPresent())){
            return;
        }
        Skill skill = opt.get();

        List<Entity> entityList = new ArrayList<>(livingEntityList);
        MythicMobs.inst().getAPIHelper().castSkill(self, skill.getInternalName(), self, self.getOrigin(), entityList, null, 1.0F);

    }

}
