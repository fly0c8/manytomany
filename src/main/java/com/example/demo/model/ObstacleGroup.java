package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObstacleGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    // ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH;
    @OneToMany(mappedBy = "obstacleGroup")
    private List<ObstacleGroupObstacle> obstacleGroupObstacles;

    public void addObstacleGroupObstacle(ObstacleGroupObstacle obstacleGroupObstacle) {
        if (obstacleGroupObstacles == null) obstacleGroupObstacles = new ArrayList<>();
        obstacleGroupObstacles.add(obstacleGroupObstacle);
    }

}
