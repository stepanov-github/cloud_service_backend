package ru.home.hibernate.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.home.hibernate.dto.request.AuthenticationRequest;
import ru.home.hibernate.dto.response.AuthenticationResponse;
import ru.home.hibernate.jwt.JwtService;

@Service
public class AuthenticationService {

    private final CustomUserDetailsService UserDetailsService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final InMemoryBlackListToken tokenBlacklist;

    public AuthenticationService(CustomUserDetailsService userDetailsService, JwtService jwtService, AuthenticationManager authenticationManager, InMemoryBlackListToken tokenBlacklist) {
        UserDetailsService = userDetailsService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenBlacklist = tokenBlacklist;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = UserDetailsService.loadUserByUsername(username);

        String token = jwtService.generateToken(userDetails);

        return new AuthenticationResponse(token);

    }

    public void logout(String token) {
        tokenBlacklist.addToBlacklist(token);
    }
}
