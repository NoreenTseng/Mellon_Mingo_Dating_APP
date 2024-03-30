package com.sda.datingapp.controller;

import com.sda.datingapp.dto.MessageDto;
import com.sda.datingapp.model.Message;
import com.sda.datingapp.model.User;
import com.sda.datingapp.service.JWTService;
import com.sda.datingapp.service.MessageService;
import com.sda.datingapp.service.UserService;
import com.sda.datingapp.util.TokenUtil;
import org.apache.coyote.Response;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    MessageService messageService;

    @Autowired
    TokenUtil tokenUtil;

    @Autowired
    JWTService jwtService;

    @PostMapping()
    public ResponseEntity<?> sendMessages(@RequestBody MessageDto messageDto, @RequestHeader("Authorization") String authorizationHeader) {
         if(!jwtService.validateToken(tokenUtil.extractJwtToken(authorizationHeader))) {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token unauthorized");
         }
         try {
             messageService.sendMessage(messageDto);
             return ResponseEntity.ok().build();
         } catch (NotFoundException e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
         } catch (RestClientException e) {
             return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service unavailable");
         }
    }

    @GetMapping("/{userIdA}/{userIdB}")
    public ResponseEntity<?> getMessages(@PathVariable Integer userIdA, @PathVariable Integer userIdB, @RequestHeader("Authorization") String authorizationHeader) {
        if(!jwtService.validateToken(tokenUtil.extractJwtToken(authorizationHeader))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("token unauthorized");
        }
        try {
            return ResponseEntity.ok(messageService.getMessages(userIdA, userIdB));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
