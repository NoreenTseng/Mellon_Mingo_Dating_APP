package com.sda.datingapp.model;

import java.sql.Time;

public class RightSwipe {
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

    public int getSwipedUserId() {
        return swipedUserId;
    }

    public void setSwipeeUserId(int swipeeUserId) {
        this.swipedUserId = swipeeUserId;
    }
}
