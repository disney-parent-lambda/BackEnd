package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.models.Attractions;
import com.lambdaschool.DisneyBackend.models.Restaurant;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AttractionService
{
    List<Attractions> findAll(Pageable pageable);

    Attractions findAttractionById(long id);

    List<Attractions> findByAttractionByName(String Attractions);

    void save (Attractions attractions);
}
