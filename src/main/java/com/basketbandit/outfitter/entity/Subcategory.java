package com.basketbandit.outfitter.entity;

import com.basketbandit.outfitter.database.Item;

import java.util.HashMap;

public class Subcategory {
    private final String name;
    private final HashMap<Integer, Item> items = new HashMap<>();

    public Subcategory(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public HashMap<Integer, Item> items() {
        return items;
    }

    public void addItem(Item item) {
        this.items.put(item.id(), item);
    }
}
