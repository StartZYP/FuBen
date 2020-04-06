package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import org.bukkit.Bukkit;


import java.io.File;

public class FubenInitThread implements Runnable {
    private String FubenName;
    private String playerName;

    public FubenInitThread(String playerName,String FubenName){
        this.FubenName = FubenName;
        this.playerName = playerName;
    }


    @Override
    public void run() {
        File wordfile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName);
        File fubenFILE = new File(Fuben.plugin.getDataFolder().getAbsolutePath()+File.separator+"FubenFile"+File.separator+FubenName);
        FubenUtil.copyFolder(fubenFILE,wordfile);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv import "+FubenName + " normal");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv load "+FubenName);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv tp "+playerName+" "+FubenName);
    }
}
