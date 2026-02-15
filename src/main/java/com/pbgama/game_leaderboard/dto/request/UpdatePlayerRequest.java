package com.pbgama.game_leaderboard.dto.request;

import lombok.Data;
@Data
public class UpdatePlayerRequest {
    private String username;
    private String email;   
    private Integer currentScore;
    private Integer highestScore;
}
