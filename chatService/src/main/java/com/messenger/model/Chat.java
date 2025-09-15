package com.messenger.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chats")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true, length = 50)
    private String name;

    @Column(nullable = false, name = "is_group")
    @Builder.Default
    private boolean isGroup = false;

    @Column(nullable = false, name = "members_count")
    @Builder.Default
    private Integer membersCount = 2;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void setMembersCount(Integer membersCount) {
        // intentionally empty — managed by DB
        // or throw new UnsupportedOperationException("Managed by DB trigger");
    }
}
