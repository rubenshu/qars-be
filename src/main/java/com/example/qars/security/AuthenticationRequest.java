package com.example.qars.security;

// This class for receiving the login request in a post endpoint
public class AuthenticationRequest {
    private String username;
    private String password;

    // Empty constructor
    public AuthenticationRequest() {}

    // Getters & Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
