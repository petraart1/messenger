package com.messenger.client.http;

import com.messenger.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpUserClient implements UserClient {
    RestTemplate restTemplate;

    @Value("${user.service.url}")
    String baseUrl;
    @Override
    public String getUsername() {
        return null;
    }
}
