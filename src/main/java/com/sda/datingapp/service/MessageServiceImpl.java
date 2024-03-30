package com.sda.datingapp.service;

import com.sda.datingapp.client.NotificationClient;
import com.sda.datingapp.dto.MessageDto;
import com.sda.datingapp.model.Message;
import com.sda.datingapp.repository.MessageRepository;
import com.sda.datingapp.repository.UserRepository;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    NotificationClient notificationClient;

    @Override
    public void sendMessage(MessageDto messageDto) throws NotFoundException, RestClientException {
        //check sender and receiver exist in the table
        if (userRepository.findById(messageDto.getSenderId()) == null
                || userRepository.findById(messageDto.getReceiverId()) == null) {
            throw new NotFoundException("User not found");
        }
        messageDto.setTimestamp(new Timestamp(System.currentTimeMillis()));
        messageRepository.saveMessage(messageDto);
        notificationClient.notifyNewMessage(messageDto);
    }

    @Override
    public List<Message> getMessages(Integer userIdA, Integer userIdB) throws NotFoundException {
        if (userRepository.findById(userIdA) == null
                || userRepository.findById(userIdB) == null) {
            throw new NotFoundException("User not found");
        }
        return messageRepository.getMessages(userIdA, userIdB);
    }
}
