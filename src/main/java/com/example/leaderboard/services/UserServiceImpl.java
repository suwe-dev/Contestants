package com.example.leaderboard.services;

import com.example.leaderboard.models.UserModel;
import com.example.leaderboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserModel> getById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserModel create(UserModel userModel) {
        return userRepository.save(userModel);
    }

    @Override
    public UserModel update(String id, UserModel userModel) {
        if(userRepository.existsById(id)) {
            userModel.setId(id);
            return userRepository.save(userModel);
        } else {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
