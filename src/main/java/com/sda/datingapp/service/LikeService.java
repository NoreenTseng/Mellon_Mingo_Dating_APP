package com.sda.datingapp.service;
import com.sda.datingapp.dto.LikeDto;
import com.sda.datingapp.model.Like;
import java.util.List;



public interface LikeService {

        public void createLike(LikeDto likeDto);

        List<Like> getAllLikes(int currentUserId);

        void dislikeStory(int id);

//        boolean existsByUserAndStory(User user, Story story);

//        void save(Like like);

}
