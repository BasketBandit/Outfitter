package com.basketbandit.outfitter.database;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "outfits")
public class Outfit {
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    private String id;
    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Season> seasons;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Item> items;
    private String imageFilename;

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public List<Season> seasons() {
        return seasons;
    }

    public List<Item> items() {
        return items;
    }

    public String imageFilename() {
        return imageFilename;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }
}
