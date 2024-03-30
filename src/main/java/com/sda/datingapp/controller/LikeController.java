package com.sda.datingapp.controller;

import com.sda.datingapp.model.Like;
import com.sda.datingapp.dto.LikeDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sda.datingapp.service.LikeService;

import java.util.List;

@RestController
@RequestMapping("/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> likeStory(@RequestBody LikeDto likeDto) {
        likeService.createLike(likeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> dislikeStory(@PathVariable int id) {
        likeService.dislikeStory(id);
        return ResponseEntity.ok().build();
    }
}
