package com.example.leaderboard.services;

import com.example.leaderboard.models.Badges;
import com.example.leaderboard.models.UserModel;
import com.example.leaderboard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    static List<String> getBadges(Integer score, List<String> badges) {

        Set<String> badgeSet = new HashSet<>(badges);
        if (1 <= score && score < 30) {
            badgeSet.add(Badges.CODE_NINJA.getName());
        } else if (30 <= score && score < 60) {
            badgeSet.add(Badges.CODE_CHAMP.getName());
        } else if (60 <= score && score <= 100) {
            badgeSet.add(Badges.CODE_MASTER.getName());
        }
        return new ArrayList<>(badgeSet);
    }

    @Override
    public UserModel update(String id, UserModel userModel) {
        Optional<UserModel> existingUserData = userRepository.findById(id);
        if (existingUserData.isPresent()) {
            UserModel existingUserModel = existingUserData.get();

            Integer new_score = userModel.getScore();
            Integer updated_score;
            updated_score = new_score;

            /* FIXME: Based on requirement, it has to be changed
                - whether score has to be just updated or to be added to the existing score
            */

//          Integer old_score = existingUserModel.getScore();
//          updated_score = old_score + new_score
//          existingUserModel.setScore(old_score + new_score); // Added to the existing score

            existingUserModel.setScore(updated_score); // Update only

            List<String> old_badges = existingUserModel.getBadges();
            List<String> badges = getBadges(updated_score, old_badges);
            existingUserModel.setBadges(badges);

            return userRepository.save(userModel);
        }
        throw new NoSuchElementException("User not found");
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }
}
