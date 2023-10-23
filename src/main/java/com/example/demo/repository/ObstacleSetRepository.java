package com.example.demo.repository;

import com.example.demo.model.ObstacleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ObstacleSetRepository extends JpaRepository<ObstacleSet, UUID> {
}
