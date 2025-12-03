package com.agri.agri_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.agri.agri_system.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Find a user by username (used for login or registration check)
    User findByUsername(String username);

    // Find a user by email - UPDATED to return Optional<User>
    @Query("SELECT u FROM User u WHERE LOWER(TRIM(u.email)) = LOWER(TRIM(:email))")
    Optional<User> findByEmail(@Param("email") String email);
    
    // Check if user exists by username and email (for forgot password)
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE LOWER(TRIM(u.username)) = LOWER(TRIM(:username)) AND LOWER(TRIM(u.email)) = LOWER(TRIM(:email))")
    boolean existsByUsernameAndEmail(@Param("username") String username, @Param("email") String email);
    
    // Check if username exists (for profile update validation)
    boolean existsByUsername(String username);
    
    // Check if email exists (for profile update validation)
    boolean existsByEmail(String email);
}