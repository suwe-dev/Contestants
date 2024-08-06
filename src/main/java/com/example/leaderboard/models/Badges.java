package com.example.leaderboard.models;

import lombok.Getter;

/**
 * This serves as the configuration for Badge list for contestants
 */
@Getter
public enum Badges {
    CODE_NINJA("Code Ninja"),
    CODE_CHAMP("Code Champ"),
    CODE_MASTER("Code Master");

    private final String name;

    Badges(String name) {
        this.name = name;
    }

}
