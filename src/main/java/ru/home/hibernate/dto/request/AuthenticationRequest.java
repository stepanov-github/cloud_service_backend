package ru.home.hibernate.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @JsonProperty("login")
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public @NotBlank String getPassword() {
        return password;
    }
}

