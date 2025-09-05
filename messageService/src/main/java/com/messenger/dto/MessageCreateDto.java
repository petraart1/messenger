package com.messenger.dto;

public record MessageCreateDto(
        Long chatId,
        String content
) {
}
