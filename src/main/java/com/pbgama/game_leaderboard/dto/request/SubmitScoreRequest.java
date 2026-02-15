package com.pbgama.game_leaderboard.dto.request;

import lombok.Data;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
@Data
public class SubmitScoreRequest 
{
    
    @NotNull(message = "Player ID is required")
    @Min(value = 1, message = "Player ID must be greater than 0")
    private Long playerId;
    
    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    private Integer score;
}