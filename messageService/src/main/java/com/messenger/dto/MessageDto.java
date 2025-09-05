package com.messenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record MessageDto(
        Long id,
        @JsonProperty("chat_id")
        Long chatId,
        @JsonProperty("sender_id")
        Long senderId,
        String content,
        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
}

