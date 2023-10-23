package com.example.demo.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.Instant;
import java.util.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Obstacle {
    @Id
    @GeneratedValue
    @JdbcTypeCode(java.sql.Types.CHAR)
    private UUID uuid;
    private String aptId;
    private String rwyId;
    private String name;
    private String comment;
    private Integer height;
    private Instant svt;
    private Instant evt;
    private Instant rvt;

    @OneToMany(mappedBy = "obstacle")
    private Set<ObstacleSetObstacle> obstacleSetObstacles;

    public void addObstacleSetObstacle(ObstacleSetObstacle obstacleSetObstacle) {
        if (obstacleSetObstacles == null) obstacleSetObstacles = new HashSet<>();
        obstacleSetObstacles.add(obstacleSetObstacle);
    }
    @Override
    public String toString() {
        return this.getUuid()+ "/" + this.getName();
    }

}
