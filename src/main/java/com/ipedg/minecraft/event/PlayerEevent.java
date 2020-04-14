package com.ipedg.minecraft.event;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.Utils.FubenUtil;
import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.economy.VaultUtil;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.entity.PlayerEntity;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Date;


public class PlayerEevent implements Listener {

    @EventHandler
    public void PlayerDoCmd(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        String name = player.getWorld().getName();
        if (name.contains(Fuben.CHEACKKEY)){
            FubenEntity fuben = Fuben.fubenplayer.get(player.getName()).getFuben();
            ArrayList<String> anticmd = fuben.getAnticmd();
            for (String cmd:anticmd){
                if (event.getMessage().contains(cmd)){
                    player.sendMessage(FubenConfig.AntiCmdMsg);
                    event.setCancelled(true);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void PlayerBreakBlock(BlockBreakEvent event){
        String name = event.getPlayer().getName();
        if (name.contains(Fuben.CHEACKKEY)){
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void PlayerClickInventory(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().contains(Fuben.CHEACKKEY)){
            event.setCancelled(true);
            int slot = event.getSlot();
            if (slot<10||slot>16){
                return;
            }
            ItemStack clickitem= inventory.getItem(slot);
            if (clickitem==null){
                return;
            }
            if (!clickitem.hasItemMeta()){
                return;
            }
            String FubenName = ItemGetNbtTagKey(clickitem);
            if (FubenName!=null){
                Player p = (Player) event.getWhoClicked();
                FubenEntity fubenEntity = Fuben.fuben.get(FubenName.indexOf(FubenName));
                if (!CheackEnterFueb(p,fubenEntity)){
                    return;
                }
                String GoFubenName = fubenEntity.getFubenWorldName()+Fuben.CHEACKKEY+p.getName().hashCode();
                Fuben.fubenplayer.put(p.getName(),new PlayerEntity(p.getName(),fubenEntity,GoFubenName));
                FubenUtil.GoFuben(p.getName(),GoFubenName,fubenEntity.getFubenWorldName(),fubenEntity.getFubenName());
            }
        }
    }
    @EventHandler
    public void InventoryFuebnMenuDragEvent(InventoryDragEvent event){
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
    public void PlayerClickRestartInventory(InventoryClickEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equals(FubenConfig.RestartTitle)){
            event.setCancelled(true);
            int slot = event.getSlot();
            Player whoClicked = (Player) event.getWhoClicked();
            if (!whoClicked.getWorld().getName().contains(Fuben.CHEACKKEY)){
                return;
            }
            if (slot ==2){
                PlayerEntity playerEntity = Fuben.fubenplayer.get(whoClicked.getName());
                if (VaultUtil.pay(whoClicked.getUniqueId(),playerEntity.getFuben().getFubenNeedMoney())){
                    playerEntity.setQuittime(0);
                    playerEntity.setStatus(1);
                    whoClicked.sendMessage(FubenConfig.RestartOkMsg);
                    whoClicked.closeInventory();
                    Fuben.fubenplayer.put(whoClicked.getName(),playerEntity);
                }else {
                    whoClicked.teleport(Fuben.mainworld);
                }
            }else if (slot ==6){
                whoClicked.teleport(Fuben.mainworld);
            }

        }
    }
    @EventHandler
    public void InventoryDrageRestartMenuevent(InventoryDragEvent event){
        Inventory inventory = event.getInventory();
        if (inventory.getTitle().equals(FubenConfig.RestartTitle)){
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void NpcDeath(EntityDeathEvent event) {
        if (event.getEntityType().name().equals("CUSTOMNPCS_CUSTOMNPC")){
            CraftEntity entity = (CraftEntity) event.getEntity();
            String name = entity.getHandle().getName();
            if (name.contains(FubenConfig.BossNameKey)){
                for (String cmd:Fuben.completecmd){
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),cmd);
                }
                for (Player player:entity.getWorld().getPlayers()){
                    PlayerEntity playerEntity = Fuben.fubenplayer.get(player.getName());
                    playerEntity.setQuittime(new Date().getTime()/1000);
                    playerEntity.setStatus(2);
                    Fuben.fubenplayer.put(player.getName(),playerEntity);
                }
            }
        }
    }

    @EventHandler
    public void PlayerDeath(EntityDeathEvent event){
        if (event.getEntityType() == EntityType.PLAYER){
            Player entity = (Player) event.getEntity();
            if (entity.getWorld().getName().contains(Fuben.CHEACKKEY)){
                //todo 加进复活CD队
                PlayerEntity playerEntity = Fuben.fubenplayer.get(entity.getName());
                int restartCount = playerEntity.getNowRestartCount()+1;
                if (restartCount>=playerEntity.getFuben().getRestartCount()){
                    entity.sendMessage(FubenConfig.DeathGoBackMsg);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            entity.teleport(Fuben.mainworld);
                        }
                    }.runTaskLater(Fuben.plugin,20L);
                }else {
                    playerEntity.setQuittime(new Date().getTime()/1000);
                    entity.sendMessage(FubenConfig.DeathMsg.replace("{Count}",String.valueOf(restartCount)));
                    playerEntity.setNowRestartCount(restartCount);
                    playerEntity.setStatus(1);
                    Fuben.fubenplayer.put(entity.getName(),playerEntity);
                }
            }
        }
    }

    @EventHandler
    public void PlayerChangeWorld(PlayerChangedWorldEvent event){
        Player player = event.getPlayer();
        if (event.getFrom().getName().contains(Fuben.CHEACKKEY)){
            PlayerEntity playerEntity = Fuben.fubenplayer.get(player.getName());
            FubenUtil.DeleteWorldFile(playerEntity.getWorldName());
            player.sendMessage(FubenConfig.QuitFubenMsg);
            player.closeInventory();
            Fuben.fubenplayer.remove(player.getName());
        }
    }

    @EventHandler
    public void PlayerQuitGame(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains(Fuben.CHEACKKEY)){
            player.teleport(Fuben.mainworld);
            Fuben.fubenplayer.remove(player.getName());
        }
    }

    @EventHandler
    public void PlayerMoveEvent(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().contains(Fuben.CHEACKKEY)){
            String name = player.getName();
            PlayerEntity playerEntity = Fuben.fubenplayer.get(name);
            if (playerEntity.getStatus()==1){
                event.setCancelled(true);
            }
        }
    }


    public static boolean CheackEnterFueb(Player player,FubenEntity fubenEntity){
        if (Fuben.fubenplayer.containsKey(player.getName())){
            player.sendMessage(FubenConfig.AntiQuickClickMsg);
            return false;
        }
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
            ItemStack itemInHand = player.getItemInHand();
            if (itemInHand!=null&&itemInHand.hasItemMeta()){
                ItemMeta itemMeta = itemInHand.getItemMeta();
                if (itemMeta.hasLore()){
                    String haslore = fubenEntity.getNeedItemLore();
                    if (itemMeta.getLore().toString().contains(haslore)){
                        int amount = itemInHand.getAmount();
                        if (amount==1){
                            player.setItemInHand(null);
                        }else {
                            itemInHand.setAmount(--amount);
                            player.setItemInHand(itemInHand);
                        }
                        return true;
                    }
                }
            }
            player.sendMessage(FubenConfig.NoItemMsg);
            return false;
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
