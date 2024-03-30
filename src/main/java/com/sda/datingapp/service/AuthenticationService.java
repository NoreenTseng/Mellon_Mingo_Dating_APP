package com.sda.datingapp.service;

import com.sda.datingapp.middleware.InputValidationMiddleware;
import com.sda.datingapp.middleware.Middleware;
import com.sda.datingapp.middleware.PasswordHashingMiddleware;
import com.sda.datingapp.middleware.UserExistsMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final JWTService jwtService;
    private final Middleware inputValidationMiddleware;

    @Autowired
    public AuthenticationService(JWTService jwtService,
                                 InputValidationMiddleware inputValidationMiddleware,
                                 UserExistsMiddleware userExistsMiddleware,
                                 PasswordHashingMiddleware passwordHashingMiddleware) {
        // Set up the chain of responsibility
        inputValidationMiddleware.setNext(userExistsMiddleware);
        userExistsMiddleware.setNext(passwordHashingMiddleware);
        this.inputValidationMiddleware = inputValidationMiddleware;
        this.jwtService = jwtService;
    }

    public String authenticate(String username, String password) throws Exception {
        // Invoke the first middleware in the chain
        boolean isSuccess = inputValidationMiddleware.check(username, password);
        if (isSuccess) {
            // If the check is successful, generate a JWT token
            return jwtService.createToken(username);
        } else {
            // If the chain returned false, throw an exception indicating authentication failure
            throw new Exception("Authentication failed");
        }
    }
}
