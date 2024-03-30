package com.sda.datingapp.service;

import com.sda.datingapp.dto.MessageDto;
import com.sda.datingapp.model.Message;
import com.sda.datingapp.model.User;
import org.apache.ibatis.javassist.NotFoundException;

import java.util.List;

public interface MessageService {
    public void sendMessage(MessageDto messageDto) throws NotFoundException;

    public List<Message> getMessages(Integer userIdA, Integer userIdB) throws NotFoundException;
}
