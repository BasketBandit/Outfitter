package com.basketbandit.outfitter.database;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Arrays;
import java.util.List;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String subcategory;
    private Integer size;
    private String[] seasons;
    private String imageFilename;

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

    public List<String> seasons() {
        return Arrays.asList(seasons);
    }

    public String imageFilename() {
        return imageFilename;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public void setSeasons(String[] seasons) {
        this.seasons = seasons;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}
