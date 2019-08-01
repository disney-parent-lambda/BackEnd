package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Attractions;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface AttractionRepository extends PagingAndSortingRepository<Attractions, Long>
{
}
