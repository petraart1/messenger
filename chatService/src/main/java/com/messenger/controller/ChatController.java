package com.messenger.controller;

import com.messenger.dto.AuthMemberDto;
import com.messenger.dto.ChatCreateDto;
import com.messenger.dto.ChatDto;
import com.messenger.service.ChatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
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
        log.info("Create chat: {}", chat);
        return chatService.createChat(chat, member);
    }

    @GetMapping
    public List<ChatDto> getChats(@AuthenticationPrincipal AuthMemberDto member) {
        log.info("Get chats: {}", member);
        return chatService.getChats(member.id());
    }

    @GetMapping("/{chat_id}")
    public ChatDto getChat(@PathVariable("chat_id") Long chatId,
                           @AuthenticationPrincipal AuthMemberDto member)
    {
        log.info("Get chat: {}", chatId);
        return chatService.getChat(chatId);
    }

    @PostMapping("/{chat_id}/members")
    public ChatDto addMembers(@PathVariable("chat_id") Long chatId,
                              @RequestBody List<Long> memberIds,
                              @AuthenticationPrincipal AuthMemberDto member)
    {
        log.info("Add member: {}", member);
        return chatService.addMembers(chatId, memberIds);
    }

    @GetMapping("/{chat_id}/members/{user_id}")
    public ResponseEntity<Boolean> checkMember(@PathVariable("chat_id") Long chatId,
                                               @PathVariable("user_id") Long userId,
                                               @AuthenticationPrincipal AuthMemberDto member)
    {
        log.info("Check member: {}", member);
        /*if (!chatService.checkMembership(chatId, member.id())) {
            throw new AccessDeniedException("Access denied");
        }*/

        return ResponseEntity.ok(chatService.checkMembership(chatId, userId));
    }

    @DeleteMapping("/{chat_id}/members/{user_id}")
    public ChatDto deleteMember(@PathVariable("chat_id") Long chatId,
                                @PathVariable("user_id") Long userId,
                                @AuthenticationPrincipal AuthMemberDto member)
    {
        log.info("Delete member {} from chat {}", userId, chatId);
        return chatService.deleteMember(chatId, userId);
    }
}
