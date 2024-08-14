package com.basketbandit.outfitter.database;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "outfits")
public class Outfit {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String imageFilename;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Item> items;

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String imageFilename() {
        return imageFilename;
    }

    public List<Item> items() {
        return items;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
