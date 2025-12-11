package com.agri.agri_system.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.agri.agri_system.model.User;
import com.agri.agri_system.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    
    @GetMapping("/login")
    public String showLoginPage() {
        return "forward:/login.html";
    }

    
    @GetMapping("/signup")
    public String showSignUpPage() {
        return "forward:/signup.html";
    }

    
    @GetMapping("/api/user/last-login")
    @ResponseBody
    public Map<String, String> getLastLoginUser(HttpSession session) {
        String lastUsername = (String) session.getAttribute("lastLoginUsername");
        
        if (lastUsername != null) {
            User user = userRepository.findByUsername(lastUsername);
            if (user != null) {
                Map<String, String> response = new HashMap<>();
                response.put("username", user.getUsername());
                response.put("firstName", user.getFirstName());
                response.put("email", user.getEmail()); 
                response.put("profileImage", user.getProfileImage());
                return response;
            }
        }
        
        return new HashMap<>(); 
    }

    
    @GetMapping("/api/user/by-email")
    @ResponseBody
    public Map<String, String> getUserByEmail(@RequestParam String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Map<String, String> response = new HashMap<>();
            response.put("username", user.getUsername());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("email", user.getEmail());
            response.put("profileImage", user.getProfileImage());
            return response;
        }
        
        return new HashMap<>(); 
    }

   
    @PostMapping("/LoginServlet")
    public String loginUser(@RequestParam String username, 
                            @RequestParam String password, 
                            HttpSession session) {

        System.out.println("🔐 Login attempt for user: " + username);
        
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("❌ User not found: " + username);
            session.setAttribute("userLoggedIn", false);
            return "redirect:/login?error=invalid";
        }

        System.out.println("👤 Found user: " + username);
        System.out.println("📏 Stored password length: " + user.getPassword().length());
        System.out.println("🔍 Password starts with: " + user.getPassword().substring(0, Math.min(4, user.getPassword().length())));

        boolean passwordMatch = false;

       
        if (user.getPassword().length() == 60 && user.getPassword().startsWith("$2")) {
           
            System.out.println("🔒 BCrypt hashed password detected");
            passwordMatch = passwordEncoder.matches(password, user.getPassword());
            System.out.println("   BCrypt match result: " + passwordMatch);
        } else {
           
            System.out.println("⚠️ Plain text password detected (old account)");
            passwordMatch = password.equals(user.getPassword());
            System.out.println("   Plain text match result: " + passwordMatch);
            
           
            if (passwordMatch) {
                try {
                    System.out.println("🔄 Auto-upgrading password to BCrypt hash...");
                    String hashedPassword = passwordEncoder.encode(password);
                    user.setPassword(hashedPassword);
                    userRepository.save(user);
                    System.out.println("✅ Password upgraded successfully for user: " + username);
                } catch (Exception e) {
                    System.err.println("⚠️ Failed to upgrade password: " + e.getMessage());
                   
                }
            }
        }

        if (passwordMatch) {
            
            System.out.println("✅ Login successful for user: " + username);
            
            
            session.setAttribute("userLoggedIn", true);
            session.setAttribute("username", username);
            session.setAttribute("loggedInUser", user);
            session.setAttribute("lastLoginUsername", username); 
            
            System.out.println("📦 Session data saved:");
            System.out.println("   - userLoggedIn: true");
            System.out.println("   - username: " + username);
            System.out.println("   - loggedInUser: " + user.getId());
            System.out.println("   - lastLoginUsername: " + username);
            
            return "redirect:/interface";
        } else {
            
            System.out.println("❌ Invalid password for user: " + username);
            session.setAttribute("userLoggedIn", false);
            return "redirect:/login?error=invalid";
        }
    }

    
    @GetMapping("/interface")
    public String showInterface(HttpSession session) {
        Boolean isLoggedIn = (Boolean) session.getAttribute("userLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            return "redirect:/login";
        }
        return "forward:/interface.html";
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    
    @GetMapping("/about")
    public String showAboutPage() {
        return "forward:/about.html";
    }
}