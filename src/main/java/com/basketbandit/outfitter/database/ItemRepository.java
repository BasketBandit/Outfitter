package com.basketbandit.outfitter.database;

import org.springframework.data.repository.ListCrudRepository;

public interface ItemRepository extends ListCrudRepository<Item, String> {
    Item getItemByName(String name);
}