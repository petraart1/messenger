package com.messenger.client.http;

import com.messenger.client.ChatClient;
import com.messenger.exception.MembershipCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Component
@Primary
@RequiredArgsConstructor
public class HttpChatClient implements ChatClient {

    private final RestTemplate restTemplate;

    @Value("${chat.service.url}")
    private String baseUrl;

    @Override
    public boolean checkMembership(Long chatId, Long userId, String token) throws MembershipCheckException {
        try {
            String url = baseUrl + "/" + chatId + "/members/" + userId;
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    Boolean.class
            );

            return response.getBody() != null ? response.getBody() : false;


        } catch (Exception e) {
            throw new MembershipCheckException("Failed to check membership", e);
        }
    }
}
