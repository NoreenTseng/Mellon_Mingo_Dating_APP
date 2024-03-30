package com.sda.datingapp.service;

import com.sda.datingapp.dto.StoryDto;
import com.sda.datingapp.model.Message;
import com.sda.datingapp.model.Story;
//import javax.persistence.*;
import com.sda.datingapp.vo.StoryVO;
import jakarta.persistence.*;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;


public interface StoryService {
    public List<StoryVO> getAllStories() throws NotFoundException;

    public List<StoryVO> getAllStoriesByUser(int userId) throws NotFoundException;

    public void createStory(StoryDto story) throws NotFoundException;

//    public String changeStoryState(Long storyId, StoryState newState);

    Story updateStoryState(int id, String newState) throws EntityNotFoundException;

    void deleteStory(int id);

    void updateStory(int id, StoryDto storyDto);

//    List<StoryVO> getAllStoriesByUser(int currentUserId);
}
