package com.messenger.controller;

import com.messenger.dto.AuthMemberDto;
import com.messenger.dto.MessageCreateDto;
import com.messenger.dto.MessageDto;
import com.messenger.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
@Slf4j
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public MessageDto sendMessage(@AuthenticationPrincipal AuthMemberDto authMemberDto,
                                  @RequestBody MessageCreateDto dto) {
        log.info("Send message: {} from {}", dto, authMemberDto.id());
        return messageService.sendMessage(authMemberDto.id(), dto);
    }

    @GetMapping("/{chat_id}")
    public List<MessageDto> getMessages(@PathVariable("chat_id") Long chatId) {
        log.info("Get messages for chat {}", chatId);
        return messageService.getMessages(chatId);
    }
}

