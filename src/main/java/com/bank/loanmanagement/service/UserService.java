package com.bank.loanmanagement.service;

import com.bank.loanmanagement.dto.AuthResponse;
import com.bank.loanmanagement.dto.LoginRequest;
import com.bank.loanmanagement.dto.RegisterRequest;
import com.bank.loanmanagement.model.User;
import com.bank.loanmanagement.repository.UserRepository;
import com.bank.loanmanagement.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtils jwtUtils;

	// Register new user
	public AuthResponse register(RegisterRequest request) {

		// Check if email already exists
		if (userRepository.existsByEmail(request.getEmail())) {
			return new AuthResponse(null, null, "Email already registered!");
		}

		// Create new user
		User user = new User();
		user.setName(request.getName());
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(User.Role.valueOf(request.getRole().toUpperCase()));

		// Save user to DB
		userRepository.save(user);

		// Generate token
		String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

		return new AuthResponse(token, user.getRole().name(), "Registration successful!");
	}

	// Login user
	public AuthResponse login(LoginRequest request) {

		// Find user by email
		User user = userRepository.findByEmail(request.getEmail()).orElse(null);

		// Check if user exists
		if (user == null) {
			return new AuthResponse(null, null, "User not found!");
		}

		// Check password
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			return new AuthResponse(null, null, "Invalid password!");
		}

		// Generate token
		String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

		return new AuthResponse(token, user.getRole().name(), "Login successful!");
	}
}