package com.sda.datingapp.service;

import com.sda.datingapp.dto.StoryDto;
import com.sda.datingapp.model.Like;
import com.sda.datingapp.model.Story;
import com.sda.datingapp.model.User;
import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.model.state.*;
import com.sda.datingapp.repository.*;
import com.sda.datingapp.vo.LikeVO;
import com.sda.datingapp.vo.StoryVO;
import jakarta.persistence.*;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.javassist.NotFoundException;

import static com.sda.datingapp.util.Constants.*;

@Service
public class StoryServiceImpl implements StoryService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StoryStateContext storyStateContext;
    @Autowired
    private MatchRepository matchRepository;

    @Override
    public List<StoryVO> getAllStories()  throws NotFoundException {
        List<Story> stories = storyRepository.findAll();
        List<StoryVO> storyVOList = new ArrayList<>();
        for(Story story: stories) {
            storyStateContext.setCurrState(story.getState());
            if(!storyStateContext.getStoryState().visibleInStoryLine()) {
                continue;
            }
            UserProfile currentUserProfile = profileRepository.findByUserId(story.getUserId());
            List<Like> likesOfTheStory = likeRepository.findAllLikesByStoryId(story.getId());
            List<LikeVO> likeVOList = new ArrayList<>();
            for(Like like : likesOfTheStory ) {
                int userId = like.getUserId();
                UserProfile userProfile = profileRepository.findByUserId(userId);
                LikeVO likeVO = new LikeVO();
                likeVO.setProfileImage(userProfile.getImageUrls());
                likeVO.setUserId(userId);
                likeVO.setId(like.getId());
                likeVOList.add(likeVO);
            }
            storyVOList.add(buildStoryVO(story, likeVOList, currentUserProfile));
        }
        return storyVOList;
    }

    @Override
    public List<StoryVO> getAllStoriesByUser(int userId) throws NotFoundException {
        List<Story> stories = storyRepository.findByUserId(userId);
        UserProfile currentUserProfile = profileRepository.findByUserId(userId);
        List<StoryVO> storyVOList = new ArrayList<>();
        for(Story story : stories) {
            storyVOList.add(buildStoryVO(story, null, currentUserProfile));
        }
        return storyVOList;
    }

    private StoryVO buildStoryVO(Story story, List<LikeVO> likeVOList, UserProfile currentUserProfile) {
        StoryVO storyVO = new StoryVO();
        storyVO.setUserProfilePicUrl(currentUserProfile.getImageUrls());
        storyVO.setUserProfileName(currentUserProfile.getNickname());
        storyVO.setLikes(likeVOList);
        storyVO.setId(story.getId());
        storyVO.setUserId(story.getUserId());
        storyVO.setState(story.getState());
        storyStateContext.setCurrState(story.getState());
        storyVO.setCanEdit(storyStateContext.getStoryState().canEdit());
        storyVO.setTimestamp(story.getTimestamp());
        storyVO.setImgContent(story.getImgContent());
        storyVO.setTextContent(story.getTextContent());
        return storyVO;
    }

    @Override
    public void createStory(StoryDto storyDto) throws NotFoundException {
        Story story = new Story();
        story.setUserId(storyDto.getUserId());
        story.setImgContent(storyDto.getImgContent());
        story.setTextContent(storyDto.getTextContent());
        story.setState(storyDto.getState());
        story.setTimestamp(new Timestamp(System.currentTimeMillis()));
        storyRepository.save(story);
    }

    public void deleteStory(int id) {
        // Add any additional business logic here if necessary
        storyRepository.deleteById(id);
    }

    @Override
    public void updateStory(int id, StoryDto storyDto) {
        storyDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        storyRepository.updateById(id, storyDto);
    }

    @Override
    public Story updateStoryState(int id, String newState) throws EntityNotFoundException {
        //get current state, map state string stored in db to actual state
        Story story = storyRepository.findById(id);
        storyStateContext.setCurrState(story.getState());
        storyStateContext.changeState(newState, id);
        story.setState(newState);
        return story;
    }


    // Additional methods to handle story updates, deletions, etc.
}
