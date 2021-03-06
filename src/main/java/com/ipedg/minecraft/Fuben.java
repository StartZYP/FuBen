package com.ipedg.minecraft;


import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.economy.VaultUtil;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.entity.MenuEntity;
import com.ipedg.minecraft.entity.PlayerEntity;
import com.ipedg.minecraft.event.PlayerEevent;
import com.ipedg.minecraft.worldmanger.DeathThread;
import com.ipedg.minecraft.worldmanger.DropFubenThread;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class Fuben extends JavaPlugin {
    public static Plugin plugin;
    public static ArrayList<FubenEntity> fuben = new ArrayList<>();
    public static ArrayList<MenuEntity> menu = new ArrayList<>();
    public static HashMap<String, Inventory> menuall = new HashMap<>();
    public static HashMap<String, PlayerEntity> fubenplayer = new HashMap<>();
    public static ArrayList<String> completecmd = new ArrayList<>();
    public static MultiverseCore multiversePlugin;
    public static String CHEACKKEY="zyp";
    public static Location mainworld;
    @Override
    public void onEnable() {
        plugin = this;
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        multiversePlugin = (com.onarandombox.MultiverseCore.MultiverseCore) Bukkit.getPluginManager().getPlugin("Multiverse-Core");
        new BukkitRunnable(){
            @Override
            public void run() {
                File FubenFile = new File(getDataFolder() + File.separator +"FubenFile");
                if (!FubenFile.exists()){
                    FubenFile.mkdir();
                }
            }
        }.runTaskLater(this,20L);
        saveDefaultConfig();
        FubenConfig.ConfigLoad();
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerEevent(),this);
        if (VaultUtil.setupEconomy()){
            System.out.println("[Fuben]Vault初始化成功");
        }else {
            System.out.println("[Fuben]Vault初始化失败");
        }
        Bukkit.getScheduler().runTaskTimer(this,new DeathThread(),0,20L);
        super.onEnable();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length==1){
            if (args[0].equalsIgnoreCase("reload")&&sender.isOp()){
                FubenConfig.ConfigLoad();
                sender.sendMessage("§e§l插件重载成功");
            }else if (Fuben.menu.contains(new MenuEntity(args[0]))){
                Player sender1 = (Player) sender;
                sender1.openInventory(Fuben.menuall.get(args[0]));
            }
        }
        return super.onCommand(sender, command, label, args);
    }
}
