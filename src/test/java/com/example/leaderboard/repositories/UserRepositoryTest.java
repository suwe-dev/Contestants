package com.example.leaderboard.repositories;

import com.example.leaderboard.models.UserModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    private UserModel user1;
    private UserModel user2;

    @BeforeEach
    void setUp() {
        user1 = new UserModel();
        user1.setId("1");
        user1.setUsername("Demo1");

        user2 = new UserModel();
        user2.setId("2");
        user2.setUsername("Demo2");

        userRepository.save(user1);
        userRepository.save(user2);
    }

//    @AfterEach
//    void tearDown() {
//        userRepository.deleteAll();
//    }

    @Test
    void test_findAllUsers() {
        List<UserModel> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    void test_saveUser() {
        UserModel user3 = new UserModel();
        user3.setId("3");
        user3.setUsername("Demo3");
        UserModel user = userRepository.save(user3);
        assertThat(user.getId()).isEqualTo("3");
        assertThat(user.getUsername()).isEqualTo("Demo3");
        assertThat(user.getScore()).isEqualTo(0);
    }

    @Test()
    void test_findById_2_when_exists() {
        Optional<UserModel> user = userRepository.findById("2");
        assertThat(user.isPresent()).isEqualTo(true);
        assertThat(user.get().getId()).isEqualTo("2");
    }

    @Test
    void test_findById_1_when_not_exists() {
        Optional<UserModel> user = userRepository.findById("4");
        assertThat(user.isPresent()).isEqualTo(false);
    }

    @Test
    void test_updateUser_1(){
        user1.setScore(30);
        UserModel user = userRepository.save(user1);
        assertThat(user.getScore()).isEqualTo(30);
    }

    @Test
    void test_deleteUser_2() {
        userRepository.deleteById(user2.getId());
        Optional<UserModel> user = userRepository.findById(user2.getId());
        assertThat(user.isPresent()).isEqualTo(false);
    }

}