package com.agri.agri_system.model;


public class VerificationCode {
    
    private String email;
    private String code;
    private long timestamp;

    
    private static final long EXPIRATION_TIME = 10 * 60 * 1000;

    
    public VerificationCode() {
    }

    
    public VerificationCode(String email, String code, long timestamp) {
        this.email = email;
        this.code = code;
        this.timestamp = timestamp;
    }

    /**
     * Check if the verification code has expired
     * @return true if expired, false otherwise
     */
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long timeDifference = currentTime - timestamp;
        return timeDifference > EXPIRATION_TIME;
    }

    /**
     * Get remaining time in seconds before expiration
     * @return remaining seconds, or 0 if expired
     */
    public long getRemainingSeconds() {
        if (isExpired()) {
            return 0;
        }
        long timeDifference = System.currentTimeMillis() - timestamp;
        long remainingMillis = EXPIRATION_TIME - timeDifference;
        return remainingMillis / 1000;
    }

    /**
     * Get expiration time in minutes
     * @return expiration time in minutes
     */
    public static long getExpirationMinutes() {
        return EXPIRATION_TIME / (60 * 1000);
    }

   
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "VerificationCode{" +
                "email='" + email + '\'' +
                ", code='" + code + '\'' +
                ", timestamp=" + timestamp +
                ", expired=" + isExpired() +
                ", remainingSeconds=" + getRemainingSeconds() +
                '}';
    }
}