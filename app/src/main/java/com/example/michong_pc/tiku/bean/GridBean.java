package com.example.michong_pc.tiku.bean;

import java.io.Serializable;

/**
 * Created by MiChong-pc on 2016/5/23.
 */
public class GridBean implements Serializable {
    public static final long SeriaVersionUUID = 201605231223L;
    private String name;
    private int icon;
    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
