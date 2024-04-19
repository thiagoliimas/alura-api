package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.infra.exceptions.InvalidUsernameException;
import com.aluraapi.aluraapi.services.UserService;
import com.aluraapi.aluraapi.views.Views;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    @JsonView(Views.Public.class)
    public ResponseEntity<User> getByUsername(@RequestParam("username") String username) {
        User user = this.userService.findUserByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) throws InvalidUsernameException {

        if(this.userService.findByUsername(userDTO.username()) != null){
            return ResponseEntity.badRequest().build();
        }

        var encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.password());
        UserDTO newUserDTO = new UserDTO(userDTO.name(), userDTO.username(), userDTO.email(), encryptedPassword, userDTO.role());

        return new ResponseEntity<>(this.userService.createUser(newUserDTO), HttpStatus.CREATED);
    }
}
