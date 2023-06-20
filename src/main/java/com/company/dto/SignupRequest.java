package com.company.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.company.validator.NotExistingUser;
import com.company.validator.PasswordsMatch;

import lombok.Data;

@Data
@NotExistingUser
@PasswordsMatch
public class SignupRequest {
	
	private String firstName;
	
	private String lastName;
	
	@NotBlank
	private String username;
	
	@Email
	@NotBlank
	private String email;
	
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$")
	private String password;
	
	@NotBlank
	private String matchingPassword;
}
