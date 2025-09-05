package com.messenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "chat_id")
    private Long chatId;

    @Column(nullable = false, name = "sender_id")
    private Long senderId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;
}

