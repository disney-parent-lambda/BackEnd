package com.lambdaschool.DisneyBackend;

import com.lambdaschool.DisneyBackend.models.*;
import com.lambdaschool.DisneyBackend.services.AttractionService;
import com.lambdaschool.DisneyBackend.services.RestaurantService;
import com.lambdaschool.DisneyBackend.services.RoleService;
import com.lambdaschool.DisneyBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    AttractionService attractionService;


    @Override
    public void run(String[] args) throws Exception
    {

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        roleService.save(r1);
        roleService.save(r2);
        roleService.save(r3);

        //restaurants

        Restaurant rest1= new Restaurant("Cinderella's Royal Table");
        Restaurant rest2 =new Restaurant("Aloha Isle");
        Restaurant rest3 =new Restaurant("Aunt Polly's");
        Restaurant rest4 =new Restaurant("Auntie Gravity's Galactic Goodies");
        Restaurant rest5 =new Restaurant("Be Our Guest Restaurant");
        Restaurant rest6 =new Restaurant("Casey's Corner");
        Restaurant rest7 =new Restaurant("Cheshire Cafe");
        Restaurant rest8 =new Restaurant("Columbia Harbour House");
        Restaurant rest9 =new Restaurant("Cool Ship");
        Restaurant rest10=new Restaurant("Cosmic Ray's Starlight Cafe");
        Restaurant rest11=new Restaurant("Gaston's Tavern");
        Restaurant rest13=new Restaurant("Golden Oak Outpost");
        Restaurant rest14=new Restaurant("Jungle Navigation Co. Ltd. Skipper Canteen");
        Restaurant rest15=new Restaurant("Liberty Square Market");
        Restaurant rest16=new Restaurant("Liberty Tree Tavern");
        Restaurant rest17=new Restaurant("Main Street Bakery");
        Restaurant rest18=new Restaurant("Pecos Bill Tall Tale Inn and Cafe");
        Restaurant rest19=new Restaurant("Pinocchio Village Haus");
        Restaurant rest20=new Restaurant("Plaza Ice Cream Parlor");
        Restaurant rest21=new Restaurant("Prince Eric's Village Market");
        Restaurant rest22=new Restaurant("Sleepy Hollow");
        Restaurant rest23=new Restaurant("Storybook Treats");
        Restaurant rest24=new Restaurant("Sunshine Tree Terrace");
        Restaurant rest25=new Restaurant("The Crystal Palace");
        Restaurant rest26=new Restaurant("The Diamond Horseshoe");
        Restaurant rest27=new Restaurant("The Friar's Nook");
        Restaurant rest28=new Restaurant("The Lunching Pad");
        Restaurant rest29=new Restaurant("The Plaza Restaurant");
        Restaurant rest30=new Restaurant("Tomorrowland Terrace Restaurant");
        Restaurant rest31=new Restaurant("Tony's Town Square Restaurant");
        Restaurant rest32=new Restaurant("Tortuga Tavern");
        Restaurant rest33=new Restaurant("Westward Ho");

        restaurantService.save(rest1);
        restaurantService.save(rest2);
        restaurantService.save(rest3);
        restaurantService.save(rest4);
        restaurantService.save(rest5);
        restaurantService.save(rest6);
        restaurantService.save(rest7);
        restaurantService.save(rest8);
        restaurantService.save(rest9);
        restaurantService.save(rest10);
        restaurantService.save(rest11);
        restaurantService.save(rest13);
        restaurantService.save(rest14);
        restaurantService.save(rest15);
        restaurantService.save(rest16);
        restaurantService.save(rest17);
        restaurantService.save(rest18);
        restaurantService.save(rest19);
        restaurantService.save(rest20);
        restaurantService.save(rest21);
        restaurantService.save(rest22);
        restaurantService.save(rest23);
        restaurantService.save(rest24);
        restaurantService.save(rest25);
        restaurantService.save(rest26);
        restaurantService.save(rest27);
        restaurantService.save(rest28);
        restaurantService.save(rest29);
        restaurantService.save(rest30);
        restaurantService.save(rest31);
        restaurantService.save(rest32);
        restaurantService.save(rest33);

        Attractions  attr1 = new Attractions("Big Thunder Moutain Railroad");
        Attractions  attr2 = new Attractions("Disney Land Monorail");
        Attractions  attr3 = new Attractions("Haunted Mansion");
        Attractions  attr4 = new Attractions("Indiana Jones Adventure");
        Attractions  attr5 = new Attractions("It's A Small World");
        Attractions  attr6 = new Attractions("King Arthur Carrousel");
        Attractions  attr7 = new Attractions("Mad Tea Party");
        Attractions  attr8 = new Attractions("Matterhorn Bobsleds");
        Attractions attr10 = new Attractions("Main Street Cinema");
        Attractions attr11 = new Attractions("Main Street Vehicles");
        Attractions attr12 = new Attractions("Millennium Falcon:Smugglers Run");
        Attractions attr13 = new Attractions("Peter Pan's Flight");
        Attractions attr14 = new Attractions("Pirates of the Caribbean");
        Attractions attr15 = new Attractions("Space Mountain");
        Attractions attr16 = new Attractions("Splash Mountain");
        Attractions attr17 = new Attractions("Star Tours-The Adventures Continue");
        Attractions attr18 = new Attractions("Star Wars Launch Bay");

        attractionService.save(attr1);
        attractionService.save(attr2);
        attractionService.save(attr3);
        attractionService.save(attr4);
        attractionService.save(attr5);
        attractionService.save(attr6);
        attractionService.save(attr7);
        attractionService.save(attr8);
        attractionService.save(attr10);
        attractionService.save(attr11);
        attractionService.save(attr12);
        attractionService.save(attr13);
        attractionService.save(attr14);
        attractionService.save(attr15);
        attractionService.save(attr16);
        attractionService.save(attr17);
        attractionService.save(attr18);

        // admin, data, user
        ArrayList<UserRoles> admins = new ArrayList<>();
        admins.add(new UserRoles(new User(), r1));
        admins.add(new UserRoles(new User(), r2));
        admins.add(new UserRoles(new User(), r3));
        User u1 = new User("admin", "password", admins);
        u1.getTickets().add(new Ticket( "ticket1", "10:50", 3 , true , u1 , rest7, attr12));
        userService.save(u1);

        // data, user
        ArrayList<UserRoles> datas = new ArrayList<>();
        datas.add(new UserRoles(new User(), r3));
        datas.add(new UserRoles(new User(), r2));
        User u2 = new User("cinnamon", "1234567", datas);
        userService.save(u2);

        // user
        ArrayList<UserRoles> users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u3 = new User("barnbarn", "ILuvM4th!", users);
        u3.getTickets().add(new Ticket("Run Forest", "12:50", 18,false, u3 , rest4,attr17));
        userService.save(u3);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u4 = new User("Bob", "password", users);
        userService.save(u4);

        users = new ArrayList<>();
        users.add(new UserRoles(new User(), r2));
        User u5 = new User("Jane", "password", users);
        userService.save(u5);













    }
}