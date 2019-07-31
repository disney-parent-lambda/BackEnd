package com.lambdaschool.DisneyBackend.repository;

import com.lambdaschool.DisneyBackend.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long>
{
}
