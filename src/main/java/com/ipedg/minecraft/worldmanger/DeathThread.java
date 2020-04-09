package com.ipedg.minecraft.worldmanger;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.entity.PlayerEntity;
import com.ipedg.minecraft.view.FubenMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.Date;
import java.util.Set;

public class DeathThread implements Runnable {
    @Override
    public void run() {
        Set<String> playernames = Fuben.fubenplayer.keySet();
        long nowtime = new Date().getTime()/1000;
        for (String p:playernames){
            PlayerEntity playerEntity = Fuben.fubenplayer.get(p);
            if (playerEntity.getQuittime()!=0){
                long hastime = nowtime-playerEntity.getQuittime();
                Player player = Bukkit.getServer().getPlayer(p);
                if (hastime>=FubenConfig.GlobalRestartCd){
                    player.teleport(Fuben.mainworld);
                }
                if (playerEntity.getStatus()==1){
                    InventoryView openInventory = player.getOpenInventory();
                    if (!openInventory.getTitle().equals(FubenConfig.RestartTitle)){
                        int restartMoney = playerEntity.getFuben().getRestartMoney();
                        Inventory inv = FubenMenu.CreateRestartInventory(restartMoney);
                        player.openInventory(inv);
                    }
                }else if(playerEntity.getStatus()==2){
                    player.sendMessage(FubenConfig.GobackPickMsg);
                }
            }
        }
    }
}
