package com.messenger.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record ChatCreateDto(
        String name,
        @JsonProperty("is_group")
        boolean isGroup,
        @JsonProperty("member_ids")
        List<Long> memberIds
) {
}
