package com.messenger.repository;

import com.messenger.model.ChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatMembersRepository extends JpaRepository<ChatMembers, Long> {
    boolean existsByChatIdAndUserId(Long chatId, Long userId);
    List<ChatMembers> findByChatId(Long chatId);
    Optional<ChatMembers> findByChatIdAndUserId(Long chatId, Long userId);
    List<ChatMembers> findByUserId(Long userId);
}
