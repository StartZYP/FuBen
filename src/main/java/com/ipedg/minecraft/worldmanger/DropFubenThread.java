package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import org.bukkit.Bukkit;


import java.io.File;

public class DropFubenThread implements Runnable {

    private String FubenName;
    public DropFubenThread(String FubenName){
        this.FubenName = FubenName;
    }
    @Override
    public void run() {
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv unload "+FubenName);
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"mv remove "+FubenName);
        File wordfile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName);
        FubenUtil.deleteDirectory(wordfile);
    }

}
