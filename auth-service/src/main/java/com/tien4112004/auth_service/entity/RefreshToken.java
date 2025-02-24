package com.tien4112004.auth_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.data.annotation.CreatedDate;

import java.time.ZonedDateTime;
import java.util.UUID;

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
