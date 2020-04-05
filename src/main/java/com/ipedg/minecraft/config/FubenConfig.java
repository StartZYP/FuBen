package com.ipedg.minecraft.config;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.view.FubenMenu;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class FubenConfig {
    public static int GlobalRestartCd;

    public static void ConfigLoad(){
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
        Fuben.menu.clear();
        Set<String> menutmps = Fuben.plugin.getConfig().getConfigurationSection("FubenMenu").getKeys(false);
        for (String menu:menutmps){
            ArrayList<String> stringList = (ArrayList<String>)Fuben.plugin.getConfig().getStringList("FubenMenu." + menu);
            System.out.println(stringList);
            System.out.println(menu);
            Fuben.menu.put(menu,stringList);
        }
        GlobalRestartCd = Fuben.plugin.getConfig().getInt("GlobalRestartCd");
        FubenMenu.InitInventorymenu();
    }
}
