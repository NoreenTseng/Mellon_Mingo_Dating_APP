package com.sda.datingapp.vo;

import com.sda.datingapp.model.state.StoryState;

import java.util.Date;
import java.util.List;

public class StoryVO {
    private int id;
    private int userId;
    private String textContent; // Consider storing image references or URLs
    private String imgContent;
    private Date timestamp;
    private String state;

    private Boolean canEdit;

    private List<LikeVO> likes;
    private String userProfilePicUrl;
    private String userProfileName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserProfilePicUrl() {
        return this.userProfilePicUrl;
    }

    public String getUserProfileName() {
        return this.userProfileName;
    }

    public void setUserProfileName(String userProfileName) {
        this.userProfileName = userProfileName;
    }
    public void setUserProfilePicUrl(String userProfilePicUrl) {
        this.userProfilePicUrl = userProfilePicUrl;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getImgContent() {
        return imgContent;
    }

    public void setImgContent(String imgContent) {
        this.imgContent = imgContent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<LikeVO> getLikes() {
        return likes;
    }

    public void setLikes(List<LikeVO> likes) {
        this.likes = likes;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }
}
