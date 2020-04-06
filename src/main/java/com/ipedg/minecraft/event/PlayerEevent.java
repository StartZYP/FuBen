package com.ipedg.minecraft.event;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.economy.VaultUtil;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.entity.PlayerEntity;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraftforge.cauldron.entity.CraftCustomEntity;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerEevent implements Listener {

    @EventHandler
    public void PlayerClickInventory(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        ItemStack item = inventory.getItem(10);
        if (!item.hasItemMeta()){
            return;
        }
        if (ItemGetNbtTagKey(item)!=null){
            int slot = event.getSlot();
            ItemStack clickitem= inventory.getItem(slot);
            event.setCancelled(true);
            String FubenName = ItemGetNbtTagKey(clickitem);
            if (FubenName!=null){
                Player p = (Player) event.getWhoClicked();
                FubenEntity fubenEntity = Fuben.fuben.get(FubenName.indexOf(FubenName));
                if (CheackEnterFueb(p,fubenEntity)){

                }

                //todo 创建世界后加进倒计时队列
            }
        }
    }
    @EventHandler
    public void InventoryDragEvent(InventoryDragEvent event){
        Inventory inventory = event.getInventory();
        ItemStack item = inventory.getItem(10);
        if (!item.hasItemMeta()){
            return;
        }
        if (ItemGetNbtTagKey(item)!=null){
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void NPCDeath(EntityDeathEvent event){
        if (event.getEntityType() == EntityType.PLAYER){
            Player entity = (Player) event.getEntity();
            if (entity.getWorld().getName().contains(Fuben.CHEACKKEY)){
                //todo 加进复活CD队列

            }
        }
    }

    @EventHandler
    public void PlayerChangeWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains(Fuben.CHEACKKEY)){
            Fuben.fubenplayer.remove(player.getName());
            player.sendMessage("离开副本");
        }
    }

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Fuben.fubenplayer.remove(player.getName());
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains(Fuben.CHEACKKEY)){
            String name = player.getName();
            PlayerEntity playerEntity = Fuben.fubenplayer.get(name);
            if (playerEntity.isDeath()){
                event.setCancelled(true);
            }
        }
    }


    public static boolean CheackEnterFueb(Player player,FubenEntity fubenEntity){
        int MaxLevel = fubenEntity.getMaxLevel();
        int MinLevel = fubenEntity.getMinLevel();
        if (MaxLevel!=0||MinLevel!=0){
            if (!FubenUtil.CheackLevl(player.getLevel(),MinLevel,MaxLevel)){
                player.sendMessage(FubenConfig.LevellimitMsg);
                return false;
            }
        }
        if (fubenEntity.getFubenNeedMoney()!=0){
            if (!VaultUtil.pay(player.getUniqueId(),fubenEntity.getFubenNeedMoney())){
                player.sendMessage(FubenConfig.NoMoneyMsg);
                return false;
            }
        }
        if (!fubenEntity.getNeedItemLore().equals("")){
            //todo遍历背
        }


        return true;
    }


    public static String ItemGetNbtTagKey(ItemStack item)
    {
        net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem.hasTag())
        {
            NBTTagCompound compound = nmsItem.getTag();
            if (compound.hasKey(Fuben.CHEACKKEY)) {
                return compound.getString(Fuben.CHEACKKEY);
            }
        }
        return null;
    }
}
