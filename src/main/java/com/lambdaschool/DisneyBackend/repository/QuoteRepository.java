package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Quote;
import org.springframework.data.repository.CrudRepository;

public interface QuoteRepository extends CrudRepository<Quote, Long>
{

}
