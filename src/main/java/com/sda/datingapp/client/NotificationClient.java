package com.sda.datingapp.client;

import com.sda.datingapp.dto.MatchDto;
import com.sda.datingapp.dto.MessageDto;

public interface NotificationClient {

    public void notifyNewMessage(MessageDto messageDto);

    public void notifyNewMatch(MatchDto matchDto);
}
