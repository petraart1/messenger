package com.messenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

public record ChatDto(
        Long id,
        String name,
        @JsonProperty("is_group")
        boolean isGroup,
        List<MemberDto> members,
        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
}
