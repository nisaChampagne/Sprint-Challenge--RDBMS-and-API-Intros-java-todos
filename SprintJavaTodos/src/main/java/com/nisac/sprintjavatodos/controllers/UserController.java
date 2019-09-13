package com.nisac.sprintjavatodos.controllers;


import com.nisac.sprintjavatodos.models.User;
import com.nisac.sprintjavatodos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{

    @Autowired
    private UserService userService;

    ///localhost:5005/users/viewall   GET
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/viewall", produces = {"application/json"})
    public ResponseEntity<?> listAllUsers()
    {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    ///localhost:5005/users/view/{userId}    GET
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping(value = "/view/{userId}", produces = {"application/json"})
    public ResponseEntity<?> getUser(@PathVariable
                                             Long userId)
    {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    ///localhost:5005/users/getusername     GET
    @GetMapping(value = "/getusername", produces = {"application/json"})
    @ResponseBody
    public ResponseEntity<?> getCurrentUserName(Authentication authentication)
    {
        return new ResponseEntity<>(userService.findUserByName(authentication.getName()), HttpStatus.OK);
        // return new ResponseEntity<>(userService.findUserByName(authentication.getName()).getUserid(), HttpStatus.OK);
    }

    ///localhost:5005/users/add    POST
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addNewUser(@Valid @RequestBody
                                                User newuser) throws URISyntaxException
    {
        newuser =  userService.save(newuser);

        // set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(newuser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    ///localhost:5005/users/update/{id}    PUT
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@RequestBody
                                                User updateUser, @PathVariable
                                                long id)
    {
        userService.update(updateUser, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    ///localhost:5005/users/delete/{id}    DELETE
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable
                                                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
