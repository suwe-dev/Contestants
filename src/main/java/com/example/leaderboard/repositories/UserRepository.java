package com.example.leaderboard.repositories;

import com.example.leaderboard.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {
    // User-defined method for querying users based on score value (desc order)
    List<UserModel> findAllByOrderByScoreDesc();
}
