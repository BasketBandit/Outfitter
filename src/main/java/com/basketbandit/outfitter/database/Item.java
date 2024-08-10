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
    private String subcategory;
    private String imagePath;
    private int size;
    public String[] seasons;

    public Item() {}

    /*
    Rename imagePath to imageFilename since path will be static
     */
    public Item(String name, String subcategory, String imagePath, int size, String[] seasons) {
        this.name = name;
        this.subcategory = subcategory;
        this.imagePath = imagePath;
        this.size = size;
        this.seasons = seasons;
    }

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String subcategory() {
        return subcategory;
    }

    public int size() {
        return size;
    }

    public String[] seasons() {
        return seasons;
    }

    public String imagePath() {
        return imagePath;
    }
}
