package com.pbgama.game_leaderboard.model;


import lombok.Data;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private Long id;
    private String username;
    private String email;
    private Integer currentScore;
    private Integer highestScore;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    public void updateScore(Integer newScore) 
    {
        if (newScore > this.currentScore) 
        {
            this.currentScore = newScore;
            if (this.highestScore == null || newScore > this.highestScore)
            {
                this.highestScore = newScore;
            }
            this.updatedAt = LocalDateTime.now();
        }
    }
}
