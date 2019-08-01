package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Restaurant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;

import java.awt.print.Pageable;
import java.util.List;

public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long>
{
}
