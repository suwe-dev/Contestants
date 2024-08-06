package com.example.leaderboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contestants")
public class UserModel {
    @Id
    @JsonProperty("user_id")
    private String id;
    private String username;
    @Min(value = 0)
    @Max(value = 100)
    private Double score;
    private List<String> badges;
}
