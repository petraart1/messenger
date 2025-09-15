package com.messenger.client.http;

import com.messenger.client.MessageClient;
import com.messenger.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequiredArgsConstructor
public class HttpMessageClient implements MessageClient {
    RestTemplate restTemplate;

    @Value("${message.service.url}")
    String baseUrl;

    @Override
    public List<MessageDto> getMessages() {
        return List.of();
    }

}
