package com.basketbandit.outfitter.entity;

import com.basketbandit.outfitter.database.Item;
import com.basketbandit.outfitter.database.Outfit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class Wardrobe {
    private final List<Outfit> outfits = new ArrayList<>();
    private final List<Category> categories = new ArrayList<>();

    public Wardrobe(HashMap<String, List<String>> categories, List<Item> items, List<Outfit> outfits) {
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

        this.outfits.addAll(outfits);
    }

    public void addItem(Item item) {
        categories.forEach(category -> category.subcategories().forEach(subcategory -> {
            if(item.subcategory().equalsIgnoreCase(subcategory.name())) {
                subcategory.addItem(item);
            } else {
                subcategory.items().remove(item.itemId());
            }
        }));
    }

    public void addOutfit(Outfit outfit) {
        this.outfits.add(outfit);
    }

    public List<Category> categories() {
        return categories;
    }

    public List<Outfit> outfits() {
        return outfits;
    }

    public int size() {
        int total = 0;
        for(Category category : categories) {
            total += category.size();
        }
        return total;
    }
}
