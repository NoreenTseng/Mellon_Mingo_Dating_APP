package com.sda.datingapp.dto;

public class LeftSwipeDto {
    private int swiperUserId;
    private int swipeeUserId;

    // Getters and setters

    public int getSwiperUserId() {
        return swiperUserId;
    }

    public void setSwiperUserId(int swiperUserId) {
        this.swiperUserId = swiperUserId;
    }

    public int getSwipeeUserId() {
        return swipeeUserId;
    }

    public void setSwipeeUserId(int swipeeUserId) {
        this.swipeeUserId = swipeeUserId;
    }
}
