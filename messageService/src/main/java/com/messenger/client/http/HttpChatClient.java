/*
package com.messenger.client.http;

import com.messenger.client.ChatClient;
import com.messenger.exception.MembershipCheckException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
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

    @Value("${NEURO_CONTAINER_URL}")
    private String baseUrl;
    */
/*public HttpChatClient(@Value("${chat.service.url}") String chatServiceUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(chatServiceUrl)
                .build();
    }*//*


    public String getClarifyingQuestion(String topic,
                                        List<Map<String, String>> conversationHistory,
                                        String sourceUrl,
                                        String sourceTitle) {
        String url = baseUrl + "/clarifying-question";

        Map<String, Object> payload = new HashMap<>();
        payload.put("topic", topic);
        payload.put("conversation_history", conversationHistory != null ? conversationHistory : List.of());
        if (sourceUrl != null && !sourceUrl.isBlank()) {
            payload.put("source_url", sourceUrl);
        }
        if (sourceTitle != null && !sourceTitle.isBlank()) {
            payload.put("source_title", sourceTitle);
        }

        return postJson(url, payload, "question");
    }

    @Override
    public boolean checkMembership(Long chatId, Long userId) throws MembershipCheckException {
        try {
            return .get()
                    .uri("/api/chats/{chatId}/members/{userId}", chatId, userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .timeout(Duration.ofSeconds(2))
                    .onErrorMap(TimeoutException.class, e -> new MembershipCheckException("Timeout checking membership", e))
                    .block();
        } catch (Exception e) {
            throw new MembershipCheckException("Failed to check membership", e);
        }
    }
}
*/
