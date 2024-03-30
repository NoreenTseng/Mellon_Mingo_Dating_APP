package com.sda.datingapp.controller;

import com.sda.datingapp.dto.LeftSwipeDto;
import com.sda.datingapp.dto.RightSwipeDto;
import com.sda.datingapp.model.LeftSwipe;
import com.sda.datingapp.model.RightSwipe;
import com.sda.datingapp.service.SwipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swipes")
public class SwipeController {

    private final SwipeService swipeService;

    @Autowired
    public SwipeController(SwipeService swipeService) {
        this.swipeService = swipeService;
    }

    @PostMapping("/left")
    public ResponseEntity<?> addLeftSwipe(@RequestBody LeftSwipeDto leftSwipeDto) {
        try {
            Integer swiperUserId = leftSwipeDto.getSwiperUserId();
            Integer swipeeUserId = leftSwipeDto.getSwipeeUserId();
            swipeService.addLeftSwipe(swiperUserId, swipeeUserId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.badRequest().body("Failed to add left swipe: " + e.getMessage());
        }
    }

    @PostMapping("/right")
    public ResponseEntity<?> addRightSwipe(@RequestBody RightSwipeDto rightSwipeDto) {
        try {
            Integer swiperUserId = rightSwipeDto.getSwiperUserId();
            Integer swipeeUserId = rightSwipeDto.getSwipeeUserId();
            System.out.println("swiperUserId: " + swiperUserId);
            System.out.println("swipeeUserId: " + swipeeUserId);
            swipeService.addRightSwipe(swiperUserId, swipeeUserId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Log the exception and return an appropriate error response
            return ResponseEntity.badRequest().body("Failed to add right swipe: " + e.getMessage());
        }
    }
}
