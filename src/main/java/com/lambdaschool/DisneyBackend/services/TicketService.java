package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.models.Ticket;

import java.util.List;

public interface TicketService
{
    List<Ticket> findAll();

    Ticket findTicketById(long id);

    List<Ticket> findByUserName(String username);

    void delete(long id);

    Ticket save(Ticket ticket);
}
