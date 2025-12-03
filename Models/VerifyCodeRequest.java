package com.agri.agri_system.model;

/**
 * Request DTO for verifying verification code
 */
public class VerifyCodeRequest {
    
    private String email;
    private String verificationCode;

    // Default constructor
    public VerifyCodeRequest() {
    }

    // Constructor with parameters
    public VerifyCodeRequest(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "VerifyCodeRequest{" +
                "email='" + email + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}