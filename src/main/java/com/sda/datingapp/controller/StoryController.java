package com.sda.datingapp.controller;

import com.sda.datingapp.dto.StoryDto;
import com.sda.datingapp.service.StoryService;
import com.sda.datingapp.vo.StoryVO;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sda.datingapp.model.Story;
import jakarta.persistence.*;
import java.util.List;
import org.apache.ibatis.javassist.NotFoundException;

@RestController
@RequestMapping("/stories")
public class StoryController {
    @Autowired
    StoryService storyService;



    @GetMapping()
    public ResponseEntity<List<StoryVO>> getAllStories() {
        // my story
//        int currentUserId = userId /* Extract user ID from authentication */;
        try {
            return ResponseEntity.ok(storyService.getAllStories());
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<StoryVO>> getAllStoriesByUser(@PathVariable Integer userId) {
        // my story
        int currentUserId = userId /* Extract user ID from authentication */;
        try {
            return ResponseEntity.ok(storyService.getAllStoriesByUser(currentUserId));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> createStory(@RequestBody StoryDto storyDto) {
        try {
            storyService.createStory(storyDto);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{storyId}")
    public ResponseEntity<?> updateStory(@PathVariable("storyId") int storyId, @RequestBody StoryDto storyDto) {
        try {
            //storyDto.setId(storyId); // Set the story ID in the DTO, assuming your DTO has an ID field
            storyService.updateStory(storyId, storyDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Handle other exceptions, perhaps returning an internal server error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStory(@PathVariable int id) {
        storyService.deleteStory(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/{id}/state")
    public ResponseEntity<?> updateStoryState(@PathVariable int id, @RequestBody String newState) {
        try {
            Story updatedStory = storyService.updateStoryState(id, newState);
            return ResponseEntity.ok(updatedStory);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
