package com.basketbandit.outfitter.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private final String name;
    private final List<Subcategory> subcategories = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public List<Subcategory> subcategories() {
        return subcategories;
    }

    public void addSubcategory(Subcategory subcategory) {
        this.subcategories.add(subcategory);
    }

    /**
     * Number of items found in the category.
     * @return int
     */
    public int size() {
        int total = 0;
        for(Subcategory subcategory : subcategories) {
            total += subcategory.items().size();
        }
        return total;
    }
}
