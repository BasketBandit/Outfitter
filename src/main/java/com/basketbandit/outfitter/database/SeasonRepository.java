package com.basketbandit.outfitter.database;

import org.springframework.data.repository.ListCrudRepository;

public interface SeasonRepository extends ListCrudRepository<Season, Integer> {
    Season getSeasonByName(String name);
}