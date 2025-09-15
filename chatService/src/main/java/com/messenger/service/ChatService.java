package com.messenger.service;

import com.messenger.dto.AuthMemberDto;
import com.messenger.dto.ChatCreateDto;
import com.messenger.dto.ChatDto;
import com.messenger.dto.MemberDto;
import com.messenger.model.Chat;
import com.messenger.model.ChatMembers;
import com.messenger.repository.ChatMembersRepository;
import com.messenger.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMembersRepository chatMembersRepository;

    public ChatDto createChat(ChatCreateDto chatCreateDto, AuthMemberDto authMemberDto) {
        log.info("Creating chat: {}", chatCreateDto);
        Chat chat = chatRepository.save(
                Chat.builder()
                        .name(chatCreateDto.name())
                        .isGroup(chatCreateDto.isGroup())
                        .membersCount(chatCreateDto.memberIds().size())
                        .build());

        addMemberToChat(chat.getId(), authMemberDto.id());

        for (Long memberId : chatCreateDto.memberIds()) {
            if (!memberId.equals(authMemberDto.id())) {
                addMemberToChat(chat.getId(), memberId);
            }
        }

        List<ChatMembers> chatMembers = chatMembersRepository.findByChatId(chat.getId());
        List<MemberDto> memberDtos = chatMembers.stream()
                .map(m -> new MemberDto(m.getUserId(), "user-" + m.getUserId(), m.getJoinedAt()))
                .collect(Collectors.toList());

        return new ChatDto(chat.getId(), chat.getName(), chat.isGroup(), memberDtos, chat.getCreatedAt());
    }

    public List<ChatDto> getChats(Long userId) {
        log.info("Getting chats for user: {}", userId);
        List<ChatMembers> memberships = chatMembersRepository.findByUserId(userId);

        List<ChatDto> chatDtos = new ArrayList<>();
        for (ChatMembers membership : memberships) {
            Chat chat = chatRepository.findById(membership.getChatId()).orElseThrow();
            List<MemberDto> members = chatMembersRepository.findByChatId(chat.getId())
                    .stream()
                    .map(m -> new MemberDto(m.getUserId(), "user-" + m.getUserId(), m.getJoinedAt()))
                    .toList();

            chatDtos.add(new ChatDto(chat.getId(), chat.getName(), chat.isGroup(), members, chat.getCreatedAt()));
        }
        return chatDtos;
    }

    public ChatDto getChat(Long chatId) {
        // TODO: добавить обращение к сервису для получения пользователей
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        List<MemberDto> members = chatMembersRepository.findByChatId(chatId)
                .stream()
                .map(m -> new MemberDto(m.getUserId(), "user-" + m.getUserId(), m.getJoinedAt()))
                .toList();

        return new ChatDto(chat.getId(), chat.getName(), chat.isGroup(), members, chat.getCreatedAt());
    }

    @Transactional
    public ChatDto addMembers(Long chatId, List<Long> memberIds) {
        log.info("Adding members: {}", memberIds);
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("Chat not found"));

        chat.setGroup(false); // bug
        chatRepository.save(chat);

        if (!chat.isGroup()) {
            throw new IllegalStateException("Cannot add members to a personal chat");
        }

        for (Long memberId : memberIds) {
            addMemberToChat(chatId, memberId);
        }

        return getChat(chatId);
    }

    @Transactional
    public ChatDto deleteMember(Long chatId, Long userId) {
        log.info("Deleting member: {}", userId);
        ChatMembers member = chatMembersRepository.findByChatIdAndUserId(chatId, userId)
                .orElseThrow(() -> new RuntimeException("User not found in chat"));

        chatMembersRepository.delete(member);

        return getChat(chatId);
    }

    public Boolean checkMembership(Long chatId, Long userId) {
        log.info("Checking if member: {}", userId);
        return chatMembersRepository.existsByChatIdAndUserId(chatId, userId);
    }

    private void addMemberToChat(Long chatId, Long userId) {
        if (chatMembersRepository.existsByChatIdAndUserId(chatId, userId)) {
            throw new IllegalArgumentException("User already in chat");
        }

        chatMembersRepository.save(ChatMembers.builder()
                .chatId(chatId)
                .userId(userId)
                .build());
    }

}
