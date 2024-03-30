package com.sda.datingapp.dto;

import com.sda.datingapp.model.StrategyType;
import com.sda.datingapp.model.ProfileAttributeType.Drinks;
import com.sda.datingapp.model.ProfileAttributeType.Drugs;
import com.sda.datingapp.model.ProfileAttributeType.Education;

public class UserProfileDto {
    private Integer userId;
    private String nickname;
    private Integer age;
    private String gender;
    private String profileImageUrl;
    private String imageUrls;
    private String bio;
    private Double lastLatitude;
    private Double lastLongitude;
    private String lookingFor;
    private String mbti;
    private String hobbies;
    private StrategyType strategyType;
    private Drinks drinks;
    private Drugs drugs;
    private Education education;
    private Integer height;


    // Getters and setters

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getImageUrls() { return imageUrls; }
    public void setImageUrls(String imageUrls) { this.imageUrls = imageUrls; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public Double getLastLatitude() { return lastLatitude; }
    public void setLastLatitude(Double lastLatitude) { this.lastLatitude = lastLatitude; }

    public Double getLastLongitude() { return lastLongitude; }
    public void setLastLongitude(Double lastLongitude) { this.lastLongitude = lastLongitude; }

    public String getLookingFor() { return lookingFor; }
    public void setLookingFor(String lookingFor) { this.lookingFor = lookingFor; }

    public String getMbti() { return mbti; }
    public void setMbti(String mbti) { this.mbti = mbti; }

    public String getHobbies() { return hobbies; }
    public void setHobbies(String hobbies) { this.hobbies = hobbies; }
    public StrategyType getStrategyType() { return strategyType; }
    public void setStrategyType(StrategyType strategyType) { this.strategyType = strategyType; }
    public Drinks getDrinks() { return drinks; }
    public void setDrinks(Drinks drinks) { this.drinks = drinks; }

    public Drugs getDrugs() { return drugs; }
    public void setDrugs(Drugs drugs) { this.drugs = drugs; }

    public Education getEducation() { return education; }
    public void setEducation(Education education) { this.education = education; }

    public Integer getHeight() { return height; }
    public void setHeight(Integer height) { this.height = height; }

}
