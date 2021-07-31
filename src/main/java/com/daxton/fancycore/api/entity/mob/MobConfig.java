package com.daxton.fancycore.api.entity.mob;


import com.daxton.fancycore.FancyCore;
import com.daxton.fancycore.api.config.ConfigLoad;
import com.daxton.fancycore.config.FileConfig;
import com.daxton.fancycore.manager.MobManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MobConfig {


    //讀取MythicMobs怪物設定檔
    public static void load(){

        Map<String, FileConfiguration> mob_Config_Map = ConfigLoad.execute("plugins\\MythicMobs\\Mobs");
        create(mob_Config_Map);
    }
    //建立怪物設定檔
    public static void create(Map<String, FileConfiguration> mob_Config_Map){
        File mobs_file = new File(FancyCore.fancyCore.getDataFolder(),"Mobs");
        if(!mobs_file.exists()){
            mobs_file.mkdir();
        }
        FileConfiguration mobDefaultConfig = FileConfig.config_Map.get("Other/Default_Mob.yml");
        mob_Config_Map.forEach((fileName, loadConfig) -> {
            File file = new File(FancyCore.fancyCore.getDataFolder(),"Mobs/"+fileName);
            if(!file.exists()){
                try {
                    if(file.createNewFile()){
                        FileConfiguration saveConfig = YamlConfiguration.loadConfiguration(file);
                        loadConfig.getConfigurationSection("").getKeys(false).forEach(mobID -> {
                            MobManager.mythicMobs_mobFile_Map.put(mobID,"Mobs/"+fileName);
                            if(loadConfig.contains(mobID +".Type")){
                                String type = loadConfig.getString(mobID +".Type");
                                saveConfig.set(mobID +".Type", type);
                            }

                            if(loadConfig.contains(mobID +".Display")){
                                String display = loadConfig.getString(mobID +".Display");
                                saveConfig.set(mobID +".Display", display);
                            }

                            if(loadConfig.contains(mobID +".Faction")){
                                String faction = loadConfig.getString(mobID +".Faction");
                                saveConfig.set(mobID +".Faction", faction);
                            }
                            mobDefaultConfig.getConfigurationSection("Default_Mob").getKeys(false).forEach(ss -> {

                                if(!saveConfig.contains(mobID +".Custom."+ss)){
                                    saveConfig.set(mobID +".Custom."+ss, mobDefaultConfig.get("Default_Mob."+ss));
                                }

                            });
                            MobManager.mob_Data_Map.put(mobID, new MobData(mobID, saveConfig));
                        });
                        try {
                            saveConfig.save(file);
                        }catch (IOException exception){
                            //
                        }
                    }
                }catch (IOException exception){
                    //
                }

            }else {
                FileConfiguration saveConfig = FileConfig.config_Map.get("Mobs/"+fileName);
                loadConfig.getConfigurationSection("").getKeys(false).forEach(mobID -> {
                    MobManager.mythicMobs_mobFile_Map.put(mobID,"Mobs/"+fileName);
                    if(loadConfig.contains(mobID +".Type")){
                        String type = loadConfig.getString(mobID +".Type");
                        saveConfig.set(mobID +".Type", type);
                    }

                    if(loadConfig.contains(mobID +".Display")){
                        String display = loadConfig.getString(mobID +".Display");
                        saveConfig.set(mobID +".Display", display);
                    }

                    if(loadConfig.contains(mobID +".Faction")){
                        String faction = loadConfig.getString(mobID +".Faction");
                        saveConfig.set(mobID +".Faction", faction);
                    }
                    mobDefaultConfig.getConfigurationSection("Default_Mob").getKeys(false).forEach(ss -> {

                        if(!saveConfig.contains(mobID +".Custom."+ss)){
                            saveConfig.set(mobID +".Custom."+ss, mobDefaultConfig.get("Default_Mob."+ss));
                        }

                    });
                    MobManager.mob_Data_Map.put(mobID, new MobData(mobID, saveConfig));
                });
                try {
                    saveConfig.save(file);
                }catch (IOException exception){
                    //
                }
            }

        });

    }

}
