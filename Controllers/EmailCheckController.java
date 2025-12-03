package com.agri.agri_system.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class EmailCheckController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Check if email already exists in database
     * GET /api/check-email?email=user@example.com
     */
    @GetMapping("/check-email")
    public ResponseEntity<Map<String, Boolean>> checkEmailAvailability(@RequestParam String email) {
        System.out.println("📧 Checking email availability: " + email);
        
        Map<String, Boolean> response = new HashMap<>();
        
        // Check if email exists (case-insensitive with trim)
        boolean exists = userRepository.findByEmail(email) != null;
        
        response.put("exists", exists);
        
        System.out.println("   Email exists: " + exists);
        
        return ResponseEntity.ok(response);
    }

    /**
     * Check if username already exists in database
     * GET /api/check-username?username=john123
     */
    @GetMapping("/check-username")
    public ResponseEntity<Map<String, Boolean>> checkUsernameAvailability(@RequestParam String username) {
        System.out.println("👤 Checking username availability: " + username);
        
        Map<String, Boolean> response = new HashMap<>();
        
        // Check if username exists
        boolean exists = userRepository.findByUsername(username) != null;
        
        response.put("exists", exists);
        
        System.out.println("   Username exists: " + exists);
        
        return ResponseEntity.ok(response);
    }
}