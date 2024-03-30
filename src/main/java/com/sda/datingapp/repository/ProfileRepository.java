package com.sda.datingapp.repository;

import com.sda.datingapp.model.UserProfile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ProfileRepository {
    @Select("SELECT * FROM profiles")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "imageUrls", column = "image_urls"),
            @Result(property = "bio", column = "bio"),
            @Result(property = "lastLatitude", column = "last_latitude"),
            @Result(property = "lastLongitude", column = "last_longitude"),
            @Result(property = "lookingFor", column = "looking_for"),
            @Result(property = "mbti", column = "mbti"),
            @Result(property = "hobbies", column = "hobbies"),
    })
    public List<UserProfile> findAll();
    @Insert("INSERT INTO profiles (user_id, nickname, age, gender, profile_image_url, image_urls, bio, last_latitude, last_longitude, looking_for, mbti, hobbies, strategyType, drinks, drugs, education, height) " +
            "VALUES (#{userId}, #{nickname}, #{age}, #{gender}, #{profileImageUrl}, #{imageUrls}, #{bio}, #{lastLatitude}, #{lastLongitude}, #{lookingFor}, #{mbti}, #{hobbies}, #{strategyType}, #{drinks}, #{drugs}, #{education}, #{height})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(UserProfile userProfile);

    @Select("SELECT * FROM profiles WHERE id = #{id}")
    Optional<UserProfile> findById(Integer id);

    @Update("UPDATE profiles SET nickname = #{nickname}, age = #{age}, gender = #{gender}, image_urls = #{imageUrls}, bio = #{bio}, last_latitude = #{lastLatitude}, last_longitude = #{lastLongitude}, looking_for = #{lookingFor}, mbti = #{mbti}, hobbies = #{hobbies}, strategyType = #{strategyType}, drinks = #{drinks}, drugs = #{drugs}, education = #{education}, height = #{height} " +
            "WHERE id = #{id}")
    void update(UserProfile userProfile);

    @Select("SELECT * FROM profiles WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "profileImageUrl", column = "profile_image_url"),
            @Result(property = "imageUrls", column = "image_urls"),
            @Result(property = "bio", column = "bio"),
            @Result(property = "lastLatitude", column = "last_latitude"),
            @Result(property = "lastLongitude", column = "last_longitude"),
            @Result(property = "lookingFor", column = "looking_for"),
            @Result(property = "mbti", column = "mbti"),
            @Result(property = "hobbies", column = "hobbies"),
            @Result(property = "strategyType", column = "strategyType"),
            @Result(property = "drinks", column = "drinks"),
            @Result(property = "drugs", column = "drugs"),
            @Result(property = "education", column = "education"),
            @Result(property = "height", column = "height"),
    })
    UserProfile findByUserId(Integer userId);

}
