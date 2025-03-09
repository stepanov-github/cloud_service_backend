package ru.home.hibernate.dto.response;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;
    private int id;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, int id) {
        this.message = message;
        this.id = id;
    }
}
