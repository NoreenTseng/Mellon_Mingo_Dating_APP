package com.sda.datingapp.model.strategy;

import com.sda.datingapp.model.LeftSwipe;
import com.sda.datingapp.model.RightSwipe;
import com.sda.datingapp.model.User;
import com.sda.datingapp.model.UserProfile;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import com.sda.datingapp.repository.UserRepository;
import com.sda.datingapp.repository.ProfileRepository;
import com.sda.datingapp.repository.LeftSwipeRepository;
import com.sda.datingapp.repository.RightSwipeRepository;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("DistanceMatchStrategy")
public class DistanceMatchStrategy implements MatchStrategy {
    //@Override
    private final ProfileRepository profileRepository;
    private final LeftSwipeRepository leftSwipeRepository;
    private RightSwipeRepository rightSwipeRepository;
    private UserRepository userRepository;

    public DistanceMatchStrategy(ProfileRepository profileRepository,
                                 LeftSwipeRepository leftSwipeRepository,
                                 RightSwipeRepository rightSwipeRepository) {

        this.profileRepository = profileRepository;
        this.leftSwipeRepository = leftSwipeRepository;
        this.rightSwipeRepository = rightSwipeRepository;
    }
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515; // Distance in miles
        System.out.println("dist: " + dist);
        return dist;
    }

    private Set<Integer> getSwipedUserIds(UserProfile currentProfile) {
        Integer currentUserId = currentProfile.getUserId();
        List<LeftSwipe> leftSwipes = leftSwipeRepository.findLeftSwipesByUserId(currentUserId);
        List<RightSwipe> rightSwipes = rightSwipeRepository.findRightSwipesByUserId(currentUserId);
        Set<Integer> swipedUserIds = new HashSet<>();

        for (LeftSwipe leftSwipe : leftSwipes) {
            swipedUserIds.add(leftSwipe.getSwipeeUserId());
        }
        for (RightSwipe rightSwipe : rightSwipes) {
            swipedUserIds.add(rightSwipe.getSwipedUserId());
        }
        return swipedUserIds;
    }

    private List<UserProfile> getEligibleProfiles(UserProfile currentProfile) {
        List<UserProfile> allProfiles = profileRepository.findAll();
        List<UserProfile> eligibleProfiles = new ArrayList<>();
        Set<Integer> swipedUserIds = getSwipedUserIds(currentProfile);
        for(UserProfile profile : allProfiles) {
            if (!swipedUserIds.contains(profile.getUserId())&&profile.getUserId() != currentProfile.getUserId()) {
                eligibleProfiles.add(profile);
            }
        }
        return eligibleProfiles;
    }

    @Override
    public List<UserProfile> findMatches(UserProfile currentProfile) {
        double currentLat = currentProfile.getLastLatitude();
        double currentLon = currentProfile.getLastLongitude();
        System.out.println("currentLat: " + currentLat);
        System.out.println("currentLon: " + currentLon);
        List<UserProfile> allProfiles = getEligibleProfiles(currentProfile);
        System.out.println("allProfiles: " + allProfiles);
        List<Pair<Integer, Double>> matchlist = new ArrayList<>();
        List<UserProfile>res = new ArrayList<>();
        for (UserProfile profile : allProfiles) {
            double distance = calculateDistance(currentLat, currentLon, profile.getLastLatitude(), profile.getLastLongitude());
            Integer currUserId = profile.getUserId();
            Pair<Integer, Double> currPair = Pair.of(currUserId, distance);
            matchlist.add(currPair);
        }
        matchlist.sort((p1, p2) -> Double.compare(p1.getSecond(), p2.getSecond()));
        for(Pair<Integer, Double> pair : matchlist){
            UserProfile currProfile = profileRepository.findByUserId(pair.getFirst());
            res.add(currProfile);
        }
        return res;
    }


}