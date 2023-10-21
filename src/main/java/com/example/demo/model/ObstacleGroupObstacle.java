package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObstacleGroupObstacle {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Obstacle obstacle;

    @ManyToOne(cascade = CascadeType.ALL)
    private ObstacleGroup obstacleGroup;

    public ObstacleGroupObstacle(ObstacleGroup obstacleGroup, Obstacle obstacle) {
        this.obstacleGroup = obstacleGroup;
        obstacleGroup.addObstacleGroupObstacle(this);
        this.obstacle = obstacle;
        obstacle.addObstacleGroupObstacle(this);
    }

}
