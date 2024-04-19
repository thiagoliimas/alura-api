package com.aluraapi.aluraapi.controllers;

import com.aluraapi.aluraapi.domain.user.AuthResponse;
import com.aluraapi.aluraapi.domain.user.User;
import com.aluraapi.aluraapi.dtos.AuthenticationDTO;
import com.aluraapi.aluraapi.dtos.RegisterDTO;
import com.aluraapi.aluraapi.dtos.UserDTO;
import com.aluraapi.aluraapi.infra.exceptions.InvalidUsernameException;
import com.aluraapi.aluraapi.infra.security.TokenService;
import com.aluraapi.aluraapi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AuthenticationDTO authenticationDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username(), authenticationDTO.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
