package com.company.service;

import com.company.dto.JwtResponse;
import com.company.dto.LoginRequest;
import com.company.dto.SignupRequest;
import com.company.model.User;

public interface AuthService {

	User registerNewUserAccount(SignupRequest account)throws Exception;
	JwtResponse authenticate(LoginRequest account)throws Exception;
}
