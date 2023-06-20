package com.company.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.dto.LoginRequest;
import com.company.dto.SignupRequest;
import com.company.service.AuthService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	
	@Autowired
	private AuthService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
		
		 return ResponseEntity.ok(userService.authenticate(loginRequest));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)throws Exception {
		userService.registerNewUserAccount(signUpRequest);
		return ResponseEntity.ok("User registered successfully!");
	}

}
