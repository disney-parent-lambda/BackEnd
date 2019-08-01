package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.DisneyBackend.models.Attractions;
import com.lambdaschool.DisneyBackend.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "attractionService")
public class AttractionServiceImpl implements AttractionService
{
    @Autowired
    private AttractionRepository attractionrepo;

    @Override
    public List<Attractions> findAll()
    {
        List<Attractions> list = new ArrayList<>();
        attractionrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Attractions findAttractionById(long id)
    {
        return attractionrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));

    }

    @Override
    public List<Attractions> findByAttractionByName(String attraction)
    {
        List<Attractions> list = new ArrayList<>();
        attractionrepo.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(r -> !r.getAttraction().equalsIgnoreCase(attraction));
        return list;
    }

    @Override
    public void save(Attractions restaurant)
    {
        attractionrepo.save(restaurant);
    }

}
