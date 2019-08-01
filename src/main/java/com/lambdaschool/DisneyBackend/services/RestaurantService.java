package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.models.Restaurant;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService
{
    List<Restaurant> findAll(Pageable pageable);

    Restaurant findRestaurantById(long id);

    List<Restaurant> findByRestaurantByName(String Restaurant);

    void save (Restaurant restaurant);
}
