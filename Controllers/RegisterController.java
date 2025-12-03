package com.agri.agri_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agri.agri_system.model.User;
import com.agri.agri_system.repository.UserRepository;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    // ✅ NEW: BCrypt password encoder
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/RegisterServlet")
    public String registerUser(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String confirmPassword,
                               Model model) {

        // 1. Validate first name (letters only)
        String nameRegex = "^[A-Za-z]+$";
        if (!firstName.matches(nameRegex)) {
            model.addAttribute("error", "First name must contain only letters");
            return "signup";
        }

        // 2. Validate last name (letters only)
        if (!lastName.matches(nameRegex)) {
            model.addAttribute("error", "Last name must contain only letters");
            return "signup";
        }

        // 3. Check password match
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        // ✅ UPDATED: Password strength validation (8-50 chars before hashing)
        // After BCrypt hashing, it will be 60 chars
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,50}$";
        if (!password.matches(passwordRegex)) {
            model.addAttribute("error", "Password must be 8-50 characters with at least 1 uppercase, 1 lowercase, 1 number, and 1 special character (@$!%*?&)"); 
            return "signup";
        }

        // 5. Validate email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!email.matches(emailRegex)) {
            model.addAttribute("error", "Invalid email format");
            return "signup";
        }

        // 6. Check if username already exists
        if (userRepository.findByUsername(username) != null) {
            model.addAttribute("error", "Username is already taken");
            return "signup";
        }

        // 7. Check if email already exists
        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email is already registered");
            return "signup";
        }

        // ✅ NEW: Hash the password before saving
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println("🔒 Password hashed for user: " + username);

        // ✅ UPDATED: Save new user with HASHED password
        User newUser = new User(firstName, lastName, username, hashedPassword, email);

        try {
            userRepository.save(newUser);
            System.out.println("✅ User registered successfully: " + username);

            return "redirect:/signup?created=true"; 
        } catch (Exception e) {
            System.err.println("❌ Error saving user: " + e.getMessage());
            e.printStackTrace();
            return "redirect:/signup?error=server";
        }
    }
}