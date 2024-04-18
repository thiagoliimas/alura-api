package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.services.UserService;
import com.aluraapi.aluraapi.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws Exception {
        User newUser = this.service.createUser(userDTO);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<User> getByUsername(@RequestParam("username") String username) throws Exception {
        User user = this.service.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
