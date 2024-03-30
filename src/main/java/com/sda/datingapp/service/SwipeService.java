package com.sda.datingapp.service;


import com.sda.datingapp.model.LeftSwipe;
import com.sda.datingapp.model.RightSwipe;

public interface SwipeService {
    void addLeftSwipe(Integer swiperUserId, Integer swipedUserId);
    void addRightSwipe(Integer swiperUserId, Integer swipedUserId);
}

