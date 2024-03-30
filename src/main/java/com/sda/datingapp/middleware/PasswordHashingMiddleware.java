package com.sda.datingapp.middleware;

import com.google.common.hash.Hashing;
import com.sda.datingapp.model.User;
import com.sda.datingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PasswordHashingMiddleware extends AbstractMiddleware {
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean check(String username, String password) {
        // Fetch user from database (pseudo-code)
        User user = userRepository.findByUsername(username).orElse(null);

        String hashedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        if (user != null && hashedPassword.equals(user.getHashedPassword())) {
            return super.check(username, password);
        }

        return false;
    }
}
