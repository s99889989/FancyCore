package com.daxton.fancycore.other.taskaction;

import com.daxton.fancycore.api.character.stringconversion.ConversionMain;
import com.daxton.fancycore.manager.TaskActionManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.SoundCategory;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.bukkit.Color.fromRGB;

public class MapGetKey {

    private Map<String, String> action_Map;
    private LivingEntity self;
    private LivingEntity target;
    //從動作Map獲取資訊
    public MapGetKey(){

    }

    public MapGetKey(Map<String, String> action_Map){
        this.action_Map = action_Map;
    }

    public MapGetKey(Map<String, String> action_Map, LivingEntity self, LivingEntity target){
        this.action_Map = action_Map;
        this.self = self;
        this.target = target;
    }

    public String getString(String[] key, String def) {
        String output = def;

        for(String ss : key){
            if(this.action_Map.get(ss.toLowerCase()) != null){
                output = this.action_Map.get(ss.toLowerCase());
                if(output.contains("&")){
                    output = ConversionMain.valueOf(this.self, this.target, output, true);
                }
                return output;
            }
        }

        return output;
    }

    public String[] getStringList(String[] key, String[] def, String split, int amount){
        String[] output;
        String inputString = getString(key,null);
        if(inputString != null){
            output = inputString.split(split);
            if(output.length == amount){
                return output;
            }
        }
        return def;
    }

    //不限長度的字串陣列
    public String[] getStringListLong(String[] key, String split){
        String[] output = null;
        String inputString = getString(key,null);
        if(inputString != null){
            output = inputString.split(split);
        }
        return output;
    }

    public boolean getBoolean(String[] key, boolean def){
        boolean output = def;
        String inputString = getString(key,null);
        if(inputString != null){
            output = Boolean.parseBoolean(inputString);
        }
        return output;
    }

    public long getLong(String[] key, long defaultKey){
        long output = defaultKey;

        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Long.parseLong(inputString);
            }catch (NumberFormatException exception){

            }
        }
        return output;
    }

    public int getInt(String[] key, int defaultKey){
        int output = defaultKey;

        String inputString = getString(key,null);
        if(inputString != null){
            if(inputString.contains(".")){
                inputString = inputString.substring(0, inputString.indexOf("."));
            }
            try {
                output = Integer.parseInt(inputString);
            }catch (NumberFormatException exception){

            }
        }
        return output;
    }
    public double getDouble(String[] key, double defaultKey){
        double output = defaultKey;
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Double.parseDouble(inputString);
            }catch (NumberFormatException exception){

            }
        }

        return output;
    }

    public float getFloat(String[] key, float defaultKey){
        float output = defaultKey;
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Float.parseFloat(inputString);
            }catch (NumberFormatException exception){

            }
        }
        return output;
    }

    //BossBarFlag
    public BarFlag getBarFlag(String[] key){
        BarFlag output = null;
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(BarFlag.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }


    //BossBar顏色
    public BarColor getBarColor(String[] key, String defaultKey){
        //BarColor output = Enum.valueOf(BarColor.class ,defaultKey);
        BarColor output = null;
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(BarColor.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }
        return output;
    }

    //BossBar樣式
    public BarStyle getBarStyle(String[] key, String defaultKey){
        //BarStyle output = Enum.valueOf(BarStyle.class ,defaultKey);
        BarStyle output = null;
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(BarStyle.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }
        return output;
    }

    //粒子
    public Particle getParticle(String[] key, String defaultKey){
        Particle output = Enum.valueOf(Particle.class ,defaultKey);
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(Particle.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //材質副值
    public BlockData getBlockData(String[] key, String defaultKey){
        BlockData output = Enum.valueOf(Material.class ,defaultKey).createBlockData();
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(Material.class ,inputString.toUpperCase()).createBlockData();
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //物品
    public ItemStack getItemStack(String[] key, String defaultKey){
        ItemStack output = new ItemStack(Enum.valueOf(Material.class ,defaultKey));
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = new ItemStack(Enum.valueOf(Material.class ,inputString.toUpperCase()));
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //粒子顏色
    public Particle.DustOptions getParticleColor(String[] key, String defaultKey){
        BigInteger bigint = new BigInteger(defaultKey, 16);
        int numb = bigint.intValue();
        Particle.DustOptions output = new Particle.DustOptions(fromRGB(numb), 1);

        String inputString = getString(key,defaultKey);

        if(inputString != null){
            try {
                bigint = new BigInteger(inputString, 16);
                numb = bigint.intValue();
                output = new Particle.DustOptions(fromRGB(numb), 1);
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //顏色
    public ChatColor getChatColor(String[] key, String defaultKey){
        ChatColor output = Enum.valueOf(ChatColor.class ,defaultKey);
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(ChatColor.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //聲音的分類
    public SoundCategory getSoundCategory(String[] key, String defaultKey){
        SoundCategory output = Enum.valueOf(SoundCategory.class ,defaultKey);
        String inputString = getString(key,null);
        if(inputString != null){
            try {
                output = Enum.valueOf(SoundCategory.class ,inputString.toUpperCase());
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //藥水的類型
    public PotionEffectType getPotionEffectType(String[] key, PotionEffectType defaultKey){
        PotionEffectType output = PotionEffectType.INCREASE_DAMAGE;

        String inputString = getString(key,null);
        if(inputString != null){
            try {
                for (PotionEffectType pe : PotionEffectType.values()) {
                    if(pe.getName().equals(inputString.toUpperCase())){
                        output = pe;
                    }
                }
            }catch (IllegalArgumentException exception){

            }
        }

        return output;
    }

    //根據動作名稱 返回動作列表
    public List<Map<String, String>> getActionMapList(String[] key, String def){
        List<Map<String, String>> customLineConfigList = new ArrayList<>();
        String inputString = getString(key,def);

        if(inputString != null){
            //CustomDisplay.getCustomDisplay().getLogger().info("Action: "+inputString);
            if(TaskActionManager.action_SubAction_Map.get(inputString) != null){
                customLineConfigList = TaskActionManager.action_SubAction_Map.get(inputString);
            }
        }

        return customLineConfigList;
    }

}
