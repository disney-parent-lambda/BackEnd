package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Attractions;
import org.springframework.data.repository.CrudRepository;

public interface AttractionRepository extends CrudRepository<Attractions, Long>
{
}
