package com.agri.agri_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.model.ApiResponse;
import com.agri.agri_system.model.ResendCodeRequest;
import com.agri.agri_system.model.ResetPasswordRequest;
import com.agri.agri_system.model.SendCodeRequest;
import com.agri.agri_system.model.VerifyCodeRequest;
import com.agri.agri_system.service.PasswordResetService;

@RestController
@RequestMapping("/api/password")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    
    @PostMapping("/send-verification-code")
    public ResponseEntity<ApiResponse> sendVerificationCode(@RequestBody SendCodeRequest request) {
        try {
            System.out.println("📨 Received request to send verification code");
            System.out.println("   Username: " + request.getUsername());
            System.out.println("   Email: " + request.getEmail());

            boolean success = passwordResetService.sendVerificationCode(
                request.getUsername(), 
                request.getEmail()
            );

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Verification code sent successfully.")
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Username or email not found.")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Error sending verification code: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error("Failed to send verification code.")
            );
        }
    }

    
    @PostMapping("/resend-code")
    public ResponseEntity<ApiResponse> resendCode(@RequestBody ResendCodeRequest request) {
        try {
            System.out.println("🔄 Received request to resend code for: " + request.getEmail());

            boolean success = passwordResetService.resendVerificationCode(request.getEmail());

            if (success) {
                return ResponseEntity.ok(
                    ApiResponse.success("Verification code resent successfully.")
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("No previous code request found.")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Error resending code: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error("Failed to resend code.")
            );
        }
    }

   
    @PostMapping("/verify-code")
    public ResponseEntity<ApiResponse> verifyCode(@RequestBody VerifyCodeRequest request) {
        try {
            System.out.println("🔍 Verifying code for email: " + request.getEmail());

            boolean valid = passwordResetService.verifyCode(
                request.getEmail(), 
                request.getVerificationCode()
            );

            if (valid) {
                return ResponseEntity.ok(
                    ApiResponse.success("Code verified successfully.")
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Invalid or expired verification code.")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Error verifying code: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error("Failed to verify code.")
            );
        }
    }

    
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            System.out.println("🔐 ========================================");
            System.out.println("🔐 RESET PASSWORD REQUEST RECEIVED");
            System.out.println("🔐 Email: " + request.getEmail());
            System.out.println("🔐 New Password Length: " + (request.getNewPassword() != null ? request.getNewPassword().length() : "NULL"));
            System.out.println("🔐 ========================================");

            
            if (request.getEmail() == null || request.getEmail().trim().isEmpty()) {
                System.err.println("❌ Email is null or empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Email is required.")
                );
            }

            if (request.getNewPassword() == null || request.getNewPassword().trim().isEmpty()) {
                System.err.println("❌ Password is null or empty");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Password is required.")
                );
            }

            
            if (request.getNewPassword().length() < 8) {
                System.err.println("❌ Password too short: " + request.getNewPassword().length());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Password must be at least 8 characters long.")
                );
            }

           
            boolean success = passwordResetService.resetPassword(
                request.getEmail(), 
                request.getNewPassword()
            );

            if (success) {
                System.out.println("✅ Password reset successfully for: " + request.getEmail());
                return ResponseEntity.ok(
                    ApiResponse.success("Password reset successfully.")
                );
            } else {
                System.err.println("❌ Service returned false for password reset");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ApiResponse.error("Failed to reset password. User not found.")
                );
            }
        } catch (Exception e) {
            System.err.println("❌ Exception in reset password controller:");
            System.err.println("   Message: " + e.getMessage());
            System.err.println("   Class: " + e.getClass().getName());
            e.printStackTrace();
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error("Failed to reset password: " + e.getMessage())
            );
        }
    }
}