package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.Restaurant;
import com.lambdaschool.DisneyBackend.services.RestaurantService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    RestaurantService restaurantService;
    @ApiOperation(value = "returns all Restaurants", response = Restaurant.class, responseContainer = "List")
//    @ApiImplicitParms({  @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
//                                            value = "Results page you want to retrieve (0..N)"),
//                         @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
//                                            value = "Number of records per page."),
//                         @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
//                                            value = "Sorting criteria in the format: property(,asc|desc). " +
//                                                    "Default sort order is ascending. " +
//                                                    "Multiple sort criteria are supported.")})

    @GetMapping(value = "/restaurants", produces = {"application/json"})
    public ResponseEntity<?> listAllRestaurants(@PageableDefault(page = 0, size = 5)Pageable pageable)
    {

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
