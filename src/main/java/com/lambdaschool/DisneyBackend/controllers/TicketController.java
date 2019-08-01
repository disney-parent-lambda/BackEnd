package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.ErrorDetail;
import com.lambdaschool.DisneyBackend.models.Ticket;
import com.lambdaschool.DisneyBackend.services.TicketService;
import io.swagger.annotations.*;
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



    @ApiOperation(value = "returns all Restaurants", response = Ticket.class, responseContainer = "List")
    @ApiImplicitParams({  @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/tickets", produces = {"application/json"})
    public ResponseEntity<?> listAllTickets(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Ticket> allTickets = ticketService.findAll();
        return new ResponseEntity<>(allTickets, HttpStatus.OK);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Retrieves a restaurant associated with the restaurantid.", response = Ticket.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Restaurant Found", response = Ticket.class),
            @ApiResponse(code = 404, message = "Restaurant Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/ticket/{ticketid}",produces = {"application/json"})
    public ResponseEntity<?> getTicketId(HttpServletRequest request, @PathVariable Long ticketid)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Ticket t = ticketService.findTicketById(ticketid);
        return new ResponseEntity<>(t, HttpStatus.OK);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Retrieves a ticket associated with the username.", response = Ticket.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Restaurant Found", response = Ticket.class),
            @ApiResponse(code = 404, message = "Restaurant Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/username/{username}", produces = {"application/json"})
    public ResponseEntity<?> findTicketByUserName(HttpServletRequest request,
                                                  @PathVariable String userName)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Ticket> theTickets = ticketService.findByUserName(userName);
        return new ResponseEntity<>(theTickets, HttpStatus.OK);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Creates a new Ticket.", notes = "The newly created Ticket id will be sent in the location header.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Ticket Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating Ticket", response = ErrorDetail.class)})
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
