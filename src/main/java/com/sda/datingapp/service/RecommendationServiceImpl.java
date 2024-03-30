package com.sda.datingapp.service;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.sda.datingapp.model.StrategyType;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.repository.MatchRepository;
import com.sda.datingapp.model.strategy.MatchStrategy;
import com.sda.datingapp.repository.ProfileRepository;
import com.sda.datingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;
import java.util.Set;

@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ApplicationContext applicationContext;

    // Removed @Qualifier as we will determine the strategy at runtime
    private MatchStrategy matchStrategy;

    private MatchStrategy determineStrategy(StrategyType strategyType) {
        System.out.println("determineStrategy: " + strategyType);
        switch (strategyType) {
            case DISTANCE:
                return applicationContext.getBean("DistanceMatchStrategy", MatchStrategy.class);
            case HOBBIES:
                return applicationContext.getBean("HobbiesMatchStrategy", MatchStrategy.class);
            default:
                throw new IllegalArgumentException("Unknown strategy type: " + strategyType);
        }
    }

    public RecommendationServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<UserProfile> findRecommendations(Integer userId) {
        UserProfile currentUserProfile = profileRepository.findByUserId(userId);
        matchStrategy = determineStrategy(currentUserProfile.getStrategyType());
        System.out.println("current matchStrategy: " + matchStrategy+ " for user: " + currentUserProfile.getUserId());
        return matchStrategy.findMatches(currentUserProfile);
    }
}
