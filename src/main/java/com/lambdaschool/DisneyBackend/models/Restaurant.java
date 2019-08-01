package com.lambdaschool.DisneyBackend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "Restaurants", description = "The Restaurant Entity")
@Entity
@Table(name = "resturant")
public class Restaurant extends Auditable
{
    @ApiModelProperty(name = "restaurantid", value = "primary key for Restaurant", required = true, example = "01")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long restaurantid;

    @ApiModelProperty(name = "name", value = "Restaurant Name", required = true, example = "Same Name")
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
