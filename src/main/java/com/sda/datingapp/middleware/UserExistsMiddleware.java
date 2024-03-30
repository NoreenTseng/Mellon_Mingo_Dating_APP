package com.sda.datingapp.middleware;

import com.sda.datingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserExistsMiddleware extends AbstractMiddleware {
    @Autowired
    UserRepository userRepository;

    public UserExistsMiddleware(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean check(String username, String password) {
        if (userRepository.findByUsername(username).isEmpty()){

            return false; // User not found, break the chain
        }
        return super.check(username, password);
    }
}

