package com.sda.datingapp.model;
import java.sql.Time;

public class LeftSwipe {
    private int id;
    private int swiperUserId;
    private int swipedUserId;

    private Time swipedAt;

    // Getters and setters

    public int getSwiperUserId() {
        return swiperUserId;
    }

    public void setSwiperUserId(int swiperUserId) {
        this.swiperUserId = swiperUserId;
    }

    public int getSwipeeUserId() {
        return swipedUserId;
    }

    public void setSwipedUserId(int swipedUserId) {
        this.swipedUserId = swipedUserId;
    }
}

