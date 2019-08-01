package com.lambdaschool.DisneyBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// User is considered the parent entity
@ApiModel(value = "User", description = "The Users Entity")
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"authority", "userRoles"})
public class User extends Auditable
{
    @ApiModelProperty(name = "UserId", value = "UserId" , required = true , example = "01")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    @ApiModelProperty(name = "username", value = "username" , required = true , example = "username:Carlossantana987")
    @Column(nullable = false,
            unique = true)
    private String username;

    @ApiModelProperty(name = "password", value = "password" , required = true , example = "password:2019pass")
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(name = "userRoles", value = "userRoles" , required = true , example = "admin")
    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<UserRoles> userRoles = new ArrayList<>();

    @ApiModelProperty(name = "ticket", value = "ticket" , required = true)
    @OneToMany(mappedBy = "user",
               cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private List<Ticket> tickets = new ArrayList<>();

    public User()
    {
    }



    public User(String username, String password, List<UserRoles> userRoles)
    {
        setUsername(username);
        setPassword(password);
        for (UserRoles ur : userRoles)
        {
            ur.setUser(this);
        }
        this.userRoles = userRoles;

    }

    public long getUserid()
    {
        return userid;
    }

    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    public List<UserRoles> getUserRoles()
    {
        return userRoles;
    }

    public void setUserRoles(List<UserRoles> userRoles)
    {
        this.userRoles = userRoles;
    }

    public List<Ticket> getTickets()
    {
        return tickets ;
    }

    public void setTickets(List<Ticket> tickets)
    {
        this.tickets = tickets;
    }

    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.userRoles)
        {
            String myRole = "ROLE_" + r.getRole().getName().toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }
}
