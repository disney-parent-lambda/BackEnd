package com.lambdaschool.DisneyBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "tickets")
public class Ticket
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ticket_ID")
    private long ticketid;

    @Column(nullable = false)
    private String ticket;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable=false, updatable=false)
    @JsonIgnoreProperties({"tickets", "hibernateLazyInitializer"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable=false, updatable=false)
    @JsonIgnoreProperties({"ticket", "hibernateLazyInitializer"})
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attractionname",  insertable=false, updatable=false)
    @JsonIgnoreProperties({"ticket", "hibernateLazyInitializer"})
    private Attractions attractions;

    public Ticket()
    {
    }

    public Ticket(String ticket, User user, Restaurant restaurant, Attractions attractions)
    {
        this.ticket = ticket;
        this.user = user;
        this.restaurant = restaurant;
        this.attractions = attractions;
    }

    public long getTicketid()
    {
        return ticketid;
    }

    public void setTicketid(long ticketid)
    {
        this.ticketid = ticketid;
    }

    public String getTicket()
    {
        return ticket;
    }

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Restaurant getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant)
    {
        this.restaurant = restaurant;
    }

    public Attractions getAttractions()
    {
        return attractions;
    }

    public void setAttractions(Attractions attractions)
    {
        this.attractions = attractions;
    }
}
