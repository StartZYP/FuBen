package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;

import java.io.File;


public class DropFubenThread implements Runnable {

    private String FubenName;
    public DropFubenThread(String FubenName){
        this.FubenName = FubenName;
    }
    @Override
    public void run() {
        Fuben.multiversePlugin.getMVWorldManager().removeWorldFromConfig(FubenName);
        File wordfile = new File(Fuben.plugin.getDataFolder().getAbsoluteFile().getParentFile().getParentFile().getAbsolutePath()+File.separator+"world"+File.separator+FubenName);
        FubenUtil.deleteDirectory(wordfile);
    }

}
