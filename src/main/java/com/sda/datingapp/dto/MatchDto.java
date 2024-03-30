package com.sda.datingapp.dto;


public class MatchDto {
    private int senderId;
    private int receiverId;

    public MatchDto(int senderId, int receiverId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

}

