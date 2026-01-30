package com.pbgama.game_leaderboard.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubmitScoreRequest 
{
    private String playerName;
    private Double score;
}
