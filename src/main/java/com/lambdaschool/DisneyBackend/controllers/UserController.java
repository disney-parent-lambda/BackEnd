package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.ErrorDetail;
import com.lambdaschool.DisneyBackend.models.User;
import com.lambdaschool.DisneyBackend.services.UserService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);

    @Autowired
    private UserService userService;


    @ApiOperation(value = "returns all Users", response = User.class, responseContainer = "List")
    @ApiImplicitParams({  @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/users",
                produces = {"application/json"})
    public ResponseEntity<?> listAllUsers(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }




    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Retrieves a User associated with the UserId.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Found", response = User.class),
            @ApiResponse(code = 404, message = "User Not Found", response = ErrorDetail.class)})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/user/{userId}",
                produces = {"application/json"})
    public ResponseEntity<?> getUser(HttpServletRequest request,
                                     @PathVariable
                                             Long userId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }




    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Retrieves a User associated with the user's name.", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "username Found", response = User.class),
            @ApiResponse(code = 404, message = "username Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/getusername",
                produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(HttpServletRequest request, Authentication authentication)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Creates a new User.", notes = "The newly created User id will be sent in the location header.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating User", response = ErrorDetail.class)})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/user",
                 consumes = {"application/json"},
                 produces = {"application/json"})
    public ResponseEntity<?> addNewUser(HttpServletRequest request, @Valid
    @RequestBody
            User newuser) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newuser = userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "updates an existing User.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User updated Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error updating User", response = ErrorDetail.class)})
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<?> updateUser(HttpServletRequest request,
                                        @RequestBody
                                                User updateUser,
                                        @PathVariable
                                                long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }




    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Deletes a user by user's ID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User Delete Successful", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting User" , response = void.class)})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUserById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}