package com.agri.agri_system.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agri.agri_system.model.User;
import com.agri.agri_system.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * GET /api/user/profile
     * Returns current logged-in user's profile
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(HttpSession session) {
        try {
            // Get logged-in user from session
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Please login to view profile");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }

            // Fetch fresh user data from database
            Optional<User> userOpt = userRepository.findById(loggedInUser.getId());
            
            if (!userOpt.isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            User user = userOpt.get();

            // Return user data (including profileImage)
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            response.put("profileImage", user.getProfileImage()); // Include profile image
            response.put("password", "••••••••"); // Masked password

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error loading profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    /**
     * PUT /api/user/profile
     * Updates current logged-in user's profile
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(
            @RequestBody Map<String, String> updates,
            HttpSession session
    ) {
        try {
            // Get logged-in user from session
            User loggedInUser = (User) session.getAttribute("loggedInUser");

            if (loggedInUser == null) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "Please login to update profile");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
            }

            // Fetch user from database
            Optional<User> userOpt = userRepository.findById(loggedInUser.getId());
            
            if (!userOpt.isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put("message", "User not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
            }

            User user = userOpt.get();

            // Update fields
            if (updates.containsKey("firstName") && updates.get("firstName") != null) {
                user.setFirstName(updates.get("firstName").trim());
            }

            if (updates.containsKey("lastName") && updates.get("lastName") != null) {
                user.setLastName(updates.get("lastName").trim());
            }

            if (updates.containsKey("username") && updates.get("username") != null) {
                String newUsername = updates.get("username").trim();
                
                // Check if username is taken by another user
                User existingUser = userRepository.findByUsername(newUsername);
                if (existingUser != null && !existingUser.getId().equals(user.getId())) {
                    Map<String, String> error = new HashMap<>();
                    error.put("message", "Username is already taken");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
                }
                
                user.setUsername(newUsername);
            }

            if (updates.containsKey("email") && updates.get("email") != null) {
                user.setEmail(updates.get("email").trim());
            }

            // NEW: Update profile image
            if (updates.containsKey("profileImage")) {
                user.setProfileImage(updates.get("profileImage"));
            }

            // Save to database
            userRepository.save(user);

            // Update session
            session.setAttribute("loggedInUser", user);

            // Return success response
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Profile updated successfully");
            response.put("user", user);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> error = new HashMap<>();
            error.put("message", "Error updating profile: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}