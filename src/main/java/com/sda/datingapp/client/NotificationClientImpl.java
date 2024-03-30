package com.sda.datingapp.client;

import com.sda.datingapp.dto.MatchDto;
import com.sda.datingapp.dto.MessageDto;
import com.sda.datingapp.dto.NotificationDto;
import com.sda.datingapp.model.NotificationType;
import com.sda.datingapp.model.User;
import com.sda.datingapp.repository.ProfileRepository;
import com.sda.datingapp.repository.UserRepository;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationClientImpl implements NotificationClient {

    private static final String url = "http://localhost:8082/notifications";
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    UserRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    private NotificationDto buildFromMessageDto(MessageDto messageDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType(NotificationType.CHAT);
        notificationDto.setSenderId(String.valueOf(messageDto.getSenderId()));
        notificationDto.setReceiverId(String.valueOf(messageDto.getReceiverId()));
        User sender = userRepository.findById(messageDto.getSenderId());
        notificationDto.setContent("Received a new message from " + sender.getUsername() + "!");
        return notificationDto;
    }

    private NotificationDto buildFromMatchDto(Integer senderId, Integer receiverId ) {
        String nickname = profileRepository.findByUserId(receiverId).getNickname();
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setType(NotificationType.MATCH);
        notificationDto.setSenderId(senderId.toString());
        notificationDto.setReceiverId(receiverId.toString());
        notificationDto.setContent("Congrats! You have been matched with " + nickname + "!");
        return notificationDto;
    }

    @Override
    public void notifyNewMessage(MessageDto messageDto) throws RestClientException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        NotificationDto notificationDto = buildFromMessageDto(messageDto);
        HttpEntity<NotificationDto> request = new HttpEntity<>(notificationDto, headers);
        restTemplate.postForObject(url, request, Void.class);
    }

    @Override
    public void notifyNewMatch(MatchDto matchDto) {
        int senderId = matchDto.getSenderId();
        int receiverId = matchDto.getReceiverId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        NotificationDto notificationSender = buildFromMatchDto(senderId, receiverId);
        NotificationDto notificationReceiver = buildFromMatchDto(receiverId, senderId);
        HttpEntity<NotificationDto> requestSender = new HttpEntity<>(notificationSender, headers);
        HttpEntity<NotificationDto> requestReceiver = new HttpEntity<>(notificationReceiver, headers);
        restTemplate.postForObject(url, requestSender, Void.class);
        restTemplate.postForObject(url, requestReceiver, Void.class);
    }
}
