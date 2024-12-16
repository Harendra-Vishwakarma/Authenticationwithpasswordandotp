package com.usernamewithpasswordandotp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import com.usernamewithpasswordandotp.dto.UserDto;
import com.usernamewithpasswordandotp.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userServiceImpl;

	// Login endpoint for username/password
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDto userDto) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDto.getUsername(), userDto.getPassword());
			Authentication authentication = authenticationManager.authenticate(authToken);
			return ResponseEntity.ok("Logged in successfully as " + authentication.getName());
		} catch (AuthenticationException e) {
			return ResponseEntity.status(401).body("Invalid username or password");
		}
	}

	// Validate OTP for mobile number authentication
	@PostMapping("/otpLogin")
	public ResponseEntity<String> otpLogin(@RequestBody UserDto userDto) {
		try {
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userDto.getEmail(), userDto.getOtp());
			Authentication authentication = authenticationManager.authenticate(authToken);
			return ResponseEntity.ok("OTP validated successfully for " + authentication.getName());
		} catch (AuthenticationException e) {
			return ResponseEntity.status(401).body("Invalid mobile number or OTP");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registeration(@RequestBody UserDto userDto) {
		
			return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.registerUser(userDto));
		}
	
	@PostMapping("/sendOtp")
	public ResponseEntity<String> sendOtp(@RequestBody UserDto userDto) {
		
			return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImpl.sendOtp(userDto));
		}
	
	
}