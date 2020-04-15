package com.ipedg.minecraft.Utils;


import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.worldmanger.DropFubenThread;
import com.ipedg.minecraft.worldmanger.FubenInitThread;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FubenUtil {

    public static void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }


    public static void copyFolder(File sourceFolder, File destinationFolder)
    {
        if (sourceFolder.isDirectory())
        {
            if (!destinationFolder.exists())
            {
                destinationFolder.mkdir();
            }
            String files[] = sourceFolder.list();

            for (String file : files)
            {
                File srcFile = new File(sourceFolder, file);
                File destFile = new File(destinationFolder, file);
                copyFolder(srcFile, destFile);
            }
        }
        else
        {
            try{
                Files.copy(sourceFolder.toPath(), destinationFolder.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static boolean CheackLevl(int PlayerLevel,int MinLevel,int MaxLevel){
        return PlayerLevel<MaxLevel&&PlayerLevel>=MinLevel;
    }

    public static boolean deleteFile(File dirFile) {
        if (!dirFile.exists()) {
            return false;
        }

        if (dirFile.isFile()) {
            return dirFile.delete();
        } else {

            for (File file : dirFile.listFiles()) {
                deleteFile(file);
            }
        }

        return dirFile.delete();
    }



    public static void GoFuben(String PlayerName,String FubenFileName,String pffile,String aliasname){

        Bukkit.getScheduler().runTask(Fuben.plugin,new FubenInitThread(PlayerName,FubenFileName,pffile,aliasname));
    }

    public static void DeleteWorldFile(String FubenFileName){
        Bukkit.getScheduler().runTaskLater(Fuben.plugin,new DropFubenThread(FubenFileName),20L);
    }
}
