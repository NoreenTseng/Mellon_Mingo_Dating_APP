package com.sda.datingapp.middleware;

import org.springframework.stereotype.Component;

@Component
public class InputValidationMiddleware extends AbstractMiddleware {

    @Override
    public boolean check(String username, String password) {
        // Validate the username
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        // Validate the password
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        // Add more validations as required

        return super.check(username, password);
    }
}

