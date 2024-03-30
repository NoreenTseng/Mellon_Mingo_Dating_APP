package com.sda.datingapp.repository;

import com.sda.datingapp.model.LeftSwipe;
import com.sda.datingapp.model.RightSwipe;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RightSwipeRepository {

    @Insert("INSERT INTO rightswipes (swiper_id, swiped_id) VALUES (#{swiperUserId}, #{swipedUserId})")
    void addRightSwipe(Integer swiperUserId ,Integer swipedUserId);

    @Select("SELECT * FROM rightswipes WHERE swiper_id = #{userId}")
    @Results({
            @Result(property = "id", column = "swipe_id"),
            @Result(property = "swiperUserId", column = "swiper_id"),
            @Result(property = "swipedUserId", column = "swiped_id"),
            @Result(property = "swipedAt", column = "swiped_at"),
    })
    List<RightSwipe> findRightSwipesByUserId(Integer userId);
}
