package com.ipedg.minecraft.view;

import com.ipedg.minecraft.Fuben;
import com.ipedg.minecraft.entity.FubenEntity;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class FubenMenu {

    public static void InitInventorymenu(){
        Fuben.menuall.clear();
        Set<String> menus = Fuben.menu.keySet();
        for (String menu:menus){
            Fuben.menuall.put(menu,CreateInventory(menu,Fuben.menu.get(menu)));
        }
    }
    public static Inventory CreateInventory(String Title,ArrayList<String> Fubenname){
        Inventory inventory = Bukkit.createInventory(null, 26, Title);
        ArrayList<FubenEntity> tmpfb = new ArrayList<>();
        for (String fuben:Fubenname){
            tmpfb.add(new FubenEntity(fuben));
        }
        List<FubenEntity> newfuben = Fuben.fuben.stream().filter(tmpfb::contains).collect(Collectors.toList());
        ItemStack redblock = itemStackinit("红色玻璃",new ArrayList<>(),160,3);
        for (int i=0;i<=9;i++){
            inventory.setItem(i,redblock);
        }
        int b =10;
        for(FubenEntity fubenEntity:newfuben){
            inventory.setItem(b,itemStackinit(fubenEntity.getFubenName(),fubenEntity.getLores(),fubenEntity.getFubenItemid(),fubenEntity.getFubenChildId()));
            b+=1;
        }
        for (int i=19;i<=26;i++){
            inventory.setItem(i,redblock);
        }
        return inventory;
    }

    private static ItemStack itemStackinit(String Name, ArrayList<String> lore, int id, int childid){
        ItemStack item = new ItemStack(id, 1, (short) childid);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(Name);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }


}
