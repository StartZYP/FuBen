package com.ipedg.minecraft.entity;

public class PlayerEntity {
    private String PlayerName;
    private String PlayerStats;
    private boolean IsDeath;
    private FubenEntity fuben;
    private String WorldName;


    @Override
    public String toString() {
        return "PlayerEntity{" +
                "PlayerName='" + PlayerName + '\'' +
                ", PlayerStats='" + PlayerStats + '\'' +
                ", IsDeath=" + IsDeath +
                ", fuben=" + fuben +
                ", WorldName='" + WorldName + '\'' +
                '}';
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    public String getPlayerStats() {
        return PlayerStats;
    }

    public void setPlayerStats(String playerStats) {
        PlayerStats = playerStats;
    }

    public boolean isDeath() {
        return IsDeath;
    }

    public void setDeath(boolean death) {
        IsDeath = death;
    }

    public FubenEntity getFuben() {
        return fuben;
    }

    public void setFuben(FubenEntity fuben) {
        this.fuben = fuben;
    }

    public String getWorldName() {
        return WorldName;
    }

    public void setWorldName(String worldName) {
        WorldName = worldName;
    }

    public PlayerEntity(String playerName, String playerStats, boolean isDeath, FubenEntity fuben, String worldName) {
        PlayerName = playerName;
        PlayerStats = playerStats;
        IsDeath = isDeath;
        this.fuben = fuben;
        WorldName = worldName;
    }
}
