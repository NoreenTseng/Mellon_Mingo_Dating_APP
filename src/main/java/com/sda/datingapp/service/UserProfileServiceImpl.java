package com.sda.datingapp.service;

import com.sda.datingapp.dto.UserProfileDto;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    // Profile Service
    @Autowired
    ProfileRepository profileRepository;
    public List<UserProfile> getAllUserProfiles() {
        return profileRepository.findAll();
    }

    public UserProfile createProfileByUserId(Integer userId, UserProfileDto userProfileDto) {
        UserProfile newUserProfile = new UserProfile();

        newUserProfile.setUserId(userId);
        newUserProfile.setNickname(userProfileDto.getNickname());
        newUserProfile.setAge(userProfileDto.getAge());
        newUserProfile.setGender(userProfileDto.getGender());
        newUserProfile.setProfileImageUrl(userProfileDto.getProfileImageUrl());
        newUserProfile.setImageUrls(userProfileDto.getImageUrls());
        newUserProfile.setBio(userProfileDto.getBio());
        newUserProfile.setLastLatitude(userProfileDto.getLastLatitude());
        newUserProfile.setLastLongitude(userProfileDto.getLastLongitude());
        newUserProfile.setLookingFor(userProfileDto.getLookingFor());
        newUserProfile.setMbti(userProfileDto.getMbti());
        newUserProfile.setHobbies(userProfileDto.getHobbies());
        newUserProfile.setStrategyType(userProfileDto.getStrategyType());
        newUserProfile.setDrinks(userProfileDto.getDrinks());
        newUserProfile.setDrugs(userProfileDto.getDrugs());
        newUserProfile.setEducation(userProfileDto.getEducation());
        newUserProfile.setHeight(userProfileDto.getHeight());

        profileRepository.insert(newUserProfile);
        return newUserProfile;
    }

    public Optional<UserProfile> getProfile(Integer id) {
        return profileRepository.findById(id);
    }

    public UserProfile getProfileByUserId(Integer userId) {
        return profileRepository.findByUserId(userId);
    }
    public UserProfile updateProfileByUserId(Integer userId, UserProfileDto updatedProfileDto) {
        UserProfile existingProfileOpt = profileRepository.findByUserId(userId);

        if(existingProfileOpt == null)
            return null;

        existingProfileOpt.setUserId(userId);
        existingProfileOpt.setNickname(updatedProfileDto.getNickname());
        existingProfileOpt.setAge(updatedProfileDto.getAge());
        existingProfileOpt.setGender(updatedProfileDto.getGender());
        existingProfileOpt.setImageUrls(updatedProfileDto.getImageUrls());
        existingProfileOpt.setBio(updatedProfileDto.getBio());
        existingProfileOpt.setLastLatitude(updatedProfileDto.getLastLatitude());
        existingProfileOpt.setLastLongitude(updatedProfileDto.getLastLongitude());
        existingProfileOpt.setLookingFor(updatedProfileDto.getLookingFor());
        existingProfileOpt.setMbti(updatedProfileDto.getMbti());
        existingProfileOpt.setHobbies(updatedProfileDto.getHobbies());
        existingProfileOpt.setStrategyType(updatedProfileDto.getStrategyType());
        existingProfileOpt.setDrinks(updatedProfileDto.getDrinks());
        existingProfileOpt.setDrugs(updatedProfileDto.getDrugs());
        existingProfileOpt.setEducation(updatedProfileDto.getEducation());
        existingProfileOpt.setHeight(updatedProfileDto.getHeight());

        profileRepository.update(existingProfileOpt);
        return existingProfileOpt;
    }

    public UserProfile updateProfile(Integer id, UserProfileDto updatedProfileDto) {
        Optional<UserProfile> existingProfileOpt = profileRepository.findById(id);

        if (existingProfileOpt.isPresent()) {
            UserProfile existingProfile = existingProfileOpt.get();
            existingProfile.setUserId(updatedProfileDto.getUserId());
            existingProfile.setNickname(updatedProfileDto.getNickname());
            existingProfile.setAge(updatedProfileDto.getAge());
            existingProfile.setGender(updatedProfileDto.getGender());
            existingProfile.setImageUrls(updatedProfileDto.getImageUrls());
            existingProfile.setBio(updatedProfileDto.getBio());
            existingProfile.setLastLatitude(updatedProfileDto.getLastLatitude());
            existingProfile.setLastLongitude(updatedProfileDto.getLastLongitude());
            existingProfile.setLookingFor(updatedProfileDto.getLookingFor());
            existingProfile.setMbti(updatedProfileDto.getMbti());
            existingProfile.setHobbies(updatedProfileDto.getHobbies());
            existingProfile.setStrategyType(updatedProfileDto.getStrategyType());
            existingProfile.setDrinks(updatedProfileDto.getDrinks());
            existingProfile.setDrugs(updatedProfileDto.getDrugs());
            existingProfile.setEducation(updatedProfileDto.getEducation());
            existingProfile.setHeight(updatedProfileDto.getHeight());

            profileRepository.update(existingProfile);
            return existingProfile;
        } else {
            // Handle the case where the profile doesn't exist.
            // Throwing an exception or another form of error handling.
            throw new RuntimeException("Profile not found with id: " + id);
            // Or return null
        }
    }
    public UserProfile createProfile(UserProfileDto userProfileDto) {
        UserProfile newUserProfile = new UserProfile();

        newUserProfile.setUserId(userProfileDto.getUserId());
        newUserProfile.setNickname(userProfileDto.getNickname());
        newUserProfile.setAge(userProfileDto.getAge());
        newUserProfile.setGender(userProfileDto.getGender());
        newUserProfile.setProfileImageUrl(userProfileDto.getProfileImageUrl());
        newUserProfile.setImageUrls(userProfileDto.getImageUrls());
        newUserProfile.setBio(userProfileDto.getBio());
        newUserProfile.setLastLatitude(userProfileDto.getLastLatitude());
        newUserProfile.setLastLongitude(userProfileDto.getLastLongitude());
        newUserProfile.setLookingFor(userProfileDto.getLookingFor());
        newUserProfile.setMbti(userProfileDto.getMbti());
        newUserProfile.setHobbies(userProfileDto.getHobbies());
        newUserProfile.setStrategyType(userProfileDto.getStrategyType());
        newUserProfile.setDrinks(userProfileDto.getDrinks());
        newUserProfile.setDrugs(userProfileDto.getDrugs());
        newUserProfile.setEducation(userProfileDto.getEducation());
        newUserProfile.setHeight(userProfileDto.getHeight());

        profileRepository.insert(newUserProfile);
        return newUserProfile;
    }
}
