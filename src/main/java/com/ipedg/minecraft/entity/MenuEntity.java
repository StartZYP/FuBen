package com.ipedg.minecraft.entity;

import java.util.ArrayList;


public class MenuEntity {
    private String menutitle;
    private ArrayList<String> menulist;
    private String menucmd;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuEntity that = (MenuEntity) o;
        return menucmd.equalsIgnoreCase(that.menucmd);
    }

    @Override
    public int hashCode() {
        return menucmd.hashCode();
    }

    @Override
    public String toString() {
        return "MenuEntity{" +
                "menutitle='" + menutitle + '\'' +
                ", menulist=" + menulist +
                ", menucmd='" + menucmd + '\'' +
                '}';
    }

    public String getMenutitle() {
        return menutitle;
    }

    public void setMenutitle(String menutitle) {
        this.menutitle = menutitle;
    }

    public ArrayList<String> getMenulist() {
        return menulist;
    }

    public void setMenulist(ArrayList<String> menulist) {
        this.menulist = menulist;
    }

    public String getmenucmd() {
        return menucmd;
    }

    public void setmenucmd(String menucmd) {
        this.menucmd = menucmd;
    }

    public MenuEntity(String menutitle, ArrayList<String> menulist, String menucmd) {
        this.menutitle = menutitle;
        this.menulist = menulist;
        this.menucmd = menucmd;
    }

    public MenuEntity(String menucmd) {
        this.menucmd = menucmd;
    }
}
