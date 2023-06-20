package com.company.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.dto.JwtResponse;
import com.company.dto.LoginRequest;
import com.company.dto.SignupRequest;
import com.company.model.User;
import com.company.repository.RoleRepository;
import com.company.repository.UserRepository;
import com.company.security.JwtTokenUtil;
import com.company.security.MyUserDetails;
import com.company.security.MyUserDetailsService;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private MyUserDetailsService userDetailsService;

	@Override
	public User registerNewUserAccount(SignupRequest account) throws Exception{
		User user = new User();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		if(userRepository.existsByEmail(account.getEmail())) {
			throw new Exception("Correo no disponible para esta cuenta.");
		}

		user.setFirstName(account.getFirstName());
		user.setLastName(account.getLastName());
		user.setPassword(passwordEncoder.encode(account.getPassword()));
		user.setUsername(account.getUsername());
		user.setEmail(account.getEmail());
		user.setRole(Set.of(roleRepository.findByName("USER")));
		user.setEnabled(true);
		return userRepository.save(user);
	}

	@Override
	public JwtResponse authenticate(LoginRequest account) throws Exception{
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(account.getUsername(), account.getPassword()));

		MyUserDetails userDetails = (MyUserDetails) userDetailsService.loadUserByUsername(account.getUsername());
		String jwt = jwtTokenUtil.generateJwtToken(userDetails);
		String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);	
		return new JwtResponse(jwt, refreshToken);
	}

}
