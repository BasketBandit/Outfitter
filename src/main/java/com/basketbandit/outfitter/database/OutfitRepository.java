package com.basketbandit.outfitter.database;

import org.springframework.data.repository.ListCrudRepository;

public interface OutfitRepository extends ListCrudRepository<Outfit, Integer> {
    Outfit getByItemsContaining(Item item);
}