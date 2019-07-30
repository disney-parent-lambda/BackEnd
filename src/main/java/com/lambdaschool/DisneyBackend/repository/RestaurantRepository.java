package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Restaurant;
import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long>
{

}
