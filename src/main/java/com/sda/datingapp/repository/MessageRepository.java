package com.sda.datingapp.repository;

import com.sda.datingapp.dto.MessageDto;
import com.sda.datingapp.model.Message;
import com.sda.datingapp.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageRepository {
    @Insert("INSERT INTO messages (sender_id, receiver_id, content, content_type, timestamp) " +
            "VALUES (#{senderId}, #{receiverId}, #{content}, #{contentType}, #{timestamp})")
    public void saveMessage(MessageDto messageDto);

    @Select("SELECT * FROM messages " +
            "WHERE (sender_id = #{userIdA} AND receiver_id = #{userIdB}) OR " +
            "(sender_id = #{userIdB} AND receiver_id = #{userIdA}) " +
            "ORDER BY timestamp")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "senderId", column = "sender_id"),
            @Result(property = "receiverId", column = "receiver_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "contentType", column = "content_type"),
            @Result(property = "timestamp", column = "timestamp"),
    })
    public List<Message> getMessages(Integer userIdA, Integer userIdB);

    @Select("SELECT * FROM messages " +
            "WHERE (sender_id = #{userIdA} AND receiver_id = #{userIdB}) OR " +
            "(sender_id = #{userIdB} AND receiver_id = #{userIdA}) " +
            "ORDER BY timestamp DESC LIMIT 1")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "senderId", column = "sender_id"),
            @Result(property = "receiverId", column = "receiver_id"),
            @Result(property = "content", column = "content"),
            @Result(property = "contentType", column = "content_type"),
            @Result(property = "timestamp", column = "timestamp"),
    })
    public Message findLastMessageBetweenUsers(Integer userIdA, Integer userIdB);
}
