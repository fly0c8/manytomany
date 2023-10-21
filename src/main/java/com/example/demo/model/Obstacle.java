package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Obstacle {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Integer height;
    private Instant svt;
    private Instant evt;
    private Instant rvt;

    @OneToMany(mappedBy = "obstacle")
    private List<ObstacleGroupObstacle> obstacleGroupObstacles;

    public void addObstacleGroupObstacle(ObstacleGroupObstacle obstacleGroupObstacle) {
        if (obstacleGroupObstacles == null) obstacleGroupObstacles = new ArrayList<>();
        obstacleGroupObstacles.add(obstacleGroupObstacle);
    }

}
