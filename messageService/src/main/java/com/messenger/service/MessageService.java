package com.messenger.service;

import com.messenger.dto.MessageCreateDto;
import com.messenger.dto.MessageDto;
import com.messenger.model.Message;
import com.messenger.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageDto sendMessage(Long senderId, MessageCreateDto dto) {
        Message message = Message.builder()
                .chatId(dto.chatId())
                .senderId(senderId)
                .content(dto.content())
                .createdAt(LocalDateTime.now())
                .build();

        messageRepository.save(message);

        return new MessageDto(
                message.getId(),
                message.getChatId(),
                message.getSenderId(),
                message.getContent(),
                message.getCreatedAt()
        );
    }

    public List<MessageDto> getMessages(Long chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtAsc(chatId)
                .stream()
                .map(m -> new MessageDto(m.getId(), m.getChatId(), m.getSenderId(), m.getContent(), m.getCreatedAt()))
                .toList();
    }
}

