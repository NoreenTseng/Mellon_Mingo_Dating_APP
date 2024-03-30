package com.sda.datingapp.dto;

import java.util.Date;
import java.util.List;

public class StoryDto {
    private int userId;
    private String textContent;
    private String imgContent;

    private String state;
    private Date timestamp;
    private List<LikeDto> likes;
    public int getUserId() {
        return this.userId;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImgContent() {
        return imgContent;
    }

    public String getState() {
        return state;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setLikes(List<LikeDto> likes) {
        this.likes = likes;
    }

}
