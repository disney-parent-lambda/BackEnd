package com.lambdaschool.DisneyBackend.controllers;


import com.lambdaschool.DisneyBackend.models.Attractions;
import com.lambdaschool.DisneyBackend.models.ErrorDetail;
import com.lambdaschool.DisneyBackend.services.AttractionService;
import io.swagger.annotations.*;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/attractions")
public class AttractionController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    AttractionService attractionsService;

    @ApiOperation(value = "returns all Attractions", response = Attractions.class, responseContainer = "List")
    @ApiImplicitParams({  @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/attractions", produces = {"application/json"})
    public ResponseEntity<?> listAllAttractions(@PageableDefault(page = 0, size = 5)
                                                            Pageable pageable)
    {
        List<Attractions> allattractions = attractionsService.findAll(pageable);
        return new ResponseEntity<>(allattractions, HttpStatus.OK);
    }


    //-----------------------------------------------------------------------------------------------------------------


    @ApiOperation(value = "Retrieves an Attraction associated with the Attraction Id.", response = Attractions.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Restaurant Found", response = Attractions.class),
            @ApiResponse(code = 404, message = "Restaurant Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/attraction/{attractionid}")
    public ResponseEntity<?> getattractionId(HttpServletRequest request,
                                           @PathVariable
                                                   Long attractionid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");
        Attractions r = attractionsService.findAttractionById(attractionid);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }


}
