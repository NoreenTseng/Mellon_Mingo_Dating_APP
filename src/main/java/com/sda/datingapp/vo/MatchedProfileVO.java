package com.sda.datingapp.vo;


public class MatchedProfileVO {
    Integer id;
    Integer userId;
    String imageUrls;
    String nickname;
    String lastestMessage;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setImageUrls(String imageUrls) {
        this.imageUrls = imageUrls;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLastestMessage(String lastestMessage) {
        this.lastestMessage = lastestMessage;
    }

    public Integer getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getImageUrls() {
        return imageUrls;
    }

    public String getNickname() {
        return nickname;
    }

    public String getLastestMessage() {
        return lastestMessage;
    }

}