package com.tien4112004.auth_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private boolean revoked = false;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Column(nullable = false)
    @CreatedDate
    private ZonedDateTime createdAt;

    public boolean isExpired() {
        return expiresAt.isBefore(ZonedDateTime.now());
    }
}
