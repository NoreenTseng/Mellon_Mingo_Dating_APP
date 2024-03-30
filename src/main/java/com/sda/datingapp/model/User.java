package com.sda.datingapp.model;
import java.time.LocalDateTime;



import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    private String username;
    private String hashed_password;

    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashed_password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashed_password = hashedPassword;
    }

    public Set<Long> getFriends() {
        Set<Long> mockFriendIds = new HashSet<>();

        // Add mock data
        mockFriendIds.add(1L); // Example friend ID
        mockFriendIds.add(2L); // Another example friend ID
        // Add as many mock IDs as needed

        return mockFriendIds;
    }
}