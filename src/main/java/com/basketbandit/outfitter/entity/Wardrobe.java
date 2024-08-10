package com.basketbandit.outfitter.entity;

import com.basketbandit.outfitter.database.Item;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Wardrobe {
    private final List<Category> categories = new ArrayList<>();

    public Wardrobe(HashMap<String, List<String>> categories, List<Item> items) {
        categories.keySet().forEach(c -> {
            Category category = new Category(c);
            categories.get(c).forEach(s -> {
                Subcategory subcategory = new Subcategory(s);
                items.forEach(item -> {
                    if(item.subcategory().equals(s)) {
                        subcategory.addItem(item);
                    }
                });
                category.addSubcategory(subcategory);
            });
            this.categories.add(category);
        });
    }

    public void addItem(Item item) {
        categories.forEach(category -> category.subcategories().forEach(subcategory -> {
            if(item.subcategory().equalsIgnoreCase(subcategory.name())) {
                subcategory.addItem(item);
            } else {
                subcategory.items().remove(item.id());
            }
        }));
    }

    public List<Category> categories() {
        return categories;
    }

    public int size() {
        int total = 0;
        for(Category category : categories) {
            total += category.size();
        }
        return total;
    }
}
