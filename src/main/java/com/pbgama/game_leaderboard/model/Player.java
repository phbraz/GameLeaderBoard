package com.pbgama.game_leaderboard.model;

import java.util.UUID;

import org.jspecify.annotations.Nullable;

import lombok.Data;
import java.time.LocalDateTime;
import lombok.Builder;

@Data
@Builder
public class Player {
    @Builder.Default
    private UUID id = UUID.randomUUID();
    private String username;
    private String email;
    @Nullable
    private Double currentScore;
    @Nullable
    private Double highestScore;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Nullable
    private LocalDateTime updateAt;
}
