package com.example.demo.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtService;

@Service

public class AuthService {
	 private final UserRepository userRepository;
	    private final PasswordEncoder passwordEncoder;
	    private final JwtService jwtService;

	    public AuthService(UserRepository userRepository,
	                       PasswordEncoder passwordEncoder,
	                       JwtService jwtService) {

	        this.userRepository = userRepository;
	        this.passwordEncoder = passwordEncoder;
	        this.jwtService = jwtService;
	    }

	    public AuthResponse login(AuthRequest request) {

	        // Find user using email
	        User user = userRepository.findByEmail(request.getEmail())
	                .orElseThrow(() ->
	                    new BadCredentialsException("Invalid email or password."));

	        // Check whether account is enabled
	        if (!user.isEnabled()) {
	            throw new DisabledException("Your account is disabled.");
	        }

	        // Verify password
	        if (!passwordEncoder.matches(
	                request.getPassword(), user.getPassword())) {

	            throw new BadCredentialsException("Invalid email or password.");
	        }

	        // Generate JWT token
	        String token = jwtService.generateToken(
	                user.getEmail(),
	                user.getRole().name()
	        );

	        // Return login response
	        return new AuthResponse(
	                token,
	                user.getName(),
	                user.getEmail(),
	                user.getRole().name()
	        );
	    }

}
