package com.ipedg.minecraft.entity;

import java.util.ArrayList;

public class FubenEntity {

    private String FubenWorldName;
    private String FubenName;
    private int FubenItemid;
    private int FubenChildId;
    private ArrayList<String> Lores;
    private ArrayList<String> anticmd;
    private int FubenNeedMoney;
    private String NeedItemLore;
    private int MaxLevel;
    private int MinLevel;
    private int RestartMoney;
    private int RestartCount;
    private int x;
    private int y;
    private int z;


    public FubenEntity(String fubenWorldName) {
        FubenWorldName = fubenWorldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FubenEntity that = (FubenEntity) o;
        return FubenWorldName.equals(that.FubenWorldName);
    }

    @Override
    public int hashCode() {
        return FubenWorldName.hashCode();
    }

    @Override
    public String toString() {
        return "FubenEntity{" +
                "FubenWorldName='" + FubenWorldName + '\'' +
                ", FubenName='" + FubenName + '\'' +
                ", FubenItemid=" + FubenItemid +
                ", FubenChildId=" + FubenChildId +
                ", Lores=" + Lores +
                ", FubenNeedMoney=" + FubenNeedMoney +
                ", NeedItemLore='" + NeedItemLore + '\'' +
                ", MaxLevel=" + MaxLevel +
                ", MinLevel=" + MinLevel +
                ", RestartMoney=" + RestartMoney +
                ", RestartCount=" + RestartCount +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getFubenWorldName() {
        return FubenWorldName;
    }

    public void setFubenWorldName(String fubenWorldName) {
        FubenWorldName = fubenWorldName;
    }

    public String getFubenName() {
        return FubenName;
    }

    public void setFubenName(String fubenName) {
        FubenName = fubenName;
    }

    public int getFubenItemid() {
        return FubenItemid;
    }

    public void setFubenItemid(int fubenItemid) {
        FubenItemid = fubenItemid;
    }

    public int getFubenChildId() {
        return FubenChildId;
    }

    public void setFubenChildId(int fubenChildId) {
        FubenChildId = fubenChildId;
    }

    public ArrayList<String> getLores() {
        return Lores;
    }

    public void setLores(ArrayList<String> lores) {
        Lores = lores;
    }

    public int getFubenNeedMoney() {
        return FubenNeedMoney;
    }

    public void setFubenNeedMoney(int fubenNeedMoney) {
        FubenNeedMoney = fubenNeedMoney;
    }

    public String getNeedItemLore() {
        return NeedItemLore;
    }

    public void setNeedItemLore(String needItemLore) {
        NeedItemLore = needItemLore;
    }

    public int getMaxLevel() {
        return MaxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        MaxLevel = maxLevel;
    }

    public int getMinLevel() {
        return MinLevel;
    }

    public void setMinLevel(int minLevel) {
        MinLevel = minLevel;
    }

    public int getRestartMoney() {
        return RestartMoney;
    }

    public void setRestartMoney(int restartMoney) {
        RestartMoney = restartMoney;
    }

    public int getRestartCount() {
        return RestartCount;
    }

    public void setRestartCount(int restartCount) {
        RestartCount = restartCount;
    }

    public ArrayList<String> getAnticmd() {
        return anticmd;
    }

    public void setAnticmd(ArrayList<String> anticmd) {
        this.anticmd = anticmd;
    }



    public FubenEntity(String fubenWorldName, String fubenName, int fubenItemid, int fubenChildId, ArrayList<String> lores, ArrayList<String> anticmd, int fubenNeedMoney, String needItemLore, int maxLevel, int minLevel, int restartMoney, int restartCount, int x, int y, int z) {
        FubenWorldName = fubenWorldName;
        FubenName = fubenName;
        FubenItemid = fubenItemid;
        FubenChildId = fubenChildId;
        Lores = lores;
        this.anticmd = anticmd;
        FubenNeedMoney = fubenNeedMoney;
        NeedItemLore = needItemLore;
        MaxLevel = maxLevel;
        MinLevel = minLevel;
        RestartMoney = restartMoney;
        RestartCount = restartCount;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
