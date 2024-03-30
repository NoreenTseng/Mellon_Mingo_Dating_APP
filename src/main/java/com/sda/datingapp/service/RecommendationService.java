package com.sda.datingapp.service;


import com.sda.datingapp.model.StrategyType;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.model.strategy.MatchStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendationService {
    // Method to perform matching
    //public void sendMatch(Integer userId1, Integer userId2);

    public List<UserProfile> findRecommendations(Integer userId);

    //public MatchStrategy determineStrategy(StrategyType strategyType);
}
