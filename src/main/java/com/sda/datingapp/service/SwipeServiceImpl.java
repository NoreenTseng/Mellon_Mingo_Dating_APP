package com.sda.datingapp.service;

import com.sda.datingapp.dto.MatchDto;
import com.sda.datingapp.model.LeftSwipe;
import com.sda.datingapp.model.RightSwipe;
import com.sda.datingapp.repository.LeftSwipeRepository;
import com.sda.datingapp.repository.RightSwipeRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sda.datingapp.client.NotificationClient;
import com.sda.datingapp.repository.MatchRepository;
import java.util.List;

@Service
public class SwipeServiceImpl implements SwipeService {

    @Autowired

    private LeftSwipeRepository leftSwipeRepository;
    @Autowired
    private RightSwipeRepository rightSwipeRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private NotificationClient notificationClient;

    @Override
    public void addLeftSwipe(Integer swiperUserId, Integer swipedUserId) {
        leftSwipeRepository.addLeftSwipe(swiperUserId, swipedUserId);
    }

    @Override
    public void addRightSwipe(Integer swiperUserId, Integer swipedUserId) {
        rightSwipeRepository.addRightSwipe(swiperUserId, swipedUserId);
        System.out.println("swiperUserId: " + swiperUserId);
        List<RightSwipe> swipedUserRightSwipes = rightSwipeRepository.findRightSwipesByUserId(swipedUserId);
        System.out.println("swipedUserRightSwipes: " + swipedUserRightSwipes);
        if(swipedUserRightSwipes.stream().anyMatch(swipe -> swipe.getSwipedUserId()==swiperUserId)){
            matchRepository.addMatch(swiperUserId, swipedUserId);
            MatchDto matchDto = new MatchDto(swipedUserId, swiperUserId);
            notificationClient.notifyNewMatch(matchDto);
        }
    }
}
