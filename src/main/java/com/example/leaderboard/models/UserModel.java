package com.example.leaderboard.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
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
    private Integer score = 0;
    private List<String> badges = new ArrayList<>();
}
