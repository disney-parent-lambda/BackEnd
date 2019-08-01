package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.Ticket;
import com.lambdaschool.DisneyBackend.services.TicketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
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

    @PostMapping(value = "/postticket")
    public ResponseEntity<?> postTicket(HttpServletRequest request, @Valid @RequestBody Ticket newTicket) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newTicket = ticketService.save(newTicket);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newTicketURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{ticketid}").buildAndExpand(newTicket.getTicketid()).toUri();responseHeaders.setLocation(newTicketURI);

        return new ResponseEntity<>("Ticket Submitted", responseHeaders, HttpStatus.CREATED);
    }

}
