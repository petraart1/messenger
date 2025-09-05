package com.messenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record MemberDto(
        Long id,
        String username,
        @JsonProperty("joined_at")
        LocalDateTime joinedAt
) {
}
