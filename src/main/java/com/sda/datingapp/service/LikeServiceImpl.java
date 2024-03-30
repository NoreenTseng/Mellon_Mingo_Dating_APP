package com.sda.datingapp.service;

import com.sda.datingapp.dto.LikeDto;
import com.sda.datingapp.model.Like;
import com.sda.datingapp.model.Story;
import com.sda.datingapp.model.User;
import com.sda.datingapp.repository.LikeRepository;
import com.sda.datingapp.repository.StoryRepository;
import com.sda.datingapp.repository.UserRepository;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private StoryRepository storyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    LikeRepository likeRepository;

    public List<Like> getAllLikes(int currentUserId) {
        return likeRepository.findByUserId(1);
    }
    public void createLike(LikeDto likeDto) {
        likeDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        likeRepository.save(likeDto);
    }

    public void dislikeStory(int id) {
        likeRepository.deleteById(id);
    }

    public void deleteStory(int id) {
        // Add any additional business logic here if necessary
        storyRepository.deleteById(id);
    }
}
