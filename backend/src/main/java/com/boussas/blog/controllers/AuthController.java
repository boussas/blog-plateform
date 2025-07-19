package com.boussas.blog.controllers;

import com.boussas.blog.entities.dtos.AuthResponse;
import com.boussas.blog.entities.dtos.LoginRequest;
import com.boussas.blog.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        UserDetails userDetails = authenticationService
                .authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        String tokenVal = authenticationService.generateToken(userDetails);
        AuthResponse auth = AuthResponse.builder().token(tokenVal).expiresIn(86400).build();
        return ResponseEntity.ok(auth);
    }
}