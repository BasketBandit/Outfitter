package com.basketbandit.outfitter.entity;

import com.basketbandit.outfitter.database.Item;

import java.util.ArrayList;
import java.util.List;

public class Subcategory {
    private final String name;
    private final List<Item> items = new ArrayList<>();

    public Subcategory(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public List<Item> items() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
