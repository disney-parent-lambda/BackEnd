package com.lambdaschool.DisneyBackend.services;


import com.lambdaschool.DisneyBackend.exceptions.ResourceNotFoundException;
import com.lambdaschool.DisneyBackend.models.Ticket;
import com.lambdaschool.DisneyBackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "ticketService")
public class TicketServiceImpl implements TicketService
{
    @Autowired
    private TicketRepository ticketrepo;

    @Override
    public List<Ticket> findAll()
    {
        List<Ticket> list = new ArrayList<>();
        ticketrepo.findAll().iterator().forEachRemaining(list::add);
        return list;
    }


    @Override
    public Ticket findTicketById(long id)
    {
        return ticketrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }

    @Override
    public List<Ticket> findByUserName(String username)
    {
        List<Ticket> list = new ArrayList<>();
        ticketrepo.findAll().iterator().forEachRemaining(list::add);

        list.removeIf(q -> !q.getUser().getUsername().equalsIgnoreCase(username));
        return list;


    }

    @Transactional
    @Override
    public Ticket save(Ticket ticket)
    {
        return ticketrepo.save(ticket);
    }

    @Override
    public void delete(long id)
    {
        if(ticketrepo.findById(id).isPresent())
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(ticketrepo.findById(id).get().getUser().getUsername().equalsIgnoreCase(authentication.getName()))
            {
                ticketrepo.deleteById(id);
            }else
            {
                throw new ResourceNotFoundException(id + " " + authentication.getName());
            }
        }
    }
}
