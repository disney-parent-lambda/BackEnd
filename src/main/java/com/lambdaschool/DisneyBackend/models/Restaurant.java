package com.lambdaschool.DisneyBackend.models;

import javax.persistence.*;


@Entity
@Table(name = "resturant")
public class Restaurant extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantid;

    @Column(nullable = false)
    private String restaurant;

    public Restaurant()
    {
    }

    public Restaurant(String restaurant)
    {
        this.restaurant = restaurant;
    }

    public long getRestaurantid()
    {
        return restaurantid;
    }

    public void setRestaurantid(long restaurantid)
    {
        this.restaurantid = restaurantid;
    }

    public String getRestaurant()
    {
        return restaurant;
    }

    public void setRestaurant(String restaurant)
    {
        this.restaurant = restaurant;
    }
}
