package com.sda.datingapp.model;
import java.util.Date;

public class Like {

    private int id;
    private int userId;

    private int storyId;
    private Date timestamp;

    public Like() {
        this.timestamp = new Date();
    }

    // Getters and Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setStoryId(int storyId) {
        this.storyId = storyId;
    }
    public int getId() {
        return this.id;
    }
    public int getStoryId() {
        return this.storyId;
    }
    public int getUserId() {
        return this.userId;
    }
}
