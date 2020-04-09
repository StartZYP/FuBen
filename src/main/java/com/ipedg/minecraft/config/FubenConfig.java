package com.ipedg.minecraft.config;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.entity.MenuEntity;
import com.ipedg.minecraft.view.FubenMenu;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.Set;

public class FubenConfig {
    public static int GlobalRestartCd;
    public static String RestartTitle;
    public static String LevellimitMsg;
    public static String NoMoneyMsg;
    public static String AntiQuickClickMsg;
    public static String NoItemMsg;
    public static String DeathMsg;
    public static String RestartOkMsg;
    public static String DeathGoBackMsg;
    public static String BossNameKey;
    public static String GobackPickMsg;
    public static String QuitFubenMsg;


    public static void ConfigLoad(){
        Fuben.plugin.reloadConfig();
        Fuben.fuben.clear();
        Set<String> mines = Fuben.plugin.getConfig().getConfigurationSection("Fuben").getKeys(false);
        for (String temp:mines){
            String FubenName= Fuben.plugin.getConfig().getString("Fuben."+temp+".Name");
            int Itemid = Fuben.plugin.getConfig().getInt("Fuben."+temp+".Itemid");
            int FubenChildId = Fuben.plugin.getConfig().getInt("Fuben."+temp+".Child");
            ArrayList<String> Lores = (ArrayList<String>) Fuben.plugin.getConfig().getStringList("Fuben."+temp+".Lores");
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
            ArrayList<String> stringList = (ArrayList<String>)Fuben.plugin.getConfig().getStringList("FubenMenu." + menu+".MenuList");
            String menuname = Fuben.plugin.getConfig().getString("FubenMenu." + menu+".Name");
            System.out.println(stringList);
            System.out.println(menuname);
            Fuben.menu.add(new MenuEntity(menuname,stringList,menu));
        }
        GlobalRestartCd = Fuben.plugin.getConfig().getInt("FubenConfig.GlobalRestartCd");
        RestartTitle = Fuben.plugin.getConfig().getString("RestartTitle");
        LevellimitMsg = Fuben.plugin.getConfig().getString("LevellimitMsg");
        NoMoneyMsg = Fuben.plugin.getConfig().getString("NoMoneyMsg");
        AntiQuickClickMsg = Fuben.plugin.getConfig().getString("AntiQuickClickMsg");
        NoItemMsg = Fuben.plugin.getConfig().getString("NoItemMsg");
        DeathMsg = Fuben.plugin.getConfig().getString("DeathMsg");
        RestartOkMsg = Fuben.plugin.getConfig().getString("RestartOkMsg");
        DeathGoBackMsg =Fuben.plugin.getConfig().getString("DeathGoBackMsg");
        BossNameKey = Fuben.plugin.getConfig().getString("FubenConfig.BossNameKey");
        GobackPickMsg = Fuben.plugin.getConfig().getString("GobackPickMsg");
        QuitFubenMsg = Fuben.plugin.getConfig().getString("QuitFubenMsg");
        Fuben.completecmd = (ArrayList<String>) Fuben.plugin.getConfig().getStringList("FubenConfig.completecmd");
        Fuben.mainworld = Bukkit.getWorld(Fuben.plugin.getConfig().getString("FubenConfig.MainWorld")).getSpawnLocation();
        FubenMenu.InitInventorymenu();
    }
}
