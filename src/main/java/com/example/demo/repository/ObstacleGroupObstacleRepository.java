package com.example.demo.repository;

import com.example.demo.model.ObstacleGroup;
import com.example.demo.model.ObstacleGroupObstacle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObstacleGroupObstacleRepository extends JpaRepository<ObstacleGroupObstacle, Long> {
    List<ObstacleGroupObstacle> findObstacleGroupObstacleByObstacleGroup_Name(String obstacleGroupName);
}
