package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import com.onarandombox.MultiverseCore.api.MultiversePlugin;
import com.onarandombox.MultiverseCore.commands.EnvironmentCommand;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;


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
        File wordfile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName);
        File fubenFILE = new File(Fuben.plugin.getDataFolder().getAbsolutePath()+File.separator+"FubenFile"+File.separator+pffile);
        FubenUtil.copyFolder(fubenFILE,wordfile);
        World.Environment environment = EnvironmentCommand.getEnvFromString("normal");
        Fuben.multiversePlugin.getMVWorldManager().addWorld(FubenName, environment, null, null,null, "", true);
        Fuben.multiversePlugin.getMVWorldManager().loadWorld(FubenName);
        Bukkit.getServer().getPlayer(playerName).teleport(Bukkit.getWorld(FubenName).getSpawnLocation());
        Fuben.multiversePlugin.getMVWorldManager().getMVWorld(FubenName).setAlias(aliasname);
    }
}
