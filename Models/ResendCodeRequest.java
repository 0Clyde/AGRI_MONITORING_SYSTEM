package com.agri.agri_system.model;

/**
 * Request DTO for resending verification code
 */
public class ResendCodeRequest {
    
    private String email;

    // Default constructor
    public ResendCodeRequest() {
    }

    // Constructor with parameters
    public ResendCodeRequest(String email) {
        this.email = email;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ResendCodeRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}