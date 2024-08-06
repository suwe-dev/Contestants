package com.example.leaderboard.models;

import lombok.Getter;

/**
 * This serves as the configuration for Badge list for contestants
 */
@Getter
public enum Badges {
    Code_Ninja("Code Ninja"),
    Code_Champ("Code Champ"),
    Code_Master("Code Master");

    private final String name;

    Badges(String name) {
        this.name = name;
    }

}
