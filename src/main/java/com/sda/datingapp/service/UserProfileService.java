package com.sda.datingapp.service;

import com.sda.datingapp.dto.UserProfileDto;
import com.sda.datingapp.model.UserProfile;

import java.util.List;
import java.util.Optional;

public interface UserProfileService {
    public List<UserProfile> getAllUserProfiles();
    public UserProfile createProfileByUserId(Integer userId, UserProfileDto userProfileDto);
    public UserProfile createProfile(UserProfileDto userProfileDto);
    public UserProfile updateProfileByUserId(Integer userId, UserProfileDto updatedProfileDto);
    public UserProfile updateProfile(Integer id, UserProfileDto updatedProfileDto);
    Optional<UserProfile> getProfile(Integer id);
    public UserProfile getProfileByUserId(Integer userId);
}
