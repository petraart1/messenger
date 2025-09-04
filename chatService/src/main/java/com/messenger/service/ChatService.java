package com.messenger.service;

import com.messenger.dto.AuthMemberDto;
import com.messenger.dto.ChatCreateDto;
import com.messenger.dto.ChatDto;
import com.messenger.dto.MemberDto;
import com.messenger.model.Chat;
import com.messenger.model.ChatMembers;
import com.messenger.repository.ChatMembersRepository;
import com.messenger.repository.ChatRepository;
import com.messenger.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatMembersRepository chatMembersRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public ChatDto createChat(ChatCreateDto chatCreateDto, AuthMemberDto authMemberDto) {
        log.info("Creating chat: {}", chatCreateDto);
        Chat chat = chatRepository.save(
                Chat.builder()
                .name(chatCreateDto.name())
                .isGroup(chatCreateDto.isGroup())
                .build());

        addMemberToChat(chat.getId(), authMemberDto.id());

        for (Long memberId : chatCreateDto.memberIds()) {
            if (!memberId.equals(authMemberDto.id())) {
                addMemberToChat(chat.getId(), memberId);
            }
        }

        return new ChatDto(chat.getId(), chat.getName(), chat.isGroup(), ,chat.getCreatedAt())
    }

    private void addMemberToChat(Long chatId, Long userId) {
        if (chatMembersRepository.existsByChatIdAndUserId(chatId, userId)) {
            throw new IllegalArgumentException("User already in chat");
        }

        ChatMembers member = ChatMembers.builder()
                .chatId(chatId)
                .userId(userId)
                .build();

        chatMembersRepository.save(member);
    }

    private ChatDto getChatWithMembers(Chat chat) {
        List<ChatMembers> members = chatMembersRepository.findByChatId(chat.getId());
        List<MemberDto> memberDtos = members.stream()
                .map()
                .collect(Collectors.toList());

        return new ChatDto(
                chat.getId(),
                chat.getName(),
                chat.isGroup(),
                memberDtos,
                chat.getCreatedAt()
        );
    }

    private MemberDto toMemberDto(ChatMembers member) {
        return new MemberDto(
                member.getUserId(),
                member.getUsername(),
                member.getJoinedAt()
        );
    }
}
