package com.example.leaderboard.services;

import com.example.leaderboard.models.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserModel> getAll();
    Optional<UserModel> getById(String id);
    UserModel create(UserModel userModel);
    UserModel update(String id, UserModel userModel);
    void delete(String id);
}
