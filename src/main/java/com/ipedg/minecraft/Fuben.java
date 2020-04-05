package com.ipedg.minecraft;


import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.worldmanger.DropFubenThread;
import com.ipedg.minecraft.worldmanger.FubenInitThread;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Fuben extends JavaPlugin {
    public static Plugin plugin;
    public static ArrayList<FubenEntity> fuben = new ArrayList<>();
    public static HashMap<String,ArrayList<String>> menu = new HashMap<>();
    public static HashMap<String, Inventory> menuall = new HashMap<>();

    @Override
    public void onEnable() {
        plugin = this;
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        File FubenFile = new File(getDataFolder()+File.separator+"FubenFile");
        if (!FubenFile.exists()){
            FubenFile.mkdir();
        }
        saveDefaultConfig();
        FubenConfig.ConfigLoad();
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1&&sender.isOp()){
            if (args[0].equalsIgnoreCase("reload")){
                FubenConfig.ConfigLoad();
                sender.sendMessage("§e§l插件重载成功");
            }else if (Fuben.menu.containsKey(args[0])){
                Player sender1 = (Player) sender;
                sender1.openInventory(Fuben.menuall.get(args[0]));
            }
            //Bukkit.getScheduler().runTask(this,new FubenInitThread("test1"));
        }else if (args.length==2){
            //Bukkit.getScheduler().runTask(this,new DropFubenThread("test1"));
        }
        return super.onCommand(sender, command, label, args);
    }
}
