package com.example.demo.repository;

import com.example.demo.model.ObstacleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ObstacleGroupRepository extends JpaRepository<ObstacleGroup, Long> {
}
