package com.lambdaschool.DisneyBackend.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@ApiModel(value = "Attractions" , description = "The Attractions Entity ")
@Entity
@Table(name = "attractions")
public class Attractions extends Auditable
{

    @ApiModelProperty(name = "attractionId", value = "attractionId", required = true, example = "01")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attractionid;

    @ApiModelProperty(name = "AttractionName", value = "AttractionName", required = true, example ="Splash Mountain")
    @Column(nullable = false)
    private String attraction;

    public Attractions()
    {
    }

    public Attractions(String attraction)
    {
        this.attraction = attraction;
    }

    public long getAttractionid()
    {
        return attractionid;
    }

    public void setAttractionid(long attractionid)
    {
        this.attractionid = attractionid;
    }

    public String getAttraction()
    {
        return attraction;
    }

    public void setAttraction(String attraction)
    {
        this.attraction = attraction;
    }
}
