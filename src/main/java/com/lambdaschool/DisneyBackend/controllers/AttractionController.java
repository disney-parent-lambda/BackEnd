package com.lambdaschool.DisneyBackend.controllers;


import com.lambdaschool.DisneyBackend.models.Attractions;
import com.lambdaschool.DisneyBackend.services.AttractionService;
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
@RequestMapping("/attractions")
public class AttractionController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    AttractionService attractionsService;

    @GetMapping(value = "/attractions", produces = {"application/json"})
    public ResponseEntity<?> listAllAttractions(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Attractions> allattractions = attractionsService.findAll();
        return new ResponseEntity<>(allattractions, HttpStatus.OK);
    }

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
