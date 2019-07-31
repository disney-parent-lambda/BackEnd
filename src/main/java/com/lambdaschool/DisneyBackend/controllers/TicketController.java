package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.Ticket;
import com.lambdaschool.DisneyBackend.services.TicketService;
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
@RequestMapping("/tickets")
public class TicketController
{
    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    TicketService ticketService;


    @GetMapping(value = "/tickets", produces = {"application/json"})
    public ResponseEntity<?> listAllTickets(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Ticket> allTickets = ticketService.findAll();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }

    @GetMapping(value = "/ticket/{ticketid}",produces = {"application/json"})
    public ResponseEntity<?> getTicketId(HttpServletRequest request, @PathVariable Long ticketid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Ticket t = ticketService.findTicketById(ticketid);
        return new ResponseEntity<>(t, HttpStatus.OK);

    }

    @GetMapping(value = "/username/{username}", produces = {"application/json"})
    public ResponseEntity<?> findTicketByUserName(HttpServletRequest request,
                                                  @PathVariable String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Ticket> theTickets = ticketService.findByUserName(userName);
        return new ResponseEntity<>(theTickets, HttpStatus.OK);
    }
}
