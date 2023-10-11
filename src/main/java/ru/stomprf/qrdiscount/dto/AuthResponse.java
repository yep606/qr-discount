package ru.stomprf.qrdiscount.dto;

import lombok.Data;

@Data
public class AuthResponse {

    private String message;
    private String jwt;
    private boolean isRegistered;

    public AuthResponse(String message) {
        this.message = message;
    }

    public AuthResponse(String message, String jwt, boolean isRegistered) {
        this.message = message;
        this.jwt = jwt;
        this.isRegistered = isRegistered;
    }
}
