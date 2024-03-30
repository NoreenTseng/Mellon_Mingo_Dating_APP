package com.sda.datingapp.model.strategy;

import com.sda.datingapp.model.ProfileAttributeType;
import com.sda.datingapp.model.User;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.util.Pair;

import java.util.*;

import static org.springframework.core.OrderComparator.sort;

@Component("HobbiesMatchStrategy")
public class HobbiesAndGenderMatchStrategy implements MatchStrategy {

    @Autowired
    private ProfileRepository profileRepository;
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) +
                    Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                            Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515; // Distance in miles
            return dist;
        }
    }

    private double getGenderScore(String g1, String g2){
        if(g1.equals(g2)) return 0.5;
        return 1.0;
    }

    private double getHobbyScore(String hobbies1, String hobbies2) {
        // Split the strings into arrays based on the underscore delimiter
        String[] hobbiesArray1 = hobbies1.split("_");
        String[] hobbiesArray2 = hobbies2.split("_");

        double initialScore = 0;
        double intersection = Math.min(hobbiesArray1.length, hobbiesArray2.length);
        Set<String> s1 = new HashSet<>(Arrays.asList(hobbiesArray1));

        for (String hobby : hobbiesArray2) {
            if (s1.contains(hobby)) {
                initialScore += 1;
            }
        }

        return intersection > 0 ? initialScore / intersection : 0;
    }

    private double getEducationScore(ProfileAttributeType.Education edu1, ProfileAttributeType.Education edu2) {
        int diff = edu2.ordinal() - edu1.ordinal();
        // Optimal match: one level higher
        if (diff == 1) {
            return 1.0;
        }
        // Acceptable match: same level or one level lower
        else if (diff == 0 || diff == -1) {
            return 0.5;
        }
        // Less desirable match: more than one level difference
        else {
            return 0.2;
        }
    }


    private double getDistanceScore(UserProfile p1, UserProfile p2) {
        double currDistance = calculateDistance(p1.getLastLatitude(), p1.getLastLongitude(), p2.getLastLatitude(), p2.getLastLongitude());
        double diff = Math.max(1.0,20-currDistance);
        return diff/20;
    }

    private double calculateAttributeScore(Enum<?> e1, Enum<?> e2, int maxScore) {
        return (maxScore - Math.abs(e1.ordinal() - e2.ordinal())) / (double) maxScore;
    }

    private double getMatchScore(UserProfile p1, UserProfile p2) {
        double distanceScore = getDistanceScore(p1, p2);
        double genderScore = getGenderScore(p1.getGender(), p2.getGender());
        double hobbyScore = getHobbyScore(p1.getHobbies(), p2.getHobbies());
        double drinkScore = calculateAttributeScore(p1.getDrinks(), p2.getDrinks(), 3);
        double drugScore = calculateAttributeScore(p1.getDrugs(), p2.getDrugs(), 3);
        double educationScore = getEducationScore(p1.getEducation(), p2.getEducation());

        return 0.2 * distanceScore + 0.1 * genderScore + 0.3 * hobbyScore +
                0.1 * drinkScore + 0.1 * drugScore + 0.2 * educationScore;
    }
    //@Override
    public List<UserProfile> findMatches(UserProfile currentProfile) {
        System.out.println("currentProfile: " + currentProfile);
        double currentLat = currentProfile.getLastLatitude();
        double currentLon = currentProfile.getLastLongitude();

        String currentHobbies = currentProfile.getHobbies();
        String currentGender = currentProfile.getGender();
        List<UserProfile> allProfiles = profileRepository.findAll();
        System.out.println("allProfiles: " + allProfiles);
        List<Pair<Integer, Double>> matchlist = new ArrayList<>();
        List<UserProfile>res = new ArrayList<>();
        for(UserProfile profile : allProfiles){
            if(profile.getUserId() == currentProfile.getUserId()) continue;
            double matchScore = getMatchScore(currentProfile, profile);
            Pair<Integer, Double> currPair = Pair.of(profile.getUserId(), matchScore);
            matchlist.add(currPair);
        }
        matchlist.sort((p1, p2) -> Double.compare(p2.getSecond(), p1.getSecond()));
        for(Pair<Integer, Double> pair : matchlist) {
            UserProfile currProfile = profileRepository.findByUserId(pair.getFirst());
            res.add(currProfile);
        }
        return res;
    }


}
