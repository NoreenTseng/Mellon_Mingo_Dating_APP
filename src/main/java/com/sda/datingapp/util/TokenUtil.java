package com.sda.datingapp.util;

import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    public String extractJwtToken(String authorizationHeader) {
        // Assuming the Authorization header follows the format "Bearer <token>"
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // Extract the token excluding "Bearer "
        }
        return null;
    }
}
