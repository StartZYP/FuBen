package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import com.ipedg.minecraft.entity.FubenEntity;
import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.onarandombox.MultiverseCore.api.MultiverseWorld;
import com.onarandombox.MultiverseCore.commands.EnvironmentCommand;
import net.minecraft.server.v1_7_R4.WorldServer;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;


import java.io.File;

public class FubenInitThread implements Runnable {
    private String FubenName;
    private String playerName;
    private String pffile;
    private String aliasname;

    public FubenInitThread(String playerName,String FubenName,String pffile,String aliasname){
        this.FubenName = FubenName;
        this.playerName = playerName;
        this.pffile = pffile;
        this.aliasname = aliasname;
    }


    @Override
    public void run() {
        World.Environment environment = EnvironmentCommand.getEnvFromString("normal");
        Fuben.multiversePlugin.getMVWorldManager().addWorld(FubenName,environment,null,WorldType.FLAT,null,"",true);
        Fuben.multiversePlugin.getMVWorldManager().unloadWorld(FubenName);
        File deletefile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName+File.separator+"region");
        System.out.println(deletefile.getAbsolutePath());
        FubenUtil.deleteFile(deletefile);
        File wordfile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName+File.separator+"region");
        File fubenFILE = new File(Fuben.plugin.getDataFolder().getAbsolutePath()+File.separator+"FubenFile"+File.separator+pffile+File.separator+"region");
        FubenUtil.copyFolder(fubenFILE,wordfile);
        World cbWorld = Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).getCBWorld();
        Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).setAllowAnimalSpawn(false);
        Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).setEnableWeather(false);
        Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).setAllowMonsterSpawn(false);
        Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).setAlias(aliasname);
        FubenEntity fuben = Fuben.fubenplayer.get(playerName).getFuben();
        Fuben.multiversePlugin.getMVWorldManager().loadWorld(FubenName);
        Bukkit.getServer().getPlayer(playerName).teleport(new Location(cbWorld,fuben.getX(),fuben.getY(),fuben.getZ()));
    }
}
