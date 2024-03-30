package com.sda.datingapp.controller;

import com.sda.datingapp.model.UserProfile;
import com.sda.datingapp.service.MatchService;
import com.sda.datingapp.vo.MatchedProfileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<MatchedProfileVO>> getMatches(@PathVariable Integer userId) {
        try {
            List<MatchedProfileVO> matches = matchService.getMatches(userId);
            System.out.println(matches);
            return ResponseEntity.ok(matches);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

}