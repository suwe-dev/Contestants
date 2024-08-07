package com.example.leaderboard.services;

import com.example.leaderboard.models.Badges;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static com.example.leaderboard.services.UserServiceImpl.getBadges;
import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
public class GetBadgeTest {

    @Test
    void test_getBadges_1() {
        Integer score = 25;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_NINJA.getName());

        List<String> actualBadges = getBadges(score, new ArrayList<>());
        assertEquals(expectedBadges, actualBadges);
    }

    @Test
    void test_getBadges_2() {
        Integer score = 50;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_CHAMP.getName());

        List<String> actualBadges = getBadges(score, new ArrayList<>());
        assertEquals(expectedBadges, actualBadges);
    }

    @Test
    void test_getBadges_3() {
        Integer score = 75;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_MASTER.getName());

        List<String> actualBadges = getBadges(score, new ArrayList<>());
        assertEquals(expectedBadges, actualBadges);
    }

    @Test
    void test_getBadges_two() {
        Integer score = 50;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_NINJA.getName());
        expectedBadges.add(Badges.CODE_CHAMP.getName());

        List<String> existingBadges = new ArrayList<>();
        existingBadges.add(Badges.CODE_NINJA.getName());

        List<String> actualBadges = getBadges(score, existingBadges);
        assertEquals(expectedBadges, actualBadges);

    }

    @Test
    void test_getBadges_three() {
        Integer score = 75;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_NINJA.getName());
        expectedBadges.add(Badges.CODE_CHAMP.getName());
        expectedBadges.add(Badges.CODE_MASTER.getName());

        List<String> existingBadges = new ArrayList<>();
        existingBadges.add(Badges.CODE_NINJA.getName());
        existingBadges.add(Badges.CODE_CHAMP.getName());

        List<String> actualBadges = getBadges(score, existingBadges);
        assertEquals(expectedBadges, actualBadges);
    }

    @Test
    void test_getBadges_duplicate_control() {
        Integer score = 75;
        List<String> expectedBadges = new ArrayList<>();
        expectedBadges.add(Badges.CODE_NINJA.getName());
        expectedBadges.add(Badges.CODE_CHAMP.getName());
        expectedBadges.add(Badges.CODE_MASTER.getName());

        List<String> existingBadges = new ArrayList<>();
        existingBadges.add(Badges.CODE_NINJA.getName());
        existingBadges.add(Badges.CODE_CHAMP.getName());
        existingBadges.add(Badges.CODE_MASTER.getName());

        List<String> actualBadges = getBadges(score, existingBadges);
        assertEquals(expectedBadges, actualBadges);
    }

}
