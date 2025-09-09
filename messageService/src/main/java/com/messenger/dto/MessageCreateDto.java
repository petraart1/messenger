package com.messenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageCreateDto(
        @JsonProperty("chat_id")
        Long chatId,
        String content
) {
}
