package com.ansersion.hubing.huailin.adapter;

/**
 * Created by hubing on 2018/4/8.
 */

public class ClassifyListViewItem {
    private String title;
    private int price;

    public ClassifyListViewItem(String title, int price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }
}
