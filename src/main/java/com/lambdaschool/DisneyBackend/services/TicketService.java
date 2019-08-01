package com.lambdaschool.DisneyBackend.services;

import com.lambdaschool.DisneyBackend.models.Ticket;

import java.util.List;

public interface TicketService
{
    List<Ticket> findAll();

    Ticket findTicketById(long id);

    List<Ticket> findByUserName(String username);

    List<Ticket> findTicketByStatus(boolean status);

//    List<Ticket> findTicketByTime();

    void delete(long id);

    Ticket save(Ticket ticket);
}
