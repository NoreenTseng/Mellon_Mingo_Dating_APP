package com.sda.datingapp.service;

import com.sda.datingapp.model.Message;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.repository.MatchRepository;
import com.sda.datingapp.repository.MessageRepository;
import com.sda.datingapp.repository.ProfileRepository;
import com.sda.datingapp.vo.MatchedProfileVO;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MatchedProfileVO> getMatches(Integer userId){
        List<MatchedProfileVO> matchedProfileVOList = new ArrayList<>();
        Set<Integer> matchedProfiles = matchRepository.findMatchesByUserId(userId);
        for(Integer matchedProfileId : matchedProfiles){
            UserProfile matchedProfile = profileRepository.findByUserId(matchedProfileId);
            MatchedProfileVO matchedProfileVO = new MatchedProfileVO();
            matchedProfileVO.setId(matchedProfile.getProfileId());
            matchedProfileVO.setUserId(matchedProfile.getUserId());
            matchedProfileVO.setNickname(matchedProfile.getNickname());
            matchedProfileVO.setImageUrls(matchedProfile.getImageUrls());
            Message lastMessage = messageRepository.findLastMessageBetweenUsers(userId, matchedProfile.getUserId());
            matchedProfileVO.setLastestMessage(lastMessage != null ? lastMessage.getContent() : "");
            matchedProfileVOList.add(matchedProfileVO);
        }
        return matchedProfileVOList;
    }


}