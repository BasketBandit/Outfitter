package com.basketbandit.outfitter.database;

import org.springframework.data.repository.ListCrudRepository;

public interface OutfitRepository extends ListCrudRepository<Outfit, String> {
    Outfit getByItemsContaining(Item item);
}