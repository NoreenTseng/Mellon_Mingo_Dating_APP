package com.sda.datingapp.service;

import com.google.common.hash.Hashing;
import com.sda.datingapp.dto.UserDto;
import com.sda.datingapp.model.User;
import com.sda.datingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   UserRepository userRepository;
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public boolean userExist(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent();
    }
    //private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public User registerNewUser(UserDto userDto) {
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());

        String hashedPassword = Hashing.sha256()
                .hashString(userDto.getPassword(), StandardCharsets.UTF_8)
                .toString();

        newUser.setHashedPassword(hashedPassword);
        // Set other properties as needed

        int rowsAffected = userRepository.save(newUser);
        if (rowsAffected > 0) {
            // Assuming the id is auto-generated and set on the story object
            return userRepository.findById(newUser.getId());
        } else {
            // Handle the case where the insert failed
            throw new RuntimeException("Failed to generate new user.");
        }
    }
}

