package com.example.demo.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Create a new user account
    public User createUser(String name, String email,
                           String rawPassword, Role role) {

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists.");
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(role);
        user.setEnabled(true);

        return userRepository.save(user);
    }

    // Find a user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found."));
    }

    // List users (for controlled administrative use)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
