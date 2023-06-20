package com.company.security;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Component
public class JwtTokenUtil {
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.expiration_ms}")
	private int jwtExpirationMs;

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.cookie.name}")
	private String jwtCookie;
	
	@Value("${jwt.refresh-token.expiration_ms}")
	private long refreshExpiration;

	private Key createSigningKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String generateJwtToken(UserDetails userDetails) {
		return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
				.signWith(createSigningKey(), SignatureAlgorithm.HS256).compact();
	}
	public String generateRefreshToken(UserDetails userDetails) {
		return Jwts.builder().setSubject((userDetails.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
				.signWith(createSigningKey(), SignatureAlgorithm.HS256).compact();
	}


	public String getUserNameFromJwtToken(String token) {
		return Jwts.parserBuilder().setSigningKey(createSigningKey()).build().parseClaimsJws(token).getBody()
				.getSubject();
	}
	
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(createSigningKey()).build().parse(authToken);
			return true;
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

}
