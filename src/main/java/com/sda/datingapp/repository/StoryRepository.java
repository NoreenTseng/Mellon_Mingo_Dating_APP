package com.sda.datingapp.repository;

import com.sda.datingapp.dto.StoryDto;
import com.sda.datingapp.vo.StoryVO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

import com.sda.datingapp.model.Story;

@Mapper
@Repository
public interface StoryRepository {
//public interface StoryRepository extends JpaRepository<Story, Long> {

    @Select("SELECT * FROM stories ORDER BY timestamp DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "imgContent", column = "img_content"),
            @Result(property = "textContent", column = "text_content"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "state", column = "state"),
    })
    List<Story> findAll();

    @Select("SELECT * FROM stories WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "imgContent", column = "img_content"),
            @Result(property = "textContent", column = "text_content"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "state", column = "state"),
    })
    Story findById(int id);

    @Select("SELECT * FROM stories WHERE user_id = #{userId} ORDER BY timestamp DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "imgContent", column = "img_content"),
            @Result(property = "textContent", column = "text_content"),
            @Result(property = "timestamp", column = "timestamp"),
            @Result(property = "state", column = "state"),
    })
    List<Story> findByUserId(int user_id);

    @Insert("INSERT INTO stories (text_content, img_content, user_id, state, timestamp) VALUES (#{textContent}, #{imgContent}, #{userId}, #{state}, #{timestamp})")
    int save(Story story);

    @Update("UPDATE stories SET text_content = #{storyDto.textContent}, img_content = #{storyDto.imgContent}, state = #{storyDto.state}, timestamp = #{storyDto.timestamp} " +
            "WHERE id = #{id}")
    void updateById(@Param("id") int id, @Param("storyDto") StoryDto storyDto);

    @Update("UPDATE stories SET state = #{state} WHERE id = #{id}")
    void updateState(@Param("id") int id, @Param("state") String state);

    @Delete("DELETE FROM stories WHERE id = #{id}")
    void deleteById(int id);
}
