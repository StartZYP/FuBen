package com.ipedg.minecraft.config;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.entity.FubenEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class FubenConfig {
    public static void ConfigLoad(){
        File config = new File(Fuben.plugin.getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            Fuben.plugin.getConfig().options().copyDefaults(true);
        }
        File FubenFile = new File(Fuben.plugin.getDataFolder()+File.separator+"FubenFile");
        if (!FubenFile.exists()){
            FubenFile.mkdir();
        }
        Fuben.fuben.clear();
        Set<String> mines = Fuben.plugin.getConfig().getConfigurationSection("Fuben").getKeys(false);
        for (String temp:mines){
            String FubenName= Fuben.plugin.getConfig().getString("Fuben."+temp+".Name");
            int Itemid = Fuben.plugin.getConfig().getInt("Fuben."+temp+".Itemid");
            int FubenChildId = Fuben.plugin.getConfig().getInt("Fuben."+temp+".Child");
            ArrayList<String> Lores = (ArrayList<String>) Fuben.plugin.getConfig().getStringList("Fuben."+temp+".Name");
            int NeedMoney = Fuben.plugin.getConfig().getInt("Fuben."+temp+".NeedMoney");
            String NeedItemLore = Fuben.plugin.getConfig().getString("Fuben."+temp+".NeedItemLore");
            int MaxLevel = Fuben.plugin.getConfig().getInt("Fuben."+temp+".MaxLevel");
            int MinLevel = Fuben.plugin.getConfig().getInt("Fuben."+temp+".MinLevel");
            int RestartMoney = Fuben.plugin.getConfig().getInt("Fuben."+temp+".RestartMoney");
            int RestartCount = Fuben.plugin.getConfig().getInt("Fuben."+temp+".RestartCount");
            FubenEntity fubenEntity = new FubenEntity(temp, FubenName, Itemid, FubenChildId, Lores, NeedMoney, NeedItemLore, MaxLevel, MinLevel, RestartMoney, RestartCount);
            System.out.println(fubenEntity);
            Fuben.fuben.add(fubenEntity);
        }
    }
}
