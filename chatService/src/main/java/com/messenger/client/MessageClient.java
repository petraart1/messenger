package com.messenger.client;

import com.messenger.dto.MessageDto;

import java.util.List;

public interface MessageClient {
    public List<MessageDto> getMessages();
}
