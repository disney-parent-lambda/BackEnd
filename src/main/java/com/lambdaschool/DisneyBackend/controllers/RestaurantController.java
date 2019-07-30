package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.Restaurant;
import com.lambdaschool.DisneyBackend.services.RestaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    RestaurantService restaurantService;

    @GetMapping(value = "/restaurants", produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Restaurant> allrestaurants = restaurantService.findAll();
        return new ResponseEntity<>(allrestaurants, HttpStatus.OK);
    }

    @GetMapping(value = "/restaurant/{restaurantid}")
        public ResponseEntity<?> getRestaurantId(HttpServletRequest request,
                                               @PathVariable
                                               Long restaurantid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        Restaurant r = restaurantService.findRestaurantById(restaurantid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
