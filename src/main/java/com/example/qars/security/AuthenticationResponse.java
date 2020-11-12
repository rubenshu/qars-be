package com.example.qars.security;

// Class for returning json web token
public class AuthenticationResponse {
    // JWT attribute
    private final String jwt;
    // Constructor
    public AuthenticationResponse(String jwt) { this.jwt = jwt; }
    // Getter
    public String getJwt() { return jwt; }
}
