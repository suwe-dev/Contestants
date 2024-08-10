package com.example.leaderboard.services;

import com.example.leaderboard.models.UserModel;
import com.example.leaderboard.repositories.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserModel user1;
    private UserModel user2;
    private UserModel user3;

    @BeforeEach
    void setUp() {
        user1 = new UserModel();
        user1.setId("1");
        user1.setUsername("Demo1");

        user2 = new UserModel();
        user2.setId("2");
        user2.setUsername("Demo2");

        user3 = new UserModel();
        user3.setId("3");
        user3.setUsername("Demo3");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_getAll() {
        List<UserModel> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        when(userRepository.findAllByOrderByScoreDesc()).thenReturn(users);

        List<UserModel> results = userService.getAll();

        assertNotNull(results);
        assertEquals(2, users.size());

        verify(userRepository, times(1)).findAllByOrderByScoreDesc();
    }

    @Test
    void test_getById() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user1));

        Optional<UserModel> result = userService.getById("1");

        assertTrue(result.isPresent());
        assertEquals(user1, result.get());
        assertEquals("1", result.get().getId());
        assertEquals("Demo1", result.get().getUsername());
        assertEquals(0, result.get().getScore());
        assertEquals(0, result.get().getBadges().size());

        verify(userRepository, times(1)).findById(anyString());
    }

    @Test
    void test_create() {
        when(userRepository.save(any(UserModel.class))).thenReturn(user3);

        UserModel result = userService.create(user3);

        assertNotNull(result);
        assertEquals("3", result.getId());
        assertEquals("Demo3", result.getUsername());
        assertEquals(0, result.getScore());
        assertEquals(0, result.getBadges().size());

        verify(userRepository, times(1)).save(user3);
    }

    @Test
    void test_update() {
        when(userRepository.findById("1")).thenReturn(Optional.of(user1));

        Optional<UserModel> before = userService.getById("1");
        assertTrue(before.isPresent());
        assertEquals(0, before.get().getScore());

        user1.setScore(25);
        userService.update("1", user1);

        Optional<UserModel> after = userService.getById("1");
        assertTrue(after.isPresent());
        assertEquals(25, after.get().getScore());
        assertEquals(1, after.get().getBadges().size());

        verify(userRepository, times(3)).findById(anyString());
    }

    @Test
    void test_delete() {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user3)).thenReturn(Optional.empty());

        Optional<UserModel> before = userService.getById("3");
        assertTrue(before.isPresent());

        userService.delete("3");

        Optional<UserModel> after = userService.getById("3");
        assertFalse(after.isPresent());

        verify(userRepository, times(2)).findById(anyString());
        verify(userRepository, times(1)).deleteById(anyString());
    }

}