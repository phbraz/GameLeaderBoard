package com.pbgama.game_leaderboard.dto.request;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CreatePlayerRequest 
{
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username must be alphanumeric")
    private String username;    

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
}
