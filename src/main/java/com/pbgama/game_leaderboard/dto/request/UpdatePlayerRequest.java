package com.pbgama.game_leaderboard.dto.request;

import org.jspecify.annotations.Nullable;

import lombok.Data;

@Data
public class UpdatePlayerRequest {
    @Nullable
    private String username;
    @Nullable
    private String email;
    @Nullable
    private Double currentScore;
    @Nullable
    private Double highestScore;
}
