package com.agri.agri_system.model;

/**
 * Request DTO for resetting password
 */
public class ResetPasswordRequest {
    
    private String email;
    private String newPassword;

    // Default constructor
    public ResetPasswordRequest() {
    }

    // Constructor with parameters
    public ResetPasswordRequest(String email, String newPassword) {
        this.email = email;
        this.newPassword = newPassword;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequest{" +
                "email='" + email + '\'' +
                ", newPassword='[PROTECTED]'" + // Don't log actual password
                '}';
    }
}