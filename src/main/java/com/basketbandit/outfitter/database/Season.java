package com.basketbandit.outfitter.database;

import jakarta.persistence.*;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;

    public Integer id() {
        return id;
    }

    public String name() {
        return name;
    }
}
