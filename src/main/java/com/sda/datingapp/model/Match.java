package com.sda.datingapp.model;

public class Match {
    private int senderId;
    private int receiverId;

    public Match(int senderId, int receiverId) {
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
