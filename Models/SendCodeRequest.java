package com.agri.agri_system.model;


public class SendCodeRequest {
    
    private String username;
    private String email;

    
    public SendCodeRequest() {
    }

    
    public SendCodeRequest(String username, String email) {
        this.username = username;
        this.email = email;
    }

    
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