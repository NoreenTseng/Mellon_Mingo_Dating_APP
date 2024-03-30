package com.sda.datingapp.controller;

import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.dto.UserProfileDto;
import com.sda.datingapp.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class UserProfileController {

    @Autowired
    UserProfileService UserProfileService;

    @GetMapping("/all")
    public List<UserProfile> getAllUserProfiles() {
        return UserProfileService.getAllUserProfiles();
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<UserProfile> createProfile(@PathVariable Integer userId, @RequestBody UserProfileDto userProfileDto) {
        return ResponseEntity.ok(UserProfileService.createProfileByUserId(userId, userProfileDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfile> getProfile(@PathVariable Integer id) {
        Optional<UserProfile> profileOpt = UserProfileService.getProfile(id);
        if (profileOpt.isPresent()) {
            return ResponseEntity.ok(profileOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfile> getProfileByUserId(@PathVariable Integer userId) {
        UserProfile profile = UserProfileService.getProfileByUserId(userId);
        if (profile != null) {
            return ResponseEntity.ok(profile);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserProfile> updateProfile(@PathVariable Integer userId, @RequestBody UserProfileDto updatedProfileDto) {
        try {
            UserProfile updatedProfile = UserProfileService.updateProfileByUserId(userId, updatedProfileDto);
            if (updatedProfile != null) {
                return ResponseEntity.ok(updatedProfile);
            }
            return ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            // Handle database-related exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (IllegalArgumentException e) {
            // Handle invalid argument exceptions
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            // Handle all other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

