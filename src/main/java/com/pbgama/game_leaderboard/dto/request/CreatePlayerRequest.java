package com.pbgama.game_leaderboard.dto.request;

import lombok.Data;

@Data
public class CreatePlayerRequest {    
    private String username;
    private String email;
}
