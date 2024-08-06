package com.basketbandit.outfitter.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String category;
    private String subcategory;
    private String imagePath;

    public Item() {}

    public Item(String name, String category, String subcategory, String imagePath) {
        this.name = name;
        this.category = category;
        this.subcategory = subcategory;
        this.imagePath = imagePath;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String category() {
        return category;
    }

    public String subcategory() {
        return subcategory;
    }

    public String imagePath() {
        return imagePath;
    }
}
