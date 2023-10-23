package com.example.demo.repository;

import com.example.demo.model.Obstacle;
import com.example.demo.model.ObstacleSetObstacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, UUID> {
    @Modifying
    void deleteObstacleByName(String name);
}
