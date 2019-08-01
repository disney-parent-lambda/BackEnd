package com.lambdaschool.DisneyBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@ApiModel(value = "Roles", description = "The Roles Entity")
@Entity
@Table(name = "roles")
public class Role extends Auditable
{

    @ApiModelProperty(name= "roleId", value = "roleId", required = true, example = "01")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long roleid;

    @ApiModelProperty(name = "Name", value = "Name", required = true, example = "user")
    @Column(nullable = false,
            unique = true)
    private String name;

    @ApiModelProperty(name = "userRoles", value = "userRoles" , required = true , example = "admin")
    @OneToMany(mappedBy = "role",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("role")
    private List<UserRoles> userRoles = new ArrayList<>();

    public Role()
    {
    }

    public Role(String name)
    {
        this.name = name;
    }

    public long getRoleid()
    {
        return roleid;
    }

    public void setRoleid(long roleid)
    {
        this.roleid = roleid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }
}
