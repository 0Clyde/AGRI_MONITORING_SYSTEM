package com.agri.agri_system.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agri.agri_system.model.User;
import com.agri.agri_system.repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    @GetMapping("/api/check-email")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkEmail(@RequestParam String email) {
        Map<String, Boolean> response = new HashMap<>();
        
       
        Optional<User> user = userRepository.findByEmail(email);
        response.put("exists", user.isPresent());
        
        System.out.println("📧 Email check: " + email + " - Exists: " + user.isPresent());
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/RegisterServlet")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {

        System.out.println("📝 Registration attempt for: " + username + " (" + email + ")");

        
        String nameRegex = "^[A-Za-z\\s'\\-]+$";
        if (!firstName.matches(nameRegex)) {
            model.addAttribute("error", "First name must contain only letters");
            return "signup";
        }

       
        if (!lastName.matches(nameRegex)) {
            model.addAttribute("error", "Last name must contain only letters");
            return "signup";
        }

       
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,50}$";
        if (!password.matches(passwordRegex)) {
            model.addAttribute("error", "Password must be 10-50 characters with at least 1 uppercase, 1 lowercase, 1 number, and 1 special character (@$!%*?&)"); 
            return "signup";
        }

        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            model.addAttribute("error", "Invalid email format");
            return "signup";
        }

        
        if (userRepository.findByUsername(username) != null) {
            System.out.println("❌ Username already taken: " + username);
            model.addAttribute("error", "Username is already taken");
            return "signup";
        }

        
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            System.out.println("❌ Email already registered: " + email);
            model.addAttribute("error", "Email is already registered");
            return "signup";
        }

        
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("🔒 Password hashed for user: " + username);

        
        User newUser = new User(firstName, lastName, username, hashedPassword, email);

        try {
            userRepository.save(newUser);
            System.out.println("✅ User registered successfully: " + username + " (" + email + ")");

            return "redirect:/signup?created=true"; 
        } catch (Exception e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/signup?error=server";
        }
    }
}