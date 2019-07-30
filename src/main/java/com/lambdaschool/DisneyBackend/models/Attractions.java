package com.lambdaschool.DisneyBackend.models;

import javax.persistence.*;

@Entity
@Table(name = "attractions")
public class Attractions extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long attractionid;

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
