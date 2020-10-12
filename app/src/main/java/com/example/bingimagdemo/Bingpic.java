package com.example.bingimagdemo;

import java.util.ArrayList;

public class Bingpic {
    private ArrayList<Images> images;
    private Tooltips tooltips;

    public ArrayList<Images> getImages( ) {
        return images;
    }

    public void setImages(ArrayList<Images> images) {
        this.images = images;
    }

    public Tooltips getTooltips() {
        return tooltips;
    }

    public void setTooltips(Tooltips tooltips) {
        this.tooltips = tooltips;
    }
}
