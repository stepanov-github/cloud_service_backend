package ru.home.hibernate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.home.hibernate.dto.request.AuthenticationRequest;
import ru.home.hibernate.dto.response.AuthenticationResponse;
import ru.home.hibernate.service.AuthenticationService;

@Validated
@RestController
@RequestMapping("/cloud")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody AuthenticationRequest Request) {

        return authenticationService.login(Request);

    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("auth-token") String token) {

        authenticationService.logout(token);

        return ResponseEntity.ok(HttpStatus.OK);

    }
}
