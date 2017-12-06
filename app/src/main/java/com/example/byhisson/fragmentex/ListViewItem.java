package com.example.byhisson.fragmentex;


import android.graphics.drawable.Drawable;

/**
 * Created by byhisson on 2017. 11. 30..
 */


public class ListViewItem {

    private Drawable iconDrawble;
    private String name;

    public void setIconDrawble(Drawable iconDrawble) {
        this.iconDrawble = iconDrawble;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getIconDrawble() {
        return this.iconDrawble;
    }

    public String getName() {
        return this.name;
    }

}
