package com.messenger.controller;

import com.messenger.dto.AuthMemberDto;
import com.messenger.dto.ChatCreateDto;
import com.messenger.dto.ChatDto;
import com.messenger.dto.MemberDto;
import com.messenger.model.Chat;
import com.messenger.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatDto createChat(@Valid @RequestBody ChatCreateDto chat,
                              @AuthenticationPrincipal AuthMemberDto member)
    {

    }

    @GetMapping
    public List<Chat> getChats(@AuthenticationPrincipal AuthMemberDto member) {

    }

    @GetMapping("/{chat_id}")
    public Chat getChat(@PathVariable Long chat_id,
                        @AuthenticationPrincipal AuthMemberDto member)
    {

    }

    @PostMapping("/{chat_id}/members")
    public Chat addMember(@PathVariable Long chatId,
                          @RequestBody List<Long> memberIds,
                          @AuthenticationPrincipal AuthMemberDto member)
    {

    }

    @DeleteMapping("/{chat_id}/members/{user_id}")
    public void deleteChat(@PathVariable Long chat_id,
                           @PathVariable Long user_id,
                           @AuthenticationPrincipal AuthMemberDto member)
    {

    }
}
