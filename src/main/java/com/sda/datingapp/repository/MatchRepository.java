package com.sda.datingapp.repository;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Set;

@Mapper
@Repository
public interface MatchRepository {

    @Insert("INSERT INTO matches (sender_id, receiver_id) VALUES (#{senderId}, #{receiverId})")
    void addMatch(int senderId, int receiverId);

    @Select("SELECT CASE WHEN m.sender_id = #{senderId} " +
            " THEN m.receiver_id ELSE m.sender_id END AS match_id" +
            " FROM matches m WHERE m.sender_id = #{senderId} OR m.receiver_id = #{senderId}")
    @Results({
            @Result(property = "matchId", column = "match_id")
    })
    Set<Integer> findMatchesByUserId(int senderId);
}
