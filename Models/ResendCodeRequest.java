package com.agri.agri_system.model;


public class ResendCodeRequest {
    
    private String email;

    
    public ResendCodeRequest() {
    }

    
    public ResendCodeRequest(String email) {
        this.email = email;
    }

    
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