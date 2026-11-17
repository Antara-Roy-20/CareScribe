package com.example.demo.security;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JwtService {
	 private final SecretKey secretKey;

	    public JwtService(@Value("${jwt.secret}") String secret) {
	        this.secretKey = Keys.hmacShaKeyFor(
	                Decoders.BASE64.decode(secret)
	        );
	    }

	    public String generateToken(String email, String role) {

	        long expirationTime = 1000L * 60 * 60; // 1 hour

	        return Jwts.builder()
	                .subject(email)
	                .claim("role", role)
	                .issuedAt(new Date())
	                .expiration(new Date(System.currentTimeMillis() + expirationTime))
	                .signWith(secretKey)
	                .compact();
	    }

	    public String extractEmail(String token) {
	        return extractClaims(token).getSubject();
	    }

	    public String extractRole(String token) {
	        return extractClaims(token).get("role", String.class);
	    }

	    public boolean isTokenValid(String token, String email) {
	        Claims claims = extractClaims(token);

	        return claims.getSubject().equals(email)
	                && claims.getExpiration().after(new Date());
	    }

	    private Claims extractClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(secretKey)
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();
	    }

}
