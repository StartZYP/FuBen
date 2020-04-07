package com.ipedg.minecraft.view;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.config.FubenConfig;
import com.ipedg.minecraft.entity.FubenEntity;
import com.ipedg.minecraft.entity.MenuEntity;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class FubenMenu {

    public static void InitInventorymenu(){
        Fuben.menuall.clear();
        for (MenuEntity menu:Fuben.menu){
            Fuben.menuall.put(menu.getmenucmd(),CreateFubenInventory(menu.getMenutitle(),menu.getMenulist()));
        }
    }
    public static Inventory CreateFubenInventory(String Title,ArrayList<String> Fubenname){
        Inventory inventory = Bukkit.createInventory(null, 27, Title+Fuben.CHEACKKEY);
        ArrayList<FubenEntity> tmpfb = new ArrayList<>();
        for (String fuben:Fubenname){
            tmpfb.add(new FubenEntity(fuben));
        }
        List<FubenEntity> newfuben = Fuben.fuben.stream().filter(tmpfb::contains).collect(Collectors.toList());
        ItemStack redblock = itemStackinit("§b§l装饰品",new ArrayList<>(),160,14,null);
        for (int i=0;i<=9;i++){
            inventory.setItem(i,redblock);
        }
        int b =9;
        for(FubenEntity fubenEntity:newfuben){
            b+=1;
            inventory.setItem(b,itemStackinit(fubenEntity.getFubenName(),fubenEntity.getLores(),fubenEntity.getFubenItemid(),fubenEntity.getFubenChildId(),fubenEntity.getFubenWorldName()));
        }
        for (int i=17;i<=26;i++){
            inventory.setItem(i,redblock);
        }
        return inventory;
    }

    public static Inventory CreateRestartInventory(int FubenMoney){
        Inventory inventory = Bukkit.createInventory(null, 9, FubenConfig.RestartTitle);
        ItemStack moneybutton = itemStackinitmoney("我要复活", new ArrayList<>(), 31, 2, FubenMoney);
        inventory.setItem(2,moneybutton);
        ItemStack norestart = itemStackinit("不复活",new ArrayList<>(), 31, 2, null);
        inventory.setItem(6,norestart);
        return inventory;
    }



    private static ItemStack itemStackinit(String Name, List<String> lore, int id, int childid,String NbtFubenName){
        ItemStack item = new ItemStack(id, 1, (short) childid);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        if (NbtFubenName!=null){
            net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
            NBTTagCompound compound = nmsItem.getTag();
            compound.setString(Fuben.CHEACKKEY, NbtFubenName);
            return CraftItemStack.asBukkitCopy(nmsItem);
        }
        return item;
    }
    private static ItemStack itemStackinitmoney(String Name, List<String> lore, int id, int childid,int NbtFubenmoney){
        ItemStack item = new ItemStack(id, 1, (short) childid);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        net.minecraft.server.v1_7_R4.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        NBTTagCompound compound = nmsItem.getTag();
        compound.setInt(Fuben.CHEACKKEY, NbtFubenmoney);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }


}
