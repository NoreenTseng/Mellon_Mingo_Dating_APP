package com.sda.datingapp.model;
import com.sda.datingapp.model.state.ArchivedState;
import com.sda.datingapp.model.state.DraftState;
import com.sda.datingapp.model.state.StoryState;
import java.util.Date;
import java.util.List;


public class Story {
    private int id;
    private int userId;
    private String textContent; // Consider storing image references or URLs
    private String imgContent;
    private Date timestamp;
    private String state;

    public void setTextContent(String content) {
        this.textContent = content;
    }
    public void setImgContent(String content) {
        this.imgContent = content;
    }
    private List<String> likes; // List to store profile picture URLs
    public List<String> getLikes() {
        return likes;
    }
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return this.id;
    }
    public String getTextContent() {
        return this.textContent;
    }

    public String getImgContent() {
        return this.imgContent;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}

