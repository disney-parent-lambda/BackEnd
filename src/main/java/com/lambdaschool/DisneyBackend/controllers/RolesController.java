package com.lambdaschool.DisneyBackend.controllers;

import com.lambdaschool.DisneyBackend.models.ErrorDetail;
import com.lambdaschool.DisneyBackend.models.Role;
import com.lambdaschool.DisneyBackend.services.RoleService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController
{
    private static final Logger logger = LoggerFactory.getLogger(RolesController.class);
    @Autowired
    RoleService roleService;

    @ApiOperation(value = "returns all Roles", response = Role.class, responseContainer = "List")
    @ApiImplicitParams({  @ApiImplicitParam(name = "page", dataType = "integr", paramType = "query",
            value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")})
    @GetMapping(value = "/roles",
                produces = {"application/json"})
    public ResponseEntity<?> listRoles(HttpServletRequest request)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Retrieves a Role associated with the RoleId.", response = Role.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Role Found", response = Role.class),
            @ApiResponse(code = 404, message = "Role Not Found", response = ErrorDetail.class)})
    @GetMapping(value = "/role/{roleId}",
                produces = {"application/json"})
    public ResponseEntity<?> getRole(HttpServletRequest request,
                                     @PathVariable
                                             Long roleId)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }




    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Creates a new Role.", notes = "The newly created Role id will be sent in the location header.", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Role Created Successfully", response = void.class),
            @ApiResponse(code = 500, message = "Error creating Role", response = ErrorDetail.class)})
    @PostMapping(value = "/role")
    public ResponseEntity<?> addNewRole(HttpServletRequest request, @Valid
    @RequestBody
            Role newRole) throws URISyntaxException
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        newRole = roleService.save(newRole);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{roleid}").buildAndExpand(newRole.getRoleid()).toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



    //-----------------------------------------------------------------------------------------------------------------



    @ApiOperation(value = "Deletes a Role by Role's ID", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Role Delete Successful", response = void.class),
            @ApiResponse(code = 500, message = "Error deleting Role" , response = void.class)})
    @DeleteMapping("/role/{id}")
    public ResponseEntity<?> deleteRoleById(HttpServletRequest request,
                                            @PathVariable
                                                    long id)
    {
        logger.trace(request.getMethod().toUpperCase() + " " + request.getRequestURI() + " accessed");

        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
