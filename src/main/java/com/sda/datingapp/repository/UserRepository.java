package com.sda.datingapp.repository;

import com.sda.datingapp.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface UserRepository {
    @Select("SELECT * FROM users")
    public  List<User> findAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    public User findById(long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    Optional<User> findByUsername(String username);

    @Insert("INSERT INTO users (username, hashed_password) VALUES (#{username}, #{hashedPassword})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int save(User newUser);
}
