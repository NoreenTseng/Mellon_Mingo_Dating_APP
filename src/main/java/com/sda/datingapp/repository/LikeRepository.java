package com.sda.datingapp.repository;

import com.sda.datingapp.dto.LikeDto;
import com.sda.datingapp.model.Like;
import com.sda.datingapp.model.Story;
import com.sda.datingapp.model.User;
import org.apache.ibatis.annotations.*;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Mapper
@Repository
//public interface LikeRepository extends JpaRepository<Like, Long> {
public interface LikeRepository {
    // You can add custom query methods here if needed
    boolean existsByUserAndStory(User user, Story story);

    @Select("SELECT * FROM likes WHERE user_id = #{userId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "storyId", column = "story_id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "timestamp", column = "timestamp")
    })
    List<Like> findByUserId(int user_id);

    @Insert("INSERT INTO likes (user_id, story_id, timestamp) VALUES (#{userId}, #{storyId}, #{timestamp})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(LikeDto likeDto);

    @Delete("DELETE FROM likes WHERE id = #{id}")
    void deleteById(int id);

    @Select("SELECT user_id FROM likes WHERE story_id = #{storyId}")
    List<Integer> findAllUserLikedThisStory(int storyId);

    @Select("SELECT * FROM likes WHERE story_id = #{storyId}")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "storyId", column = "story_id")
    })
    List<Like> findAllLikesByStoryId(int storyId);
}