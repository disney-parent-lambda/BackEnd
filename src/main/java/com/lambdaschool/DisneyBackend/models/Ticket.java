package com.lambdaschool.DisneyBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiResponse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ApiModel(value = "Restaurants", description = "The Tickets Entity")
@Entity
@Table(name = "tickets")
public class Ticket
{

    @ApiModelProperty(name =" TicketID", value = "primary key for Restaurant", required = true, example = "01")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Ticket_ID")
    private long ticketid;

    @ApiModelProperty(name = "TicketMessage", value = "Ticket Description", required = true, example = "Hello World")
    @NotNull
    @Size(max = 200)
    @Column(nullable = false)
    private String ticket;

    @ApiModelProperty(name = "Ticket time request", value = "TicketTime" , required = true, example = "10:15")
    private String time;

    @ApiModelProperty(name = "Kid Count", value = "Kid Count", required = true , example = "13")
    private int kidCount;

    @ApiModelProperty(name = "Ticket Status", value = "status", required = true , example = "true/false")
    private boolean status;

    @ApiModelProperty(name = "User", value = "User", required = true , example = "Username:Carlos")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    @JsonIgnoreProperties({"tickets", "hibernateLazyInitializer"})
    private User user;

    @ApiModelProperty(name = "Restaurant", value = "Restaurant", required = true , example = "Restaurant:Olive Garden")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "useridR")
    @JsonIgnoreProperties({"ticket", "hibernateLazyInitializer"})
    private Restaurant restaurant;

    @ApiModelProperty(name = "Attraction", value = "Attraction", required = true , example = "Attraction:Rock'en Roller Coaster")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "useridA")
    @JsonIgnoreProperties({"ticket", "hibernateLazyInitializer"})
    private Attractions attractions;

    public Ticket()
    {
    }

    public Ticket(@NotNull @Size(max = 200) String ticket, String time, int kidCount, boolean status, User user, Restaurant restaurant, Attractions attractions)
    {
        this.ticket = ticket;
        this.time = time;
        this.kidCount = kidCount;
        this.status = status;
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

    public int getKidCount()
    {
        return kidCount;
    }

    public void setKidCount(int kidCount)
    {
        this.kidCount = kidCount;
    }

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
