package com.lambdaschool.DisneyBackend.services;


import com.lambdaschool.DisneyBackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.DisneyBackend.models.Restaurant;
import com.lambdaschool.DisneyBackend.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "restaurantService")
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restrauntrepo;

    @Override
    public List<Restaurant> findAll()
    {
        List<Restaurant> list = new ArrayList<>();
        restrauntrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Restaurant findRestaurantById(long id)
    {
        return restrauntrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

    }

    @Override
    public List<Restaurant> findByRestaurantByName(String restaurant)
    {
        List<Restaurant> list = new ArrayList<>();
        restrauntrepo.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(r -> !r.getRestaurant().equalsIgnoreCase(restaurant));
        return list;
    }

    @Override
    public void save(Restaurant restaurant)
    {
        restrauntrepo.save(restaurant);
    }
}
