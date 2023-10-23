package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObstacleSetObstacle {

    @Id
    @GeneratedValue
    private UUID uuid;

    @ManyToOne
    private Obstacle obstacle;

    @ManyToOne
    private ObstacleSet obstacleSet;

    private Double distance;

    public ObstacleSetObstacle(ObstacleSet obstacleSet, Obstacle obstacle) {
        this.obstacleSet = obstacleSet;
        obstacleSet.addObstacleSetObstacle(this);
        this.obstacle = obstacle;
        obstacle.addObstacleSetObstacle(this);
    }

}
