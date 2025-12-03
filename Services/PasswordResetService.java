package com.agri.agri_system.service;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.agri.agri_system.model.User;
import com.agri.agri_system.model.VerificationCode;
import com.agri.agri_system.repository.UserRepository;

@Service
public class PasswordResetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Store verification codes in memory (use Redis in production)
    private Map<String, VerificationCode> verificationCodes = new ConcurrentHashMap<>();

    /**
     * Generate and send verification code
     */
    public boolean sendVerificationCode(String username, String email) {
        // 1. Check if user exists with both username and email
        if (!userRepository.existsByUsernameAndEmail(username, email)) {
            System.out.println("User not found with username: " + username + " and email: " + email);
            return false;
        }

        // 2. Generate 6-digit code
        String code = generateCode();

        // 3. Store code
        verificationCodes.put(email, new VerificationCode(email, code, System.currentTimeMillis()));

        // 4. Send email
        try {
            sendEmail(email, "Password Reset Verification Code", 
                "Hello,\n\n" +
                "You requested to reset your password for Agri System.\n\n" +
                "Your verification code is: " + code + "\n\n" +
                "This code will expire in 10 minutes.\n\n" +
                "If you didn't request this, please ignore this email.\n\n" +
                "Best regards,\n" +
                "Agri System Team");
            
            System.out.println("✅ Verification code sent to " + email + ": " + code);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
            // For development, print code to console
            System.out.println("📧 Verification code for " + email + ": " + code);
            return true; // Still return true for testing without email
        }
    }

    /**
     * Resend verification code
     */
    public boolean resendVerificationCode(String email) {
        // Check if email has previous code request
        if (!verificationCodes.containsKey(email)) {
            System.out.println("No previous verification code request found for: " + email);
            return false;
        }

        // Generate new code
        String code = generateCode();
        verificationCodes.put(email, new VerificationCode(email, code, System.currentTimeMillis()));

        // Send email
        try {
            sendEmail(email, "Password Reset Verification Code (Resent)", 
                "Hello,\n\n" +
                "You requested to resend your password reset verification code.\n\n" +
                "Your new verification code is: " + code + "\n\n" +
                "This code will expire in 10 minutes.\n\n" +
                "Best regards,\n" +
                "Agri System Team");
            
            System.out.println("✅ New verification code sent to " + email + ": " + code);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            System.out.println("📧 New verification code for " + email + ": " + code);
            return true;
        }
    }

    /**
     * Verify code
     */
    public boolean verifyCode(String email, String code) {
        VerificationCode stored = verificationCodes.get(email);

        if (stored == null) {
            System.out.println("❌ No verification code found for: " + email);
            return false;
        }

        if (stored.isExpired()) {
            verificationCodes.remove(email);
            System.out.println("❌ Verification code expired for: " + email);
            return false;
        }

        boolean isValid = stored.getCode().equals(code);
        if (isValid) {
            System.out.println("✅ Code verified for: " + email);
        } else {
            System.out.println("❌ Invalid code for: " + email);
        }
        
        return isValid;
    }

    /**
     * Reset password - FIXED
     */
    public boolean resetPassword(String email, String newPassword) {
        try {
            // 1. Find user by email - FIXED: Handle Optional
            Optional<User> userOpt = userRepository.findByEmail(email);
            if (!userOpt.isPresent()) {
                System.out.println("❌ User not found with email: " + email);
                return false;
            }

            User user = userOpt.get();

            // 2. Hash password using BCrypt
            String hashedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(hashedPassword);

            // 3. Save to database
            userRepository.save(user);

            // 4. Clear verification code
            verificationCodes.remove(email);

            System.out.println("✅ Password reset successfully for: " + email);
            return true;
        } catch (Exception e) {
            System.err.println("❌ Failed to reset password: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Generate 6-digit code
     */
    private String generateCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    /**
     * Send email using JavaMailSender
     */
    private void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("veranoclyde887@gmail.com");
        
        mailSender.send(message);
    }
}