package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ObstacleSet {

    @Id
    @GeneratedValue
    @JdbcTypeCode(java.sql.Types.CHAR)
    private UUID uuid;

    private String aptId;
    private String rwyId;
    private String name;
    private String comment;
    private Integer accHgtAE;
    private Integer thrHgtAE;
    private Integer thrHgtEO;
    private String eoProc;
    private String gaProc;
    private Integer minTas;
    private Integer maxTas;

    // ALL, PERSIST, MERGE, REMOVE, REFRESH, DETACH;
    @OneToMany(mappedBy = "obstacleSet")
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
