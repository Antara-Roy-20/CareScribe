package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CreateUserRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;



@RestController
@RequestMapping("/api/admin/users")

public class AdminUserController {
	 private final UserService userService;

	    public AdminUserController(UserService userService) {
	        this.userService = userService;
	    }

	    // Create Doctor or Nurse account
	    @PostMapping
	    public ResponseEntity<Map<String, Object>> createUser(
	            @RequestBody CreateUserRequest request) {

	        if (request.getRole() != Role.DOCTOR &&
	                request.getRole() != Role.NURSE) {
	            return ResponseEntity.badRequest()
	                    .body(Map.of("message",
	                            "Only DOCTOR and NURSE accounts can be created."));
	        }

	        User user = userService.createUser(
	                request.getName(),
	                request.getEmail(),
	                request.getPassword(),
	                request.getRole()
	        );

	        return ResponseEntity.status(HttpStatus.CREATED)
	                .body(toResponse(user));
	    }

	    // View all registered users
	    @GetMapping
	    public ResponseEntity<List<Map<String, Object>>> getAllUsers() {

	        List<Map<String, Object>> users = userService.getAllUsers()
	                .stream()
	                .map(this::toResponse)
	                .toList();

	        return ResponseEntity.ok(users);
	    }

	    // Return safe user details without password
	    private Map<String, Object> toResponse(User user) {

	        Map<String, Object> response = new LinkedHashMap<>();

	        response.put("id", user.getId());
	        response.put("name", user.getName());
	        response.put("email", user.getEmail());
	        response.put("role", user.getRole());
	        response.put("enabled", user.isEnabled());

	        return response;
	    }

}
