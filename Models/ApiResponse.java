package com.agri.agri_system.model;

/**
 * Generic API Response wrapper
 * Used to standardize all API responses
 */
public class ApiResponse {
    
    private boolean success;
    private String message;

    // Default constructor
    public ApiResponse() {
    }

    // Constructor with parameters
    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Static factory method for success response
    public static ApiResponse success(String message) {
        return new ApiResponse(true, message);
    }

    // Static factory method for error response
    public static ApiResponse error(String message) {
        return new ApiResponse(false, message);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}