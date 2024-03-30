package com.sda.datingapp.controller;

import com.sda.datingapp.dto.UserProfileDto;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<UserProfileDto>> getRecommendations(@PathVariable Integer userId) {
        try {
            System.out.println("userId: " + userId);
            List<UserProfile> matches = recommendationService.findRecommendations(userId);
            System.out.println("matches: " + matches);
            List<UserProfileDto> matchDtos = matches.stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(matchDtos);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private UserProfileDto convertToDto(UserProfile userProfile) {
        UserProfileDto dto = new UserProfileDto();
        dto.setUserId(userProfile.getUserId());
        dto.setNickname(userProfile.getNickname());
        dto.setAge(userProfile.getAge());
        dto.setGender(userProfile.getGender());
        dto.setProfileImageUrl(userProfile.getProfileImageUrl());
        dto.setImageUrls(userProfile.getImageUrls());
        dto.setBio(userProfile.getBio());
        dto.setLastLatitude(userProfile.getLastLatitude());
        dto.setLastLongitude(userProfile.getLastLongitude());
        dto.setLookingFor(userProfile.getLookingFor());
        dto.setMbti(userProfile.getMbti());
        dto.setHobbies(userProfile.getHobbies());
        return dto;
    }
}

