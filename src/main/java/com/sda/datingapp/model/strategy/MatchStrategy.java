package com.sda.datingapp.model.strategy;

import com.sda.datingapp.model.UserProfile;
import java.util.List;


public interface MatchStrategy {
    List<UserProfile> findMatches(UserProfile userProfile);

}

