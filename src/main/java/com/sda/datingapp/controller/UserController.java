package com.sda.datingapp.controller;

import com.sda.datingapp.dto.LoginDto;
import com.sda.datingapp.dto.UserDto;
import com.sda.datingapp.model.User;
import com.sda.datingapp.service.AuthenticationService;
import com.sda.datingapp.service.JWTService;
import com.sda.datingapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            User registeredUser = userService.registerNewUser(userDto);
            return ResponseEntity.ok(registeredUser); // Return a DTO instead of the User entity directly
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        try {
            String token = authenticationService.authenticate(loginDto.getUsername(), loginDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @Autowired
    private JWTService jwtService;
    // Unsecure endpoint for testing purposes (it is more secure than RequestParam)
    // More Secure endpoint: (1) User TokenDto (2) Use @RequestHeader("Authorization") String authHeader
    // We can discuss this maybe on Thursday.
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String token) {
        jwtService.blacklistToken(token);
        return ResponseEntity.ok().body("Logged out successfully");
    }
}
