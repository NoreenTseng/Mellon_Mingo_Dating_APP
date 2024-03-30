package com.sda.datingapp.repository;

import com.sda.datingapp.model.LeftSwipe;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LeftSwipeRepository {

    @Insert("INSERT INTO leftswipes (swiper_id, swiped_id) VALUES (#{swiperUserId}, #{swipedUserId})")
    void addLeftSwipe(Integer swiperUserId ,Integer swipedUserId);

    @Select("SELECT * FROM leftswipes WHERE swiper_id = #{userId}")
    @Results({
            @Result(property = "id", column = "swipe_id"),
            @Result(property = "swiperUserId", column = "swiper_id"),
            @Result(property = "swipedUserId", column = "swiped_id"),
            @Result(property = "swipedAt", column = "swiped_at"),
    })
    List<LeftSwipe> findLeftSwipesByUserId(Integer userId);
}
