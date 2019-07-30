package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.models.Restaurant;

import java.util.List;

public interface RestaurantService
{
    List<Restaurant> findAll();

    Restaurant findRestaurantById(long id);

    List<Restaurant> findByRestaurantByName(String Restaurant);

    void save (Restaurant restaurant);
}
