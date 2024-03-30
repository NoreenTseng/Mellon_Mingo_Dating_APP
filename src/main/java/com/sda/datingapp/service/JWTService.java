package com.sda.datingapp.service;

import com.sda.datingapp.model.User;
import com.sda.datingapp.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class JWTService {

    // Secret key for signing JWT, should be kept secure
    // NOTES: the secretKey must be encoded in base64!!
    private final String secretKey = "yourSecretKeyyourSecretKeyyourSecretKeyyourSecretKey";
    @Autowired
    UserRepository userRepository;
    public String createToken(String username) throws UnsupportedEncodingException {
        Optional<User> newUser = userRepository.findByUsername(username);

        if (!newUser.isPresent()) {
            // Handle the case where the user is not found in the database
            throw new IllegalArgumentException("User not found");
        }

        int userId = newUser.get().getId(); // Correct way to access the value from Optional
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + (24 * 1000 * 60 * 60)); // 1 hour expiry

        String token = Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return token;
    }

    private final Set<String> blacklistedTokens = Collections.newSetFromMap(new ConcurrentHashMap<>());
    public void blacklistToken(String token) {
        blacklistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklistedTokens.contains(token);
    }

    public boolean validateToken(String token) {
        if (isTokenBlacklisted(token)) {
            return false;
        }

        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}

