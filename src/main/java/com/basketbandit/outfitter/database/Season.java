package com.basketbandit.outfitter.database;

import jakarta.persistence.*;

@Entity
@Table(name = "seasons")
public class Season {
    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String id;
    private String name;

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }
}
