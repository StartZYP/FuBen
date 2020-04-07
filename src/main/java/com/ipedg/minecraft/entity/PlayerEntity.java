package com.ipedg.minecraft.entity;

public class PlayerEntity {
    private String PlayerName;
    private FubenEntity fuben;
    private String WorldName;
    private long quittime = 0;
    private int nowRestartCount = 0;
    private int Status = 0;


    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "PlayerEntity{" +
                "PlayerName='" + PlayerName + '\'' +
                ", fuben=" + fuben +
                ", WorldName='" + WorldName + '\'' +
                ", quittime=" + quittime +
                ", nowRestartCount=" + nowRestartCount +
                ", Status=" + Status +
                '}';
    }

    public long getQuittime() {
        return quittime;
    }

    public void setQuittime(long quittime) {
        this.quittime = quittime;
    }

    public String getPlayerName() {
        return PlayerName;
    }

    public void setPlayerName(String playerName) {
        PlayerName = playerName;
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

    public int getNowRestartCount() {
        return nowRestartCount;
    }

    public void setNowRestartCount(int nowRestartCount) {
        this.nowRestartCount = nowRestartCount;
    }

    public PlayerEntity(String playerName, FubenEntity fuben, String worldName) {
        PlayerName = playerName;
        this.fuben = fuben;
        WorldName = worldName;
    }

}
