package ru.home.hibernate.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("auth-token")
    private String token;


    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
