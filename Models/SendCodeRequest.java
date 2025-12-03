package com.agri.agri_system.model;

/**
 * Request DTO for sending verification code
 * This file should ONLY contain SendCodeRequest class
 */
public class SendCodeRequest {
    
    private String username;
    private String email;

    // Default constructor
    public SendCodeRequest() {
    }

    // Constructor with parameters
    public SendCodeRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SendCodeRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}