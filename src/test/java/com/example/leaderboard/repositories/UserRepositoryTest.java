package com.example.leaderboard.repositories;

import com.example.leaderboard.models.UserModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
        user1.setUsername("Suwethan");

        user2 = new UserModel();
        user2.setId("2");
        user2.setUsername("Vijay");

        userRepository.save(user1);
        userRepository.save(user2);
    }

    @AfterEach
    void tearDown() {
        user1 = null;
        userRepository.deleteAll();
    }

    @Test
    @Order(1)
    void saveUser_1() {
        UserModel user1 = userRepository.save(this.user1);
        assertThat(user1.getId()).isEqualTo("1");
        assertThat(user1.getUsername()).isEqualTo("Suwethan");
        assertThat(user1.getScore()).isEqualTo(0);
        assertThat(user1.getBadges().size()).isEqualTo(0);
    }

    @Test
    @Order(2)
    void getAllUser() {
        List<UserModel> users = userRepository.findAll();
        assertThat(users.size()).isEqualTo(2);
        assertThat(users.getFirst().getId()).isEqualTo("1");
        assertThat(users.getFirst().getUsername()).isEqualTo("Suwethan");
    }

    @Test
    @Order(3)
    void saveUser_2() {
        user2 = new UserModel();
        user2.setId("2");
        user2.setUsername("Vijay");

        assertThat(user2.getId()).isEqualTo("2");
        assertThat(user2.getUsername()).isEqualTo("Vijay");
        assertThat(user2.getScore()).isEqualTo(0);
        assertThat(user2.getBadges().size()).isEqualTo(0);
    }

    @Test
    @Order(5)
    void getUserById_1() {
        Optional<UserModel> user = userRepository.findById("1");
        assertThat(user.isPresent()).isEqualTo(true);
        assertThat(user.get()).isInstanceOf(UserModel.class);
        assertThat(user.get().getId()).isEqualTo("1");
    }

    @Test
    @Order(6)
    void getUserById_2() {
        Optional<UserModel> user = userRepository.findById("2");
        assertThat(user.isPresent()).isEqualTo(true);
        assertThat(user.get()).isInstanceOf(UserModel.class);
        assertThat(user.get().getId()).isEqualTo("2");
    }

    @Test
    @Order(7)
    void updateUser_1() {
        user1.setScore(30);
        UserModel user = userRepository.save(user1);
        assertThat(user.getId()).isEqualTo("1");
        assertThat(user.getScore()).isEqualTo(30);
    }

    @Test
    @Order(8)
    void deleteUser_2() {
        userRepository.deleteById("2");
        Optional<UserModel> user = userRepository.findById("2");
        assertThat(user.isPresent()).isEqualTo(false);

    }

}